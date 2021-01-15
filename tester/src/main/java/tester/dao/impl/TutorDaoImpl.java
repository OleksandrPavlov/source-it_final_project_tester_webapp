package tester.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import tester.converter.ConverterMultiSelectedTables;
import tester.dao.TutorDao;
import tester.exception.ApplicationException;
import tester.factory.ConnectionUtils;
import tester.factory.ResultSetFactory;
import tester.handler.DefaultListResultSetHandler;
import tester.handler.DefaultOneRowResultSet;
import tester.model.AccountModel;
import tester.model.AnswerModel;
import tester.model.QuestionModel;
import tester.model.RawQuestionModel;
import tester.model.TestModel;

public class TutorDaoImpl implements TutorDao {

	private final QueryRunner queryRunner = new QueryRunner();
	private ConverterMultiSelectedTables converterMultiSelectedTables = new ConverterMultiSelectedTables() {

		@Override
		public List<QuestionModel> convertQuestionModel(List<RawQuestionModel> listRawQuestionModel) {
			if (listRawQuestionModel == null) {
				return null;
			}
			Long currentId = listRawQuestionModel.get(0).getId();
			List<AnswerModel> listAnswerModel = new ArrayList<AnswerModel>();
			List<QuestionModel> listQuestionModel = new ArrayList<QuestionModel>();
			QuestionModel questionModel = new QuestionModel();
			AnswerModel answerModel = null;

			int counter = 1;
			for (RawQuestionModel rawQuestionModel : listRawQuestionModel) {
				if (rawQuestionModel.getId() == currentId) {
					answerModel = new AnswerModel();
					questionModel.setId(currentId);
					questionModel.setQuestionName(rawQuestionModel.getName());
					questionModel.setTestId(rawQuestionModel.getTestId());
					answerModel.setAnswerName(rawQuestionModel.getAnswerName());
					answerModel.setId(rawQuestionModel.getAnswerId());
					answerModel.setQuestionId(rawQuestionModel.getQuestionId());
					answerModel.setRight(rawQuestionModel.getRight());
					listAnswerModel.add(answerModel);
				} else if (rawQuestionModel.getId() != currentId || listRawQuestionModel.size() == counter) {
					currentId = rawQuestionModel.getId();

					questionModel.setAnswerList(listAnswerModel);
					listQuestionModel.add(questionModel);
					questionModel = new QuestionModel();
					listAnswerModel = new ArrayList<AnswerModel>();

					answerModel = new AnswerModel();
					questionModel.setId(currentId);
					questionModel.setQuestionName(rawQuestionModel.getName());
					questionModel.setTestId(rawQuestionModel.getTestId());
					answerModel.setAnswerName(rawQuestionModel.getAnswerName());
					answerModel.setId(rawQuestionModel.getAnswerId());
					answerModel.setQuestionId(rawQuestionModel.getQuestionId());
					answerModel.setRight(rawQuestionModel.getRight());
					listAnswerModel.add(answerModel);
				}
				if (listRawQuestionModel.size() == counter) {
					questionModel.setAnswerList(listAnswerModel);
					listQuestionModel.add(questionModel);
				}
				counter++;
			}
			return listQuestionModel;
		}

	};

	private ResultSetHandler<List<TestModel>> resultSetAllTest = new DefaultListResultSetHandler<TestModel>(
			ResultSetFactory.RESULT_SET_TEST_HANDLER);
	private ResultSetHandler<List<RawQuestionModel>> resultSetRawQuestionModel = new DefaultListResultSetHandler<RawQuestionModel>(
			ResultSetFactory.RESULT_SET_RAW_QUESTION_MODEL);
	private ResultSetHandler<RawQuestionModel> resultSetOneRawQuestionModel = new DefaultOneRowResultSet<RawQuestionModel>(
			ResultSetFactory.RESULT_SET_RAW_QUESTION_MODEL);

	@Override
	public List<TestModel> getAllTestById(Long id, int limit, int offset) {
		Connection con = null;
		List<TestModel> testList = null;
		con = ConnectionUtils.getCurrentConnection();
		String sql = "SELECT * FROM test WHERE id_account=? LIMIT ? OFFSET ?";
		try {
			testList = queryRunner.query(con, sql, resultSetAllTest, id, limit, offset);
		} catch (SQLException e) {
			return null;
		}
		return testList;
	}

