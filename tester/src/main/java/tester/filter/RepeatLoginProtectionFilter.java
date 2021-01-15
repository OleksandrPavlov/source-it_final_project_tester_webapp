package tester.filter;

import tester.Constants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName= "repeatLoginProtection")
public class RepeatLoginProtectionFilter extends AbstractFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        Object currentAccount = req.getSession().getAttribute(Constants.CURRENT_ACCOUNT);
        if (Objects.isNull(currentAccount)) {
            chain.doFilter(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/roleDistributor");
        }
    }
}
