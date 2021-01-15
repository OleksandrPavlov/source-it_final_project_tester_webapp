package tester.controller.admin;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tester.controller.AbstractController;
import tester.service.LogService;

@WebServlet(urlPatterns = "/admin/enable")
public class EnableAccountController extends AbstractController {
    public static final Logger LOG = Logger.getLogger(EnableAccountController.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogService.info(this, "EnableController GET");
        String par = req.getParameter("id");
        Long var = Long.decode(par);
        if (req.getParameter("currentActivity").equals("true")) {
            getAdminService().EnableDisable(var, "false");
        } else {
            getAdminService().EnableDisable(var, "true");
        }
        resp.sendRedirect(req.getContextPath() + "/admin/home");
    }
}
