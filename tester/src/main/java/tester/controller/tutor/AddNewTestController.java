package tester.controller.tutor;

import tester.controller.AbstractController;
import tester.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/tutor/home/test-add")
public class AddNewTestController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogService.info(this, "NewTestAdd GET");
        resp.sendRedirect(req.getContextPath() + "/unavailableFeature");
    }
}
