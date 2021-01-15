package tester.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import tester.Constants;
import tester.constants.NavigationConstants;
import tester.controller.AbstractController;
import tester.exception.ConvertException;
import tester.exception.ValidationException;
import tester.model.AccountModel;

@WebServlet(urlPatterns = "/admin/add")
public class CreateAccountController extends AbstractController {
    public static final Logger LOG = Logger.getLogger(CreateAccountController.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("AddUserController GET");
        forwardToPage("/WEB-INF/view/admin/addAccount.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("AddUserController POST");
        AccountModel accountModel;
        try {
            try {
                accountModel = (AccountModel) getAdminService().getConverter().convertThis(req);
                getAdminService().insertNewAccount(accountModel);
            } catch (ValidationException e) {
                req.setAttribute(Constants.ADMIN_ERROR, e.getMessage());
                forwardToPage("/WEB-INF/view/admin/addAccount.jsp", req, resp);
            }
            req.setAttribute(Constants.ADMIN_SUCCESS, "Account has been added!");
            forwardToPage("/WEB-INF/view/admin/addAccount.jsp", req, resp);
        } catch (ConvertException e) {
            req.setAttribute(Constants.ADMIN_ERROR, "You missed fields!");
            forwardToPage("/WEB-INF/view/admin/addAccount.jsp", req, resp);
        }
    }
}
