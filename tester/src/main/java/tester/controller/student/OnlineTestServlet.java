package tester.controller.student;

import tester.controller.AbstractController;
import tester.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/student/home/proceed/online-test")
public class OnlineTestServlet extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogService.info(this,"OnlineTestServlet GET");
        forwardToPage("/WEB-INF/view/common/unavailableService.jsp", "/WEB-INF/view/shell/studentShell.jsp", req, resp);
    }
}
