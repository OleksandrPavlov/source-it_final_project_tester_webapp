package tester.controller.tutor;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tester.Constants;
import tester.controller.AbstractController;
import tester.exception.GetTestException;
import tester.model.AccountModel;
import tester.model.TestModel;
import tester.service.LogService;

@WebServlet(urlPatterns = "/tutor/home")
public class TutorHomeController extends AbstractController {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LogService.info(this, "TutorHomeController GET");
		AccountModel accountModel = (AccountModel) req.getSession().getAttribute(Constants.CURRENT_ACCOUNT);
		Long quantityRows = getTutorService().testQuantity(accountModel.getId());
		Long quantityPage = quantityPage(quantityRows, Constants.LIMIT_ROWS_DATABASE);
		List<TestModel> testList = null;
		Integer currentPage;
		Long prev  ;
		Long next  ;
		req.setAttribute("QUANTITY_PAGE", quantityPage);

		if (Objects.isNull(req.getParameter("koef"))) {
			next = 2L;
			prev = 1L;
			currentPage = 1;

		} else {
			next = getNext(req.getParameter("koef"), quantityPage);
			prev = getPrev(req.getParameter("koef"));
			currentPage = Integer.decode(req.getParameter("koef"));
		}

		try {

			testList = getTutorService().getAllTestById(accountModel.getId(), Constants.LIMIT_ROWS_DATABASE,
					Constants.OFFSET_DATABASE * (currentPage - 1));

		} catch (GetTestException e) {
			req.setAttribute(Constants.TUTOR_ERROR, e.getMessage());

		}
		req.setAttribute("CURRENT_PAGE", currentPage);
		req.setAttribute("PREVIOUSE", prev);
		req.setAttribute("NEXT", next);
		req.setAttribute(Constants.TUTOR_DATA, testList);

		forwardToPage("/WEB-INF/view/tutor/tutorHome.jsp", "/WEB-INF/view/shell/tutorShell.jsp", req, resp);

	}

}
