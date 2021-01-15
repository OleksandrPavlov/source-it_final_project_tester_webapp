package tester.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tester.Constants;
import tester.service.LogService;

@WebFilter(filterName = "rightRoleAccessFilter")
public class RightRoleAccessFilter extends AbstractFilter {

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        LogService.info(this, "RightRoleAccessFilter");
        String uri = req.getRequestURI();
        String stringToCompare = null;
        if (uri.contains("admin")) {
            stringToCompare = "administrator";
        } else if (uri.contains("advancedTutor")) {
            stringToCompare = "advancedTutor";
        } else if (uri.contains("tutor")) {
            stringToCompare = "tutor";
        } else if (uri.contains("student")) {
            stringToCompare = "student";
        }
        if (req.getSession().getAttribute(Constants.CURRENT_ROLE).equals(stringToCompare)) {
            chain.doFilter(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath()+"/roleDistributor");
        }
    }

}
