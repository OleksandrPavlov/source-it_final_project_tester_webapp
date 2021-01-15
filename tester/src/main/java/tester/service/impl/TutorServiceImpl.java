package tester.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import tester.converter.ConverterMultiSelectedTables;
import tester.converter.UneversalConverter;
import tester.dao.AccountDao;
import tester.dao.TutorDao;
import tester.exception.ConvertException;
import tester.exception.EditException;
import tester.exception.GetTestException;
import tester.factory.ConnectionUtils;
import tester.model.AnswerModel;
import tester.model.QuestionModel;
import tester.model.RawQuestionModel;
import tester.model.TestModel;
import tester.service.TutorService;

public class TutorServiceImpl implements TutorService {

	private TutorDao tutorDao;
	private DataSource dataSource;

	public TutorServiceImpl(TutorDao tutorDao, DataSource dataSource) {
		this.tutorDao = tutorDao;
		this.dataSource = dataSource;

	}

	@Override
	public List<TestModel> getAllTestById(Long id, int limit, int offset) throws GetTestException {
		Connection con = null;
		List<TestModel> testList = null;

		try {
			con = dataSource.getConnection();
			ConnectionUtils.setConnection(con);
			testList = tutorDao.getAllTestById(id, limit, offset);
			if (testList == null) {
				throw new GetTestException("You have not any tests yet!");
			}

		} catch (SQLException e) {

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ConnectionUtils.clearCurrentConnection();
		}
		return testList;
	}

	@Override
	public Long testQuantity(Long id) {
		Connection con = null;
		Long quant = null;
		try {
			con = dataSource.getConnection();
			ConnectionUtils.setConnection(con);
			quant = tutorDao.testQuantity(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionUtils.clearCurrentConnection();
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return quant;
	}

	@Override
	public List<QuestionModel> getQuestionById(Long id, boolean allOrOne) throws GetTestException {
		Connection con = null;
		List<QuestionModel> questionList = null;
		try {
			con = dataSource.getConnection();

			ConnectionUtils.setConnection(con);
			questionList = tutorDao.getQuestionById(id, allOrOne);
			if (questionList == null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ConnectionUtils.clearCurrentConnection();
				throw new GetTestException("No one question belongs to this test!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ConnectionUtils.clearCurrentConnection();
		}

		return questionList;
	}

	@Override
	public void deleteAnswerById(Long id) {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			ConnectionUtils.setConnection(con);
			tutorDao.deleteAnswerById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ConnectionUtils.clearCurrentConnection();
		}
	}

	@Override
	public void editQuestionNameById(Long id, String name) throws EditException {
		Connection con=null;
		try {
			con=dataSource.getConnection();
			ConnectionUtils.setConnection(con);
			boolean r=tutorDao.editQuestionNameById(id, name);
			if(!r){
				throw new EditException("Question has not been edited");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionUtils.clearCurrentConnection();
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void editAnswer(Boolean right, String name, Long id) throws EditException {
		Connection con=null;
		try {
			con=dataSource.getConnection();
			ConnectionUtils.setConnection(con);
		    boolean ok=tutorDao.editAnswer(right, name, id);
		    if(!ok){
		    	throw new EditException("DataBase editAnswer ERROR please reload youe page");
		    }
		} catch (SQLException e) {
			throw new EditException("DataBase editAnswer ERROR please reload youe page");
		}finally{
			ConnectionUtils.clearCurrentConnection();
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void addAnswer(Boolean right, String name, Long id) throws EditException {
		Connection con=null;
		try {
			con=dataSource.getConnection();
			ConnectionUtils.setConnection(con);
			boolean ok=tutorDao.addAnswer(right, name, id);
			if(!ok){
				throw new EditException("DATA BASE ERROR DURING ADDING ANSWER!");
			}
		} catch (SQLException e) {
			
		}finally{
			ConnectionUtils.clearCurrentConnection();
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public List<AnswerModel> getAnswerModelFromServletRequest(HttpServletRequest req){
		String answerCounter=req.getParameter("answerCounter");
		Long ac=Long.decode(answerCounter);
		List<AnswerModel> answerList=null;
		UneversalConverter<List<AnswerModel>,Object> uc=new UneversalConverter<List<AnswerModel>,Object>(){

			@Override
			public List<AnswerModel> convertThis(HttpServletRequest req) throws ConvertException {
				String answerCounter=req.getParameter("answerCounter");
				Long ac=Long.decode(answerCounter);
				List<AnswerModel> answerList=new ArrayList<AnswerModel>();
				for(int i=1;i<=ac;i++){
					AnswerModel am=new AnswerModel();
					am.setAnswerName(req.getParameter("answerName"+i));
					String right=req.getParameter("right"+i);
					Boolean r=null;
					if(right==null){
						r=false;
					}else{
						r=true;
					}
					am.setRight(r);
					
					answerList.add(am);
				}
				return answerList;
			}

			
			
		};
		try {
			answerList=uc.convertThis(req);
		} catch (ConvertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return answerList;
	}

	@Override
	public boolean addQuestion(List<AnswerModel> answerName, String questionName, Long testId) {
		Connection con=null;
		try {
			con=dataSource.getConnection();
			ConnectionUtils.setConnection(con);
			tutorDao.addQuestion(answerName, questionName, testId);
		} catch (SQLException e) {
			
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
		
		
		return false;
	}

	@Override
	public Short getDuration(Long idTest) {
		Connection con=null;
		Short c=null;
		try {
			con=dataSource.getConnection();
			ConnectionUtils.setConnection(con);
			c=tutorDao.getDuration(idTest);
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
		
		
		return c;
	}

	@Override
	public void setDuration(Long idTest, Short duration) {
		Connection con=null;
		try {
			con=dataSource.getConnection();
			ConnectionUtils.setConnection(con);
			tutorDao.setDuration(idTest, duration);
			
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
		
		
	}

}
