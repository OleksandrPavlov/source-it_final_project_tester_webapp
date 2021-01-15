package tester.controller.admin;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tester.Constants;
import tester.controller.AbstractController;
import tester.exception.DeleteAccountException;
import tester.service.LogService;

@WebServlet(urlPatterns = "/admin/delete")
public class DeleteAccountController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogService.info(this,"DeleteController GET");
        String par = req.getParameter("id");
        Long var = Long.decode(par);
        getAdminService().deleteAccount(var);
        req.setAttribute(Constants.ADMIN_SUCCESS, "Account deleted!");
        req.getRequestDispatcher("/admin/home").forward(req, resp);
    }
}
