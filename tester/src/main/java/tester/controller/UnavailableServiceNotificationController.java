package tester.controller;

import tester.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/unavailableFeature")
public class UnavailableServiceNotificationController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogService.info(this, "UnavailableServiceNotificationServlet GET");
        forwardToPage("/WEB-INF/view/common/unavailableService.jsp", "/WEB-INF/view/shell/loginShell.jsp", req, resp);
    }


}
