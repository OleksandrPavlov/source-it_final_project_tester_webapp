package tester.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tester.Constants;
import tester.constants.NavigationConstants;
import tester.controller.AbstractController;
import tester.exception.GetUserFromDataBaseException;
import tester.model.AccountModel;
import tester.service.LogService;

@WebServlet(urlPatterns = "/admin/home")
public class AdminHomeController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogService.info(this, "AdminHomeController GET");
            List<AccountModel> accountList = null;
        Long quantityRows = getAdminService().rowQuantity();
        Long quantityPage = quantityPage(quantityRows, Constants.LIMIT_ROWS_DATABASE);
        Integer currentPage = null;
        Long prev;
        Long next;
        req.setAttribute("QUANTITY_PAGE", quantityPage);


        if (Objects.isNull(req.getParameter("koef"))) {
            next = 2L;
            prev = 1L;
            currentPage = 1;

        } else {
            next = getNext(req.getParameter("koef"), quantityPage);
            prev = getPrev(req.getParameter("koef"));
            currentPage = Integer.decode(req.getParameter("koef"));
        }

        try {
            accountList = getAdminService().getUsersFromDB(Constants.OFFSET_DATABASE * (currentPage - 1), Constants.LIMIT_ROWS_DATABASE,
                    "administrator", Constants.PARAM_SORT_BY_ID);
        } catch (GetUserFromDataBaseException e) {
            req.setAttribute(Constants.ADMIN_ERROR, e.getMessage());
        }
        req.setAttribute("CURRENT_PAGE", currentPage);
        req.setAttribute("PREVIOUSE", prev);
        req.setAttribute("NEXT", next);
        req.setAttribute(Constants.ADMIN_DATA, accountList);
        forwardToPage("/WEB-INF/view/admin/adminHome.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogService.info(this, "HomeController POST");
        List<AccountModel> accountList = null;
        Long quantityRows = getAdminService().rowQuantity();
        Long quantityPage = quantityPage(quantityRows, Constants.LIMIT_ROWS_DATABASE);
        Integer currentPage = null;
        Long prev = null;
        Long next = null;
        req.setAttribute("QUANTITY_PAGE", quantityPage);
        if (quantityPage > 0) {
            if (Objects.isNull(req.getParameter("koef"))) {
                next = 2L;
                prev = 1L;
                currentPage = 1;
            } else {
                next = getNext(req.getParameter("koef"), quantityPage);
                prev = getPrev(req.getParameter("koef"));
                currentPage = Integer.decode(req.getParameter("koef"));
            }
            try {
                accountList = getAdminService().getUsersFromDB(Constants.OFFSET_DATABASE * (currentPage - 1), Constants.LIMIT_ROWS_DATABASE,
                        "administrator", Constants.PARAM_SORT_BY_ID);
            } catch (GetUserFromDataBaseException e) {
                req.setAttribute(Constants.ADMIN_ERROR, e.getMessage());
            }
            req.setAttribute("CURRENT_PAGE", currentPage);
            req.setAttribute("PREVIOUSE", prev);
            req.setAttribute("NEXT", next);
            req.setAttribute(Constants.ADMIN_DATA, accountList);
        } else {
            req.setAttribute(Constants.ADMIN_ERROR, "There no one user yet");
        }
        forwardToPage("/WEB-INF/view/admin/adminHome.jsp", req, resp);
    }


}
