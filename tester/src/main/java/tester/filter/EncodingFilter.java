package tester.filter;

import tester.service.LogService;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName="encodingFilter")
public class EncodingFilter extends AbstractFilter {

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		LogService.info(this, "EncodingFilter");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		chain.doFilter(req, resp);
	}

}
