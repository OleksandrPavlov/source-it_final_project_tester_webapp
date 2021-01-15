package tester.controller.admin;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tester.Constants;
import tester.constants.NavigationConstants;
import tester.controller.AbstractController;
import tester.exception.ConvertException;
import tester.exception.EditException;
import tester.exception.ValidationException;
import tester.model.AccountModel;
import tester.service.LogService;

@WebServlet(urlPatterns = "/admin/edit")

public class EditAccountController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogService.info(this,"EditController GET");
        String par = req.getParameter("id");
        Long var;
        if (par != null) {
            var = Long.decode(par);
            AccountModel accountModel = null;
            try {
                accountModel = getAdminService().findById(var);
                req.getSession().setAttribute("SPECIAL_MODEL", accountModel);
                accountModel.setId(var);
            } catch (ValidationException e) {

            }

            req.setAttribute(Constants.ACCOUNT, accountModel);
            forwardToPage("/WEB-INF/view/admin/editAccount.jsp", req, resp);
        } else {
            resp.sendRedirect("/admin/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogService.info(this,"EditController POST");
        AccountModel accountModel = null;
        try {
            accountModel = (AccountModel) getAdminService().getConverter().convertThis(req);
            String par = req.getParameter("hiddenid");
            Long var = Long.decode(par);
            accountModel.setId(var);
            getAdminService().editAccount(accountModel);
            req.setAttribute(Constants.ADMIN_SUCCESS, "Account has been edited!");
            req.getRequestDispatcher("/admin/home").forward(req, resp);
        } catch (ConvertException | EditException e) {
            req.setAttribute(Constants.ACCOUNT, req.getSession().getAttribute("SPECIAL_MODEL"));
            req.setAttribute(Constants.ADMIN_ERROR, "User under this Login or Email already exist");
            req.setAttribute("id", accountModel.getId());
            forwardToPage("/WEB-INF/view/admin/editAccount.jsp", req, resp);
        }
    }
}
