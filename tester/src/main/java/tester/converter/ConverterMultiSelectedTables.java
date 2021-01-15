package tester.converter;
import java.util.List;

import tester.model.QuestionModel;
import tester.model.RawQuestionModel;
public interface ConverterMultiSelectedTables {
	public List<QuestionModel> convertQuestionModel(List<RawQuestionModel> listRawQuestionModel);
}
