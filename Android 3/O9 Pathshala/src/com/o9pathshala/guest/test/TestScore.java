package com.o9pathshala.guest.test;

import java.util.ArrayList;

import com.o9pathshala.dto.QuestionDTO;

public class TestScore {
	public static int score(ArrayList<QuestionDTO> questionDTOs) {
		int sum = 0;
		for (int i = 0; i < questionDTOs.size(); i++) {
			if (questionDTOs.get(i).getAnswer()
					.equals(questionDTOs.get(i).getSelectedanswer()))
				sum++;
		}
		return sum;
	}
}
