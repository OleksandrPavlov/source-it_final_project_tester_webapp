package tester.controller.tutor;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.reflect.generics.reflectiveObjects.LazyReflectiveObjectGenerator;
import tester.Constants;
import tester.controller.AbstractController;
import tester.exception.GetTestException;
import tester.model.QuestionModel;
import tester.service.LogService;

@WebServlet(urlPatterns = "/tutor/home/test")
public class TestController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogService.info(this, "TestController GET");
        String id = req.getParameter("idTest");
        String testName = req.getParameter("testName");
        List<QuestionModel> listQuestionModel;
        Long testId;
        if (Objects.nonNull(id)) {
            testId = Long.decode(id);
            req.setAttribute("testName", testName);
            req.setAttribute("id", testId);
            try {
                listQuestionModel = getTutorService().getQuestionById(testId, true);
                req.setAttribute(Constants.TUTOR_DATA, listQuestionModel);

            } catch (GetTestException e) {
                req.setAttribute(Constants.TUTOR_ERROR, e.getMessage());
            }
            forwardToPage("/WEB-INF/view/tutor/testSelection.jsp", "/WEB-INF/view/shell/tutorShell.jsp", req, resp);
        } else {
            resp.sendRedirect("/tutor/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogService.info(this, "TestController POST");
        String id = req.getParameter("idTest");
        String testName = req.getParameter("testName");
        List<QuestionModel> listQuestionModel = null;
        Long testId = null;
        if (Objects.nonNull(id)) {
            testId = Long.decode(id);
            req.setAttribute("testName", testName);
            req.setAttribute("id", testId);
            try {
                listQuestionModel = getTutorService().getQuestionById(testId, true);
                req.setAttribute(Constants.TUTOR_DATA, listQuestionModel);

            } catch (GetTestException e) {
                req.setAttribute(Constants.TUTOR_ERROR, e.getMessage());
            }
            forwardToPage("/WEB-INF/view/tutor/testSelection.jsp", "/WEB-INF/view/shell/tutorShell.jsp", req, resp);
        } else {
            resp.sendRedirect("/tutor/home");
        }
    }

}
