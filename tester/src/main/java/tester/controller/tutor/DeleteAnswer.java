package tester.controller.tutor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tester.Constants;
import tester.controller.AbstractController;
import tester.service.LogService;

@WebServlet(urlPatterns = "/tutor/home/test/question-edit/delete-answer")
public class DeleteAnswer extends AbstractController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LogService.info(this,"DeleteAnswer POST");
        String lost = req.getParameter("lost");
        req.setAttribute("idTest", req.getParameter("idTest"));
        req.setAttribute("testName", req.getParameter("testName"));
        if (Long.decode(lost) == 1) {
            req.setAttribute(Constants.TUTOR_ERROR, "Should be one answer at least!");
            req.getRequestDispatcher("/tutor/home/test").forward(req, resp);

        } else {
            getTutorService().deleteAnswerById(Long.decode(req.getParameter("id")));
            req.setAttribute(Constants.TUTOR_SUCCESS, "Answer deleted!");
            req.getRequestDispatcher("/tutor/home/test").forward(req, resp);
        }
    }
}
