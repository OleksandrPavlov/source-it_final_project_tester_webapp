package tester.controller.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tester.controller.AbstractController;
import tester.service.LogService;

@WebServlet(urlPatterns="/student/home/proceed/mode-distributor")
public class TestingModeDistributor extends AbstractController {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LogService.info(this,"TestingModeDistributor GET");
		if(req.getParameter("mode").equals("offline")){
			req.setAttribute("idTest", req.getParameter("idTest"));
			req.getRequestDispatcher("/student/home/proceed/offline-test").forward(req, resp);
		}else{
			req.getRequestDispatcher("/student/home/proceed/online-test").forward(req,resp);
		}
	}

}
