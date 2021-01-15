package tester.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import tester.Constants;
import tester.converter.UneversalConverter;
import tester.dao.AccountDao;
import tester.dao.StudentDao;
import tester.exception.ConvertException;
import tester.exception.GetTestException;
import tester.factory.ConnectionUtils;
import tester.model.AccountModel;
import tester.model.QuestionModel;
import tester.model.SpecialQuestionModel;
import tester.model.TestAndAuthorModel;
import tester.model.TestModel;

public class StudentServiceImpl implements tester.service.StudentService {

	private UneversalConverter<List<SpecialQuestionModel>, HttpServletRequest> fromHttpRquestToSpecialQuestionModel = new UneversalConverter<List<SpecialQuestionModel>, HttpServletRequest>() {

		@Override
		public List<SpecialQuestionModel> convertThis(HttpServletRequest req) throws ConvertException {
			 List<QuestionModel> questionModel=(List<QuestionModel>) req.getSession().getAttribute(Constants.STUDENT_QUESTION_MODEL_LIST_DATABASE);
	      int a=4;
	      int b=a+2;
			return null;
		}
		
	};
	
	
	
	private StudentDao studentDao;
	private DataSource dataSource;
	public StudentServiceImpl(StudentDao studentDao, DataSource dataSource) {
		this.dataSource = dataSource;
		this.studentDao = studentDao;
	}
	
	
	@Override
	public List<TestModel> getAllTests() throws GetTestException {
	Connection con=null;
	List<TestModel> testModelList=null;
	try {
		con=dataSource.getConnection();
		ConnectionUtils.setConnection(con);
		testModelList=studentDao.getAllTests();
		if(testModelList==null){
			throw new GetTestException("No one test in the data base yet!");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionUtils.clearCurrentConnection();
	}
	
		return testModelList;
	}


	@Override
	public TestAndAuthorModel getTestAndAuthorModel(Long idTest) throws GetTestException {
		Connection con=null;
		TestAndAuthorModel tm=new TestAndAuthorModel();
		try {
			con=dataSource.getConnection();
			ConnectionUtils.setConnection(con);
			tm.setTestModel(studentDao.getTestModelById(idTest));
			if(tm.getTestModel()==null){
				throw new GetTestException("No one test by this id!");
			}
			tm.setAuthor(studentDao.getAuthorById(tm.getTestModel().getIdAccount()));
			tm.setQuantityQuestion(studentDao.getQuestionQuantity(idTest));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ConnectionUtils.clearCurrentConnection();
		}
		return tm;
	}


	@Override
	public UneversalConverter getQuestionConverter() {
		
		return fromHttpRquestToSpecialQuestionModel;
	}

}
