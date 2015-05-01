package com.o9pathshala.temp;

import java.util.ArrayList;

import com.o9pathshala.dto.QuestionDTO;
import com.o9pathshala.dto.TestDTO;

public class Questions {

	public static TestDTO getQuestions() {
		TestDTO testDTO = new TestDTO();
		QuestionDTO q;
		ArrayList<QuestionDTO> list = new ArrayList<QuestionDTO>();
		q = new QuestionDTO();
		q.setQuestion("WHat is the capital of INDIA ?");
		q.setAnswer("1");
		q.setOption1("Delhi");
		q.setOption2("Delhi1");
		q.setOption3("Delhi2");
		q.setOption4("Delhi3");
		q.setSelectedanswer("");
		list.add(q);
		q = new QuestionDTO();
		q.setQuestion("WHat is the capital of PAK ?");
		q.setAnswer("1,2");
		q.setOption1("Delhi");
		q.setOption2("Islamabad");
		q.setOption3("Delhi2");
		q.setOption4("Delhi3");
		q.setSelectedanswer("");
		list.add(q);
		testDTO.setQuestionList(list);
		testDTO.setNumberOfQuestions(list.size());
		String[] questions = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			questions[i] = "Q" + (i + 1);
		}
		testDTO.setQuestions(questions);
		return testDTO;
	}
}
