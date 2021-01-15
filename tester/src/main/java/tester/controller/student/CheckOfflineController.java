package tester.controller.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tester.Constants;
import tester.controller.AbstractController;
import tester.exception.ConvertException;
import tester.exception.GetTestException;
import tester.model.*;
import tester.service.LogService;

@WebServlet(urlPatterns="/student/home/proceed/offline-test/check")
public class CheckOfflineController extends AbstractController {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LogService.info(this, "CheckOfflineController POST");
			List<QuestionModel> byUser=getQMFR(req,req.getParameter("idTest"));
			List<QuestionModel> r;
			List<QuestionModel> b;
		   try {
			List<QuestionModel> qm=getTutorService().getQuestionById(Long.decode(req.getParameter("idTest")), true);
			r=getRightQuestions(qm,byUser);
			b=getBadQuestions(qm,byUser);
			Double k= (double)(100/qm.size())*r.size();
			
			req.setAttribute("idTest", req.getParameter("idTest"));
			AccountModel accountModel=(AccountModel) req.getSession().getAttribute(Constants.CURRENT_ACCOUNT);
			req.setAttribute("UserName", accountModel.getFirstName()+" "+accountModel.getLastName());
			TestAndAuthorModel tAM=getStudentService().getTestAndAuthorModel(Long.decode(req.getParameter("idTest")));
			req.setAttribute("testName", tAM.getTestModel().getName());
			req.setAttribute("quantityQuestion", tAM.getQuantityQuestion());
			req.setAttribute("quantityR", r.size());
			req.setAttribute("quantityB", b.size());
			req.setAttribute("percent", Double.toString(k)+"%");
			req.setAttribute("rightTest", r);
			req.setAttribute("badTest", b);
			forwardToPage("/WEB-INF/view/student/testResult.jsp", "/WEB-INF/view/shell/tutorShell.jsp", req, resp);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (GetTestException e) {
			e.printStackTrace();
		}
	}
	
	private List<QuestionModel> getQMFR(HttpServletRequest req,String idTest){
		List<QuestionModel> userQuestionModel=new ArrayList <QuestionModel>();
		QuestionModel questionModel=null;
		AnswerModel answerModel=null;
		List<AnswerModel> answerList=null;
		List<QuestionModel> listQuestionModel=null;
		Long idTest1=Long.decode(idTest);
		try {
			listQuestionModel=getTutorService().getQuestionById(idTest1, true);
		} catch (GetTestException e) {
			
		}
		
		for(int i=0;i<listQuestionModel.size();i++){
			questionModel=new QuestionModel();
			answerList=new ArrayList();
			for(int j=0;j<listQuestionModel.get(i).getAnswerList().size();j++){
				answerModel=new AnswerModel();
				answerModel.setAnswerName(listQuestionModel.get(i).getAnswerList().get(j).getAnswerName());
				answerModel.setId(listQuestionModel.get(i).getAnswerList().get(j).getId());
				answerModel.setQuestionId(listQuestionModel.get(i).getAnswerList().get(j).getQuestionId());
				
				if(req.getParameter("Box"+listQuestionModel.get(i).getAnswerList().get(j).getId())!=null){
					answerModel.setRight(true);
				}else{
					answerModel.setRight(false);
				}
				answerList.add(answerModel);
				
			}
			questionModel.setAnswerList(answerList);
			questionModel.setId(listQuestionModel.get(i).getId());
			questionModel.setQuestionName(listQuestionModel.get(i).getQuestionName());
			questionModel.setTestId(listQuestionModel.get(i).getTestId());
			userQuestionModel.add(questionModel);
			
		}
		return userQuestionModel;
	}
	private List<QuestionModel> getRightQuestions(List<QuestionModel> right, List<QuestionModel> user){
		List<QuestionModel> rightQuestions=new ArrayList<QuestionModel>();
		for(int i=0;i<right.size();i++){
			boolean rightq=true;
			for(int j=0;j<right.get(i).getAnswerList().size();j++){
				if(right.get(i).getAnswerList().get(j).getRight()!=user.get(i).getAnswerList().get(j).getRight()){
					rightq=false;
				}
			}
			if(rightq){
				rightQuestions.add(right.get(i));
			}
		}
		return rightQuestions;
	}
	private List<QuestionModel> getBadQuestions(List<QuestionModel> right, List<QuestionModel> user){
		List<QuestionModel> rightQuestions=new ArrayList<QuestionModel>();
		for(int i=0;i<right.size();i++){
			boolean rightq=true;
			for(int j=0;j<right.get(i).getAnswerList().size();j++){
				if(right.get(i).getAnswerList().get(j).getRight()!=user.get(i).getAnswerList().get(j).getRight()){
					rightq=false;
				}
			}
			if(!rightq){
				rightQuestions.add(right.get(i));
			}
		}
		return rightQuestions;
	}
	

}
