package tester.controller;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tester.Constants;
import tester.exception.ValidationException;
import tester.model.AccountModel;
import tester.service.LogService;

@WebServlet(urlPatterns = "/login")
public class LoginController extends AbstractController {
    private static final long serialVersionUID = 635713160155188864L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogService.info(this, "LoginController GET");
        forwardToPage("/WEB-INF/view/login.jsp", "/WEB-INF/view/shell/loginShell.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogService.info(this, "LoginController POST");
        String log = req.getParameter("login");
        String pass = req.getParameter("password");
        try {
            AccountModel accountModel = getCommonService().login(log, pass);
            req.getSession().setAttribute(Constants.CURRENT_ACCOUNT, accountModel);
            req.getSession().setAttribute(Constants.CURRENT_ROLE, req.getParameter("role"));
            resp.sendRedirect(req.getContextPath() + "/roleDistributor");
        } catch (ValidationException e) {
            req.setAttribute(Constants.FILTER_SELECTED_ROLE_ERROR, e.getMessage());
            forwardToPage("/WEB-INF/view/login.jsp", "/WEB-INF/view/shell/loginShell.jsp", req, resp);
        }
    }
}
