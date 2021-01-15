package tester.controller.tutor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tester.Constants;
import tester.controller.AbstractController;
import tester.service.LogService;

@WebServlet(urlPatterns="/tutor/home/test/question-edit/change-duration")
public class ChangeDuration extends AbstractController {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LogService.info(this,"ChangeDuration GET");
		Long idTest = Long.decode(req.getParameter("idTest"));
	Short currentDuration=getTutorService().getDuration(idTest);
	req.setAttribute("currentDuration", currentDuration);
	req.setAttribute("idTest", idTest);
	forwardToPage("/WEB-INF/view/tutor/editDuration.jsp", "/WEB-INF/view/shell/tutorShell.jsp", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LogService.info(this,"ChangeDuration POST");
		Short c;
		try{c=Short.decode(req.getParameter("currentDuration"));}catch(NumberFormatException e){
			c=1001;
		}
		
		Long idTest=Long.decode(req.getParameter("idTest"));
		req.setAttribute("idTest", idTest);
		if(c>=30&&c<1000){
			getTutorService().setDuration(idTest, c);
			req.setAttribute("currentDuration",c);
			
			forwardToPage("/WEB-INF/view/tutor/editDuration.jsp", "/WEB-INF/view/shell/tutorShell.jsp", req, resp);
			req.setAttribute(Constants.TUTOR_SUCCESS, "Duration has been changed");
		}else{
			req.setAttribute("currentDuration",getTutorService().getDuration(idTest));
			req.setAttribute(Constants.TUTOR_ERROR, "You entered uncorrect number!");
			forwardToPage("/WEB-INF/view/tutor/editDuration.jsp", "/WEB-INF/view/shell/tutorShell.jsp", req, resp);
		}
	}

}