	@Override
	public Long testQuantity(Long id) {
		Connection con = ConnectionUtils.getCurrentConnection();
		Long c = null;
		try {
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM test WHERE id_account=" + id + " ");
			if (rs.next()) {
				c = rs.getLong("count");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return c;
	}

	@Override
	public List<QuestionModel> getQuestionById(Long id, boolean allOrOne) {
		Connection con = ConnectionUtils.getCurrentConnection();

		List<QuestionModel> questionList = null;
		List<RawQuestionModel> listRawQuestionModel = null;
		String sql = "SELECT * FROM question AS q, answer AS a WHERE id_test=? AND q.id=a.id_question ORDER BY id";
		// condition to determine how much objects will be returned
		if (allOrOne) {
			try {

				listRawQuestionModel = queryRunner.query(con, sql, resultSetRawQuestionModel, id);
				questionList = converterMultiSelectedTables.convertQuestionModel(listRawQuestionModel);

			} catch (SQLException e) {

				e.printStackTrace();
			}
		} else {

		}
		return questionList;
	}

	@Override
	public void deleteAnswerById(Long id) {
		String sql = "DELETE FROM answer WHERE id_answer=?;";
		Connection con = ConnectionUtils.getCurrentConnection();
		try {
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			prepareStatement.setLong(1, id);
			prepareStatement.execute();
		} catch (SQLException e) {
			throw new ApplicationException();
		}
	}

	@Override
	public boolean editQuestionNameById(Long id, String name) {
		Connection con = ConnectionUtils.getCurrentConnection();

		String sql = "UPDATE question SET name=? WHERE id=?";
		try {
			queryRunner.update(con, sql, name, id);
		} catch (SQLException e) {
			return false;
		}
		return true;

	}

	@Override
	public boolean editAnswer(Boolean right, String name, Long id) {
		Connection con = ConnectionUtils.getCurrentConnection();

		String qrl = "UPDATE answer SET name_answer=?,correct=? WHERE id_answer=?";
		try {
			queryRunner.update(con, qrl, name, right, id);
		} catch (SQLException e) {
			return false;
		}

		return true;
	}

	@Override
	public boolean addAnswer(Boolean right, String name, Long id) {
		Connection con = ConnectionUtils.getCurrentConnection();
		String sql = "INSERT INTO answer (id_answer,id_question,name_answer,correct) VALUES(nextval('answer_seq'),?,?,?)";
		try {
			queryRunner.update(con, sql, id, name, right);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean addQuestion(List<AnswerModel> answerName, String questionName, Long testId) {
		Connection con = ConnectionUtils.getCurrentConnection();
		
		String sql=createSql(answerName,questionName, testId);
		try {
			queryRunner.update(con, sql);
		} catch (SQLException e) {
			return false;
		}
		
		return true;
		
		
	}
	private String createSql(List<AnswerModel> answerName, String questionName,Long testId){
		String sqlAnswer = "INSERT INTO answer (id_answer,id_question,name_answer,correct) VALUE(nextval('answer_seq'),currvar(question_seq),?,?);";
		String sqlQuestion="INSERT INTO question (id,id_test,name) VALUES(nextval('question_seq'),"+testId+",'"+questionName+"');";
		StringBuilder sb=new StringBuilder();
		sb.append(sqlQuestion);
		for(int i=0;i<answerName.size();i++){
			sb.append(" INSERT INTO answer (id_answer,id_question,name_answer,correct) VALUES(nextval('answer_seq'),currval('question_seq'),'"+answerName.get(i).getAnswerName()+"',"+answerName.get(i).getRight()+");");
		}
		return sb.toString();
	}

	@Override
	public Short getDuration(Long idTest) {
		Connection con=ConnectionUtils.getCurrentConnection();
		
		Short c = null;
		try {
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT duration_per_question FROM test WHERE id="+idTest+"");
			if (rs.next()) {
				c = rs.getShort("duration_per_question");
			}
		}catch (SQLException e) {

				e.printStackTrace();
			}
		
			return c;
		
	}

	@Override
	public void setDuration(Long idTest,Short duration) {
		Connection con=ConnectionUtils.getCurrentConnection();
		
		try {
			queryRunner.update(con, "UPDATE test SET duration_per_question=? WHERE id=?", duration,idTest);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
