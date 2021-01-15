package tester.controller.tutor;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tester.Constants;
import tester.controller.AbstractController;
import tester.exception.EditException;
import tester.exception.GetTestException;
import tester.model.AnswerModel;
import tester.model.QuestionModel;
import tester.service.LogService;

@WebServlet(urlPatterns = "/tutor/home/test/question-edit")
public class TutorEditQuestion extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogService.info(this, "TutorEditQuestion GET");
        Long testId;
        Long questionId;
        String id = req.getParameter("id");
        req.setAttribute("testName", req.getParameter("testName"));
        List<QuestionModel> questionModel = null;
        QuestionModel questionModel1 = null;
        if (Objects.nonNull(id)) {
            testId = Long.decode(id);
            try {
                questionId = Long.decode(req.getParameter("questionId"));
                questionModel = getTutorService().getQuestionById(testId, true);
                for (QuestionModel questionModelt : questionModel) {
                    if (questionModelt.getId() == questionId) {
                        questionModel1 = questionModelt;
                    }
                }
                req.setAttribute(Constants.TUTOR_DATA, questionModel1);
            } catch (GetTestException e) {

                e.printStackTrace();
            }


        }
        forwardToPage("/WEB-INF/view/tutor/editQuestion.jsp.jsp", "/WEB-INF/view/shell/tutorShell.jsp", req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogService.info(this, "TutorEditQuestion POST");
        String name = req.getParameter("questionName");
        String id = req.getParameter("id");
        String idTest = req.getParameter("idTest");
        String testName = req.getParameter("testName");

        Long qid = Long.decode(id);
        try {
            getTutorService().editQuestionNameById(qid, name);
        } catch (EditException e) {
            req.setAttribute("errorMessage","");
            forwardToPage("/WEB-INF/view/tutor/editQuestionError.jsp", "/WEB-INF/view/shell/tutorShell.jsp", req, resp);
        }
        req.setAttribute("testName", testName);
        req.setAttribute("idTest", idTest);
        req.setAttribute(Constants.TUTOR_SUCCESS, "Question edited!");
        req.getRequestDispatcher("/tutor/home/test").forward(req, resp);


    }


}
