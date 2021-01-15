package tester.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tester.Constants;
import tester.controller.AbstractController;
import tester.exception.GetTestException;
import tester.model.TestAndAuthorModel;
import tester.service.LogService;

@WebServlet(urlPatterns="/student/home/proceed")
public class ProceedToTheTestController extends AbstractController {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LogService.info(this,"ProceedToTheTestController GET");
		TestAndAuthorModel testAndAuthorModel=null;
		Long idTest=null;
		
		idTest=Long.decode(req.getParameter("idTest"));
		req.setAttribute("idTest", idTest);
		try {
			testAndAuthorModel=getStudentService().getTestAndAuthorModel(idTest);
		} catch (GetTestException e) {
			req.setAttribute(Constants.STUDENT_ERROR, e.getMessage());
		}
		req.setAttribute(Constants.STUDENT_DATA, testAndAuthorModel);
		forwardToPage("/WEB-INF/view/student/proceedTest.jsp", "/WEB-INF/view/shell/studentShell.jsp", req, resp);
	}
}
