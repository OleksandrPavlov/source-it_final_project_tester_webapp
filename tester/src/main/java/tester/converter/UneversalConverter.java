package tester.converter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import tester.exception.ConvertException;
import tester.model.QuestionModel;
import tester.model.RawQuestionModel;

public interface UneversalConverter<T,U> {
public T convertThis(HttpServletRequest req)throws ConvertException;

}
