package com.example.android.classicmusicquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * Created by fanny on 05/01/2018.
 */

public class MainActivity extends AppCompatActivity {

    final int totalQuestions = 7;

    Button submitButton;
    Button viewAnswersButton;
    RadioGroup questionOneAnswers, questionTwoAnswers, questionThreeAnswers, questionFourAnswers,
            questionFiveAnswers, questionSixAnswers;
    RadioButton questionOneCorrectAnswer, questionTwoCorrectAnswer, questionThreeCorrectAnswer,
            questionFourCorrectAnswer, questionFiveCorrectAnswer, questionSixCorrectAnswer;

    CheckBox questionSevenCorrectCheckBox1;
    CheckBox questionSevenCheckBox2;
    CheckBox questionSevenCorrectCheckBox3;
    CheckBox questionSevenCheckBox4;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = (findViewById(R.id.submit_button));
        viewAnswersButton = (findViewById(R.id.view_answers_button));


        questionOneAnswers = findViewById(R.id.radio_group_question_1);
        questionTwoAnswers = findViewById(R.id.radio_group_question_2);
        questionThreeAnswers = findViewById(R.id.radio_group_question_3);
        questionFourAnswers = findViewById(R.id.radio_group_question_4);
        questionFiveAnswers = findViewById(R.id.radio_group_question_5);
        questionSixAnswers = findViewById(R.id.radio_group_question_6);

        questionOneCorrectAnswer = findViewById(R.id.question_1_answer_3_correct);
        questionTwoCorrectAnswer = findViewById(R.id.question_2_answer_2_correct);
        questionThreeCorrectAnswer = findViewById(R.id.question_3_answer_2_correct);
        questionFourCorrectAnswer = findViewById(R.id.question_4_answer_4_correct);
        questionFiveCorrectAnswer = findViewById(R.id.question_5_answer_3_correct);
        questionSixCorrectAnswer = findViewById(R.id.question_6_answer_3_correct);

        questionSevenCorrectCheckBox1 = (CheckBox) findViewById(R.id.question_7_answer_1_correct);
        questionSevenCheckBox2 = (CheckBox) findViewById(R.id.question_7_answer_2);
        questionSevenCorrectCheckBox3 = (CheckBox) findViewById(R.id.question_7_answer_3_correct);
        questionSevenCheckBox4 = (CheckBox) findViewById(R.id.question_7_answer_4);

        Intent userNameIntent = getIntent();
        final String userName = userNameIntent.getStringExtra("userName");


        submitButton.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                int[] results = checkAnswers();
                String message = createMessage(results, totalQuestions, userName);
                Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            }
        });

        viewAnswersButton.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                highlightCorrectAnswers();
            }
        });
    }

    /**
     * This method checks if any answer in any question was selected and then checks
     * if the correct answer was selected. It also calculates the score of correct answers and
     * how many questions was not answered.
     *
     * @return an array of two values [correct answers, questions not selected by the user]
     */
    public int[] checkAnswers() {
        int correctAnswers = 0;
        int questionsNotChecked = 0;
        boolean questionSevenCorrectAnswer1 = questionSevenCorrectCheckBox1.isChecked();
        boolean questionSevenAnswer2 = questionSevenCheckBox2.isChecked();
        boolean questionSevenCorrectAnswer3 = questionSevenCorrectCheckBox3.isChecked();
        boolean questionSevenAnswer4 = questionSevenCheckBox4.isChecked();

        if (questionOneAnswers.getCheckedRadioButtonId() == -1) {
            questionsNotChecked += 1;
        } else {
            if (questionOneCorrectAnswer.isChecked()) {
                correctAnswers += 1;
            }
        }

        if (questionTwoAnswers.getCheckedRadioButtonId() == -1) {
            questionsNotChecked += 1;
        } else {
            if (questionTwoCorrectAnswer.isChecked()) {
                correctAnswers += 1;
            }
        }

        if (questionThreeAnswers.getCheckedRadioButtonId() == -1) {
            questionsNotChecked += 1;
        } else {
            if (questionThreeCorrectAnswer.isChecked()) {
                correctAnswers += 1;
            }
        }

        if (questionFourAnswers.getCheckedRadioButtonId() == -1) {
            questionsNotChecked += 1;
        } else {
            if (questionFourCorrectAnswer.isChecked()) {
                correctAnswers += 1;
            }
        }

        if (questionFiveAnswers.getCheckedRadioButtonId() == -1) {
            questionsNotChecked += 1;
        } else {
            if (questionFiveCorrectAnswer.isChecked()) {
                correctAnswers += 1;
            }
        }


        if (questionSixAnswers.getCheckedRadioButtonId() == -1) {
            questionsNotChecked += 1;
        } else {
            if (questionSixCorrectAnswer.isChecked()) {
                correctAnswers += 1;
            }
        }

        if (questionSevenCorrectAnswer1==false) {
            if (questionSevenAnswer2==false) {
                if (questionSevenCorrectAnswer3==false) {
                    if (questionSevenAnswer4==false) {
                        questionsNotChecked += 1;
                    }
                }
            }
        } else {
            if (questionSevenCorrectAnswer1) {
                if (questionSevenCorrectAnswer3) {
                    correctAnswers += 1;
                }
            }
        }

        return new int[]{correctAnswers, questionsNotChecked};
    }

    /**
     * This method creates the score message and checks if the user answered to all questions.
     *
     * @param results        an array of two values [correct answers, questions not selected by the user]
     * @param totalQuestions the total number of questions in the quiz
     * @param userName       the user's name
     * @return final score message
     */
    public String createMessage(int[] results, int totalQuestions, String userName) {
        String message = "";
        message += getString(R.string.userGreetings) + " " + userName + "!\n";
        if (results[1] > 0) {
            message += getString(R.string.question_not_answered) + " " + results[1] + " " + getString(R.string.score_message_3);
            return message;
        } else if (results[0] == totalQuestions) {
            message += getString(R.string.perfect_score);
            return message;
        } else if (results[0] >= 3) {
            message += getString(R.string.score_message_1) + " " + results[0] + " " + getString(R.string.correct_answers);
            message += " " + getString(R.string.total_question) + " " + totalQuestions;
            return message;
        } else {
            message += getString(R.string.score_message_2) + " " + results[0] + " " + getString(R.string.correct_answers);
            message += " " + getString(R.string.total_question) + " " + totalQuestions;
            return message;
        }
    }

    /**
     * This method changes the text color of correct answers.
     */
    public void highlightCorrectAnswers() {
        questionOneCorrectAnswer.setTextColor(getResources().getColor(R.color.correctAnswers));
        questionTwoCorrectAnswer.setTextColor(getResources().getColor(R.color.correctAnswers));
        questionThreeCorrectAnswer.setTextColor(getResources().getColor(R.color.correctAnswers));
        questionFourCorrectAnswer.setTextColor(getResources().getColor(R.color.correctAnswers));
        questionFiveCorrectAnswer.setTextColor(getResources().getColor(R.color.correctAnswers));
        questionSixCorrectAnswer.setTextColor(getResources().getColor(R.color.correctAnswers));
        questionSevenCorrectCheckBox1.setTextColor(getResources().getColor(R.color.correctAnswers));
        questionSevenCorrectCheckBox3.setTextColor(getResources().getColor(R.color.correctAnswers));
    }
}
