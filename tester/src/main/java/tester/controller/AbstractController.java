package tester.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tester.Constants;
import tester.constants.NavigationConstants;
import tester.service.AdminService;
import tester.service.CommonService;
import tester.service.ServiceManager;
import tester.service.StudentService;
import tester.service.TutorService;
import tester.service.impl.AdminServiceImpl;
import tester.service.impl.TutorServiceImpl;

public abstract class AbstractController extends HttpServlet {
    private static final long serialVersionUID = -6966521926184112635L;
    // protected WebService webService;
    ServiceManager serviceManager;

    protected void forwardToPage(String pageName, HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setAttribute("currentPage", pageName);
        req.getRequestDispatcher("/WEB-INF/view/shell/adminShell.jsp").forward(req, res);
    }

    protected void forwardToPage(String pageName, String wrapName, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("currentPage", pageName);
        req.getRequestDispatcher(wrapName).forward(req, resp);

    }

    @Override
    public void init() throws ServletException {
        serviceManager = (ServiceManager) getServletConfig().getServletContext().getAttribute("SERVICE_MANAGER");
    }

    public CommonService getCommonService() {
        return serviceManager.getCommonService();
    }

    public AdminService getAdminService() {
        return serviceManager.getAdminService();
    }

    public TutorServiceImpl getTutorService() {
        return serviceManager.getTutorService();
    }

    public StudentService getStudentService() {
        return serviceManager.getStudentService();
    }

    protected Long quantityPage(Long divident, int lIMIT_ROWS_DATABASE) {
        if (divident > lIMIT_ROWS_DATABASE) {
            if ((divident % lIMIT_ROWS_DATABASE) == 0) {
                return divident / lIMIT_ROWS_DATABASE;
            } else {
                return (divident / lIMIT_ROWS_DATABASE) + 1;
            }
        } else {
            return 0L;
        }

    }

    protected Long getNext(String koef, Long quantityPage) {
        Long var = Long.decode(koef);
        if (var == quantityPage) {
            return var;
        }
        return var + 1;

    }

    protected Long getPrev(String koef) {
        Long var = Long.decode(koef);
        if (var == 1) {
            return 1L;
        }
        return var - 1;
    }
}
