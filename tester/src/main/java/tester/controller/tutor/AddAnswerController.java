package tester.controller.tutor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tester.Constants;
import tester.controller.AbstractController;
import tester.exception.EditException;
import tester.service.LogService;

@WebServlet(urlPatterns="/tutor/home/test/add-answer")
public class AddAnswerController extends AbstractController {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LogService.info(this,"AddAnswerController GET");
		req.setAttribute("idTest", req.getParameter("idTest"));
		req.setAttribute("testName", req.getParameter("testName"));
		req.setAttribute("questionId", req.getParameter("questionId"));
		forwardToPage("/WEB-INF/view/tutor/addAnswer.jsp", "/WEB-INF/view/shell/tutorShell.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LogService.info(this,"AddAnswerController POST");
		req.setAttribute("idTest", req.getParameter("idTest"));
		req.setAttribute("testName", req.getParameter("testName"));
		String id= req.getParameter("id");
		String answerName=req.getParameter("answerName");
		String right=req.getParameter("right");
		Boolean r=null;
		if(right==null){
			r=false;
		}else{
			r=true;
		}
		try {
			getTutorService().addAnswer(r, answerName, Long.decode(id));
			
		} catch (NumberFormatException e) {
			
		} catch (EditException e) {
			
			forwardToPage("/WEB-INF/view/tutor/editQuestionError.jsp", "/WEB-INF/view/shell/tutorShell.jsp", req, resp);
			
		}
		
		req.setAttribute(Constants.TUTOR_SUCCESS, "Answer has been added");
		req.getRequestDispatcher("/tutor/home/test").forward(req, resp);
		
		
	}

}
