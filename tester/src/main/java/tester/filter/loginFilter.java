package tester.filter;

import com.sun.org.apache.bcel.internal.classfile.Constant;
import tester.Constants;
import tester.service.LogService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "loginFilter")
public class loginFilter extends AbstractFilter {

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        LogService.info(this, "loginFilter");
        Object account = req.getSession().getAttribute(Constants.CURRENT_ACCOUNT);
        if (Objects.nonNull(account)) {
            chain.doFilter(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath()+"/login");
        }
    }
}
