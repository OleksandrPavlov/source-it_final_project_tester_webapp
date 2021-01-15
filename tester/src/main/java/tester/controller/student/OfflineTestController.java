package tester.controller.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tester.model.*;
import tester.Constants;
import tester.controller.AbstractController;
import tester.exception.GetTestException;
import tester.service.LogService;

@WebServlet(urlPatterns = "/student/home/proceed/offline-test")
public class OfflineTestController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogService.info(this, "OfflineTestController GET");
        List<QuestionModel> questionModelList = null;
        TestModel testModel = null;
        Long idTest = null;
        idTest = Long.decode(req.getParameter("idTest"));
        req.setAttribute("idTest", idTest);
        try {
            questionModelList = getTutorService().getQuestionById(idTest, true);
            req.setAttribute(Constants.STUDENT_TEST_MODEL, getStudentService().getTestAndAuthorModel(idTest).getTestModel());
            req.setAttribute(Constants.STUDENT_DATA, questionModelList);
            req.getSession().setAttribute(Constants.STUDENT_QUESTION_MODEL_LIST_DATABASE, questionModelList);
            forwardToPage("/WEB-INF/view/student/offlineTest.jsp", "/WEB-INF/view/shell/tutorShell.jsp", req, resp);
        } catch (GetTestException e) {

        }

    }
}
