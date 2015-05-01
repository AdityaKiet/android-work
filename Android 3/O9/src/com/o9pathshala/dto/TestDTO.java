package com.o9pathshala.dto;

import java.util.ArrayList;
import java.util.Arrays;

public class TestDTO {
	private ArrayList<QuestionDTO> questionList;
	private String[] questions;
	private Integer numberOfQuestions;

	public ArrayList<QuestionDTO> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(ArrayList<QuestionDTO> questionList) {
		this.questionList = questionList;
	}

	public String[] getQuestions() {
		return questions;
	}

	public void setQuestions(String[] questions) {
		this.questions = questions;
	}

	public Integer getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(Integer numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	@Override
	public String toString() {
		return "TestDTO [questionList=" + questionList + ", questions="
				+ Arrays.toString(questions) + ", numberOfQuestions="
				+ numberOfQuestions + "]";
	}

}
