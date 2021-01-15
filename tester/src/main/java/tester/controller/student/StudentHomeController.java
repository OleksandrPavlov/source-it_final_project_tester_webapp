package tester.controller.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tester.constants.NavigationConstants;
import tester.model.*;
import tester.Constants;
import tester.controller.AbstractController;
import tester.exception.GetTestException;
import tester.service.LogService;

@WebServlet(urlPatterns="/student/home")
public class StudentHomeController extends AbstractController {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LogService.info(this,"StudentHomeController GET");
		List<TestModel> testModelList=null;
		try {
			testModelList=getStudentService().getAllTests();
		} catch (GetTestException e) {
			req.setAttribute(Constants.STUDENT_ERROR, e.getMessage());
		}
		req.setAttribute(Constants.STUDENT_DATA, testModelList);
				forwardToPage("/WEB-INF/view/student/studentHome.jsp", "/WEB-INF/view/shell/studentShell.jsp", req, resp);
	}

}
