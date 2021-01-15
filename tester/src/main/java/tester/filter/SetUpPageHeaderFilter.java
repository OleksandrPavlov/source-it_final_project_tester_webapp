package tester.filter;

import tester.constants.NavigationConstants;
import tester.service.LogService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "pageHeaderFilter")
public class SetUpPageHeaderFilter extends AbstractFilter {
    private Map<String, String> pageHeadersOnURI = new HashMap<>();

    public SetUpPageHeaderFilter() {
        pageHeadersOnURI.put("/admin/home", NavigationConstants.ACCOUNT_LIST);
        pageHeadersOnURI.put("/admin/add", NavigationConstants.ADDING_ACCOUNT);
        pageHeadersOnURI.put("/admin/edit", NavigationConstants.EDIT_ACCOUNT);
        pageHeadersOnURI.put("/tutor/home", NavigationConstants.TEST_LIST);
        pageHeadersOnURI.put("/tutor/home/test/add-answer", NavigationConstants.ADD_ANSWER);
        pageHeadersOnURI.put("/tutor/home/test-add", NavigationConstants.ADD_TEST);
        pageHeadersOnURI.put("/tutor/home/test/add-question", NavigationConstants.ADD_QUESTION);
        pageHeadersOnURI.put("/tutor/home/test/answer-edit", NavigationConstants.EDIT_ANSWER);
        pageHeadersOnURI.put("/tutor/home/test/question-edit/change-duration", NavigationConstants.EDIT_DURATION);
        pageHeadersOnURI.put("/tutor/home/test", NavigationConstants.TEST);
        pageHeadersOnURI.put("/tutor/home/test/question-edit", NavigationConstants.EDIT_QUESTION);
        pageHeadersOnURI.put("/tutor/test/rename", NavigationConstants.RENAME_TEST);
        pageHeadersOnURI.put("/student/home", NavigationConstants.TEST_LIST);
        pageHeadersOnURI.put("/student/home/proceed/mode-distributor", NavigationConstants.TEST_EXECUTING);
        pageHeadersOnURI.put("/student/home/proceed", NavigationConstants.STUDENT_TEST_INFO);
        pageHeadersOnURI.put("/student/home/proceed/offline-test/check", NavigationConstants.TEST_RESULT);
    }

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        LogService.info(this, "SetUpPageHeaderFilter");
        req.setAttribute(NavigationConstants.PAGE_HEADER, pickAppropriateHeaderBasedOnURI(req.getRequestURI()));
        chain.doFilter(req, resp);
    }

    public String pickAppropriateHeaderBasedOnURI(String URI) {
        for (Map.Entry<String, String> entry : pageHeadersOnURI.entrySet()) {
            if (URI.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "Unknown";
    }
}
