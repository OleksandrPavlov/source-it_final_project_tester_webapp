package tester.controller.tutor;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tester.model.AnswerModel;
import tester.Constants;
import tester.controller.AbstractController;
import tester.service.LogService;

@WebServlet(urlPatterns="/tutor/home/test/add-question")
public class AddQuestionController extends AbstractController {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LogService.info(this,"AddQuestionController GET");
		String answerCounter=req.getParameter("answerCounter");
		String s=req.getParameter("idTest");
		Long ac=null;
		if(answerCounter==null ){
			ac=1L;
		}else if(Long.decode(answerCounter)<=0){
			ac=1L;
		}else{
			ac=Long.decode(answerCounter)+1;
		}
		req.setAttribute("answerCounter", ac);
		req.setAttribute("idTest", req.getParameter("idTest"));
		req.setAttribute("testName", req.getParameter("testName"));
		forwardToPage("/WEB-INF/view/tutor/addQuestion.jsp", "/WEB-INF/view/shell/tutorShell.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LogService.info(this,"AddQuestionController POST");
		List<AnswerModel> answerList=null;
		String s=req.getParameter("idTest");
		answerList=getTutorService().getAnswerModelFromServletRequest(req);
		getTutorService().addQuestion(answerList, req.getParameter("questionName"), Long.decode(req.getParameter("idTest")));
		req.setAttribute("idTest", req.getParameter("idTest"));
		req.setAttribute("testName", req.getParameter("testName"));
req.setAttribute(Constants.TUTOR_SUCCESS, "Question added");
req.getRequestDispatcher("/tutor/home/test").forward(req, resp);
		
		
	}
	

}
