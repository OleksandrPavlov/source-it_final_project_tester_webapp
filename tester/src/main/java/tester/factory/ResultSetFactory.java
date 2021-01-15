package tester.factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.ResultSetHandler;
import org.postgresql.core.ResultHandler;

import tester.model.AccountModel;
import tester.model.QuestionModel;
import tester.model.RawQuestionModel;
import tester.model.TestModel;

public final class ResultSetFactory {
	private ResultSetFactory() {
	}

	public static ResultSetHandler<String> RESULT_SET_ROLE_HANDLER = new ResultSetHandler<String>() {

		public String handle(ResultSet rs) throws SQLException {
			String roleList = null;
			Long idRole = null;
			idRole = rs.getLong("id_role");
			if (idRole == 1) {
				roleList = "student";
			}
			if (idRole == 2) {
				roleList = "tutor";
			}
			if (idRole == 3) {
				roleList = "administrator";
			}
			if (idRole == 4) {
				roleList = "advanced tutor";
			}

			return roleList;
		}
	};

	public static ResultSetHandler<AccountModel> RESULT_SET_ACCOUNT_HANDLER = new ResultSetHandler<AccountModel>() {
		public AccountModel handle(ResultSet rs) throws SQLException {
			AccountModel accountModel = new AccountModel();
			accountModel.setId(rs.getLong("id"));
			accountModel.setFirstName(rs.getString("first_name"));
			accountModel.setLastName(rs.getString("last_name"));
			accountModel.setMiddleName(rs.getString("middle_name"));
			accountModel.setLogin(rs.getString("login"));
			accountModel.setPassword(rs.getString("password"));
			accountModel.setEmail(rs.getString("email"));
			accountModel.setCreated(rs.getTimestamp("created"));
			accountModel.setActive(rs.getBoolean("active"));
			return accountModel;
		}
	};
	public static ResultSetHandler<AccountModel> RESULT_SET_ACCOUNT_ROLE_HANDLER = new ResultSetHandler<AccountModel>() {

		public AccountModel handle(ResultSet rs) throws SQLException {

			AccountModel accountModel = new AccountModel();
			accountModel.setId(rs.getLong("id"));
			accountModel.setFirstName(rs.getString("first_name"));
			accountModel.setLastName(rs.getString("last_name"));
			accountModel.setMiddleName(rs.getString("middle_name"));
			accountModel.setLogin(rs.getString("login"));
			accountModel.setPassword(rs.getString("password"));
			accountModel.setEmail(rs.getString("email"));
			accountModel.setCreated(rs.getTimestamp("created"));
			accountModel.setActive(rs.getBoolean("active"));

			// Logic for filling roleList
			List<String> roleList = new ArrayList<String>();
			roleList.add(converterIdIntoString(rs.getLong("id_role")));
			Long tempId = accountModel.getId();

			while (rs.next()) {

				if (tempId == rs.getLong("id")) {
					roleList.add(converterIdIntoString(rs.getLong("id_role")));
				} else {
					rs.previous();
					break;
				}
			}
			accountModel.setRole(roleList);
			return accountModel;
		}

	};

	public static ResultSetHandler<Map<Long, List<String>>> RESULT_SET_ROLES = new ResultSetHandler<Map<Long, List<String>>>() {

		@Override
		public Map<Long, List<String>> handle(ResultSet rs) throws SQLException {
			Map<Long, List<String>> map = new HashMap<>();
			List<String> list = new ArrayList<>();
			Long currentId = null;
			Long previouseId = null;
			boolean beg = true;
			while (rs.next()||rs.isLast()) {
				currentId = rs.getLong("id_account");
				if(beg){
					previouseId=currentId;
				}
				if (currentId == previouseId) {
					list.add(converterIdIntoString(rs.getLong("id_role")));
					previouseId=currentId;
				} else {
					map.put(previouseId, list);
					list = new ArrayList<>();
					list.add(converterIdIntoString(rs.getLong("id_role")));
					previouseId=currentId;
				}
				beg=false;
			}
			map.put(currentId,list);
			return map;
		}

	};

	private static String converterIdIntoString(Long id) {
		if (id == 1) {
			return "student";
		}
		if (id == 2) {
			return "tutor";
		}
		if (id == 3) {
			return "administrator";
		}
		if (id == 4) {
			return "advanced tutor";
		}
		return null;
	}
	
	public static ResultSetHandler<TestModel> RESULT_SET_TEST_HANDLER=new ResultSetHandler<TestModel>(){

		@Override
		public TestModel handle(ResultSet rs) throws SQLException {
			TestModel testModel=new TestModel();
			testModel.setName(rs.getString("name"));
			testModel.setDescription(rs.getString("description"));
			testModel.setDuration(rs.getShort("duration_per_question"));
			testModel.setIdAccount(rs.getLong("id_account"));
			testModel.setId(rs.getLong("id"));
			return testModel;
		}
		
	};
	
	public static ResultSetHandler<RawQuestionModel> RESULT_SET_RAW_QUESTION_MODEL=new ResultSetHandler<RawQuestionModel>(){
		@Override
		public RawQuestionModel handle(ResultSet rs) throws SQLException {
			RawQuestionModel rawQuestionModel=new RawQuestionModel();
			rawQuestionModel.setId(rs.getLong("id"));
			rawQuestionModel.setTestId(rs.getLong("id_test"));
			rawQuestionModel.setQuestionId(rs.getLong("id_question"));
			rawQuestionModel.setName(rs.getString("name"));
			rawQuestionModel.setRight(rs.getBoolean("correct"));
			rawQuestionModel.setAnswerId(rs.getLong("id_answer"));
			rawQuestionModel.setAnswerName(rs.getString("name_answer"));
			return rawQuestionModel;
			
		}
	};


}
