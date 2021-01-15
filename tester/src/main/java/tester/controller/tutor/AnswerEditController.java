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

@WebServlet(urlPatterns="/tutor/home/test/answer-edit")
public class AnswerEditController extends AbstractController {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LogService.info(this,"AnswerEditController GET");
		req.setAttribute("answerName", req.getParameter("answerName"));
		req.setAttribute("id", req.getParameter("id"));
		req.setAttribute("right", req.getParameter("right"));
		req.setAttribute("testName", req.getParameter("testName"));
		req.setAttribute("idTest", req.getParameter("idTest"));
		forwardToPage("/WEB-INF/view/tutor/editAnswer.jsp", "/WEB-INF/view/shell/tutorShell.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LogService.info(this,"AnswerEditController POST");
       String id=req.getParameter("id");
       String answerName=req.getParameter("answerName");
       String right=req.getParameter("right");
       Boolean r=null;
       if(right==null){
    	   
    	   r=false;
       }else{
    	  
    	   r=true;
       }
       try {
		getTutorService().editAnswer(r, answerName,Long.decode(id));
	} catch (EditException e) {
		forwardToPage("/WEB-INF/view/tutor/editQuestionError.jsp", "/WEB-INF/view/shell/tutorShell.jsp", req, resp);
	}
       req.setAttribute("testName", req.getParameter("testName"));
		req.setAttribute("idTest",req.getParameter("idTest"));
		req.setAttribute(Constants.TUTOR_SUCCESS, "Answer edited!");
		req.getRequestDispatcher("/tutor/home/test").forward(req, resp);
		
		
	}


}
