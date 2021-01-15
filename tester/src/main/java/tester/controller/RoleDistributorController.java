package tester.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tester.Constants;
import tester.service.LogService;

@WebServlet(urlPatterns = "/roleDistributor")
public class RoleDistributorController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogService.info(this, "DistributorController GET");
        switch ((String) req.getSession().getAttribute(Constants.CURRENT_ROLE)) {
            case "tutor":
                resp.sendRedirect(req.getContextPath() + "/tutor/home");
                break;
            case "advanced tutor":
                resp.sendRedirect(req.getContextPath() + "/advancedTutor/home");
                break;
            case "administrator":
                resp.sendRedirect(req.getContextPath() + "/admin/home");
                break;
            case "student":
                resp.sendRedirect(req.getContextPath() + "/student/home");
                break;
        }

    }
}
