package tester.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tester.Constants;
import tester.exception.ConvertException;
import tester.exception.EditException;
import tester.model.AccountModel;
import tester.service.LogService;

@WebServlet(urlPatterns = "/owner-data")
public class OwnerDataServlet extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogService.info(this, "OwnerDataServlet GET");
        AccountModel accountModel = (AccountModel) req.getSession().getAttribute(Constants.CURRENT_ACCOUNT);
        req.setAttribute("ACCOUNT", accountModel);
        forwardToPage("/WEB-INF/view/owner-data.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogService.info(this, "OwnerDataServlet POST");
        AccountModel accountModel;
        AccountModel currentAccountModel;
        currentAccountModel = (AccountModel) req.getSession().getAttribute(Constants.CURRENT_ACCOUNT);
        accountModel = new AccountModel();
        accountModel.setEmail(req.getParameter("email"));
        accountModel.setFirstName(req.getParameter("name"));
        accountModel.setLastName(req.getParameter("surname"));
        accountModel.setMiddleName(req.getParameter("middlename"));
        accountModel.setPassword(req.getParameter("password"));
        accountModel.setActive(currentAccountModel.getActive());
        accountModel.setLogin(currentAccountModel.getLogin());
        List<String> roleList = new ArrayList<String>();
        if (req.getParameter("administrator") != null) {
            roleList.add("administrator");
        }
        if (req.getParameter("tutor") != null) {
            roleList.add("tutor");
        }
        if (req.getParameter("advancedTutor") != null) {
            roleList.add("advancedTutor");
        }
        if (req.getParameter("student") != null) {
            roleList.add("student");
        }
        accountModel.setRole(roleList);
        accountModel.setId(currentAccountModel.getId());

        try {
            getAdminService().editAccount(accountModel);
            req.getSession().removeAttribute(Constants.CURRENT_ACCOUNT);
            req.getSession().setAttribute(Constants.CURRENT_ACCOUNT, accountModel);
            req.setAttribute(Constants.ADMIN_SUCCESS, "Fields successfully edited!");
            accountModel = (AccountModel) req.getSession().getAttribute(Constants.CURRENT_ACCOUNT);
            req.setAttribute("ACCOUNT", accountModel);
            forwardToPage("/WEB-INF/view/owner-data.jsp", req, resp);
        } catch (EditException e) {
            req.setAttribute(Constants.ADMIN_ERROR, e.getMessage());
            accountModel = (AccountModel) req.getSession().getAttribute(Constants.CURRENT_ACCOUNT);
            req.setAttribute("ACCOUNT", accountModel);
            forwardToPage("/WEB-INF/view/owner-data.jsp", req, resp);
        }

    }

}
