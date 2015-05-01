package com.o9pathshala.guest.slidingmenu.fragments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.O9.pathshala.R;
import com.o9pathshala.dto.QuestionDTO;
import com.o9pathshala.dto.TestDTO;
import com.o9pathshala.guest.test.TestScore;
import com.o9pathshala.temp.Questions;

public class FragmentTest extends Fragment implements OnClickListener {
	private String[] questions;
	private ListView lv;
	private Integer numberOfQuestions, currentQuestion = 0;
	private LinearLayout linearLayout;
	private ArrayList<QuestionDTO> questionList;
	private Button btnNext, btnPrevious, btnSubmit;
	private TextView txtquestion, txtTimer;
	private CheckBox chkOption1, chkOption2, chkOption3, chkOption4;
	private ArrayAdapter<String> adapter;
	private CountDownTimer countDownTimer;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		TestDTO testDTO = Questions.getQuestions();
		questions = testDTO.getQuestions();
		questionList = testDTO.getQuestionList();
		numberOfQuestions = testDTO.getNumberOfQuestions();
		linearLayout = (LinearLayout) inflater.inflate(R.layout.test,
				container, false);
		countDownTimer = new CountDownTimer(15000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				txtTimer.setText("Seconds : " + millisUntilFinished / 1000);
			}

			@Override
			public void onFinish() {
				txtTimer.setText("Finished");
			}
		};
		countDownTimer.start();
		populate();

		lv.setAdapter(adapter);
		changeQuestion(0);
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				currentQuestion = position;
				changeQuestion(position);

			}
		});
		return linearLayout;
	}

	private void populate() {
		lv = (ListView) linearLayout.findViewById(R.id.lstQuestions);
		txtquestion = (TextView) linearLayout.findViewById(R.id.txtQuestion);
		txtTimer = (TextView) linearLayout.findViewById(R.id.txtTimer);
		btnNext = (Button) linearLayout.findViewById(R.id.btnNext);
		btnPrevious = (Button) linearLayout.findViewById(R.id.btnPrevious);
		btnSubmit = (Button) linearLayout.findViewById(R.id.btnSubmit);
		chkOption1 = (CheckBox) linearLayout.findViewById(R.id.chk1);
		chkOption2 = (CheckBox) linearLayout.findViewById(R.id.chk2);
		chkOption3 = (CheckBox) linearLayout.findViewById(R.id.chk3);
		chkOption4 = (CheckBox) linearLayout.findViewById(R.id.chk4);
		btnNext.setOnClickListener(this);
		btnPrevious.setOnClickListener(this);
		btnSubmit.setOnClickListener(this);
		chkOption1.setOnClickListener(this);
		chkOption2.setOnClickListener(this);
		chkOption3.setOnClickListener(this);
		chkOption4.setOnClickListener(this);
		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, questions);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSubmit:
			int score = TestScore.score(questionList);
			btnSubmit.setText("" + score);			
			getActivity().setContentView(R.layout.test_finish);
			break;
		case R.id.btnNext:
			currentQuestion++;
			if (currentQuestion == numberOfQuestions) {
				currentQuestion--;
			} else {
				changeQuestion(currentQuestion);
			}
			break;
		case R.id.btnPrevious:
			currentQuestion--;
			if (currentQuestion < 0) {
				currentQuestion++;
			} else {
				changeQuestion(currentQuestion);
			}
			break;
		case R.id.chk1:
			updateAnswer(1, chkOption1.isChecked());
			break;
		case R.id.chk2:
			updateAnswer(2, chkOption2.isChecked());
			break;
		case R.id.chk3:
			updateAnswer(3, chkOption3.isChecked());
			break;
		case R.id.chk4:
			updateAnswer(4, chkOption4.isChecked());
			break;
		default:

			break;
		}

	}

	private void updateAnswer(int option, boolean checked) {
		String[] answer = questionList.get(currentQuestion).getSelectedanswer()
				.split(",");
		ArrayList<String> answerList = new ArrayList<String>(
				Arrays.asList(answer));
		if (answerList.contains(String.valueOf(option))) {
			answerList.remove(String.valueOf(option));
		} else {
			answerList.add(String.valueOf(option));
		}
		answerList.remove("");
		Collections.sort(answerList);

		if (answerList.size() == 0) {
			questionList.get(currentQuestion).setSelectedanswer("");
		}
		if (answerList.size() > 0) {
			questionList.get(currentQuestion).setSelectedanswer("");
			String temp = "";
			for (String string : answerList) {
				temp = temp.concat(questionList.get(currentQuestion)
						.getSelectedanswer().concat(string + ","));
			}
			questionList.get(currentQuestion).setSelectedanswer(temp);
			StringBuilder sb = new StringBuilder(questionList.get(
					currentQuestion).getSelectedanswer());
			sb.deleteCharAt(sb.length() - 1);
			questionList.get(currentQuestion).setSelectedanswer(sb.toString());
		}
	}

	private void changeQuestion(int index) {
		txtquestion.setText(questionList.get(index).getQuestion());
		chkOption1.setText(questionList.get(index).getOption1());
		chkOption2.setText(questionList.get(index).getOption2());
		chkOption3.setText(questionList.get(index).getOption3());
		chkOption4.setText(questionList.get(index).getOption4());
		chkOption1.setChecked(false);
		chkOption2.setChecked(false);
		chkOption3.setChecked(false);
		chkOption4.setChecked(false);
		String[] answers = questionList.get(index).getSelectedanswer()
				.split(",");
		ArrayList<String> answerList = new ArrayList<String>(
				Arrays.asList(answers));
		if (answerList.contains("1"))
			chkOption1.setChecked(true);
		if (answerList.contains("2"))
			chkOption2.setChecked(true);
		if (answerList.contains("3"))
			chkOption3.setChecked(true);
		if (answerList.contains("4"))
			chkOption4.setChecked(true);

	}
}
