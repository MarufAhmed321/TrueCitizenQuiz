package com.example.truecitizenquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button falseButton;
    private Button trueButton;
    private TextView questionTextView;
    private ImageButton nextButton;
    private ImageButton previousButton;


    private int currentQuestionIndex = 0;

    private Question[] questions = new Question[]{
            new Question(R.string.question_amendments, false),
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_independence_rights, true),
            new Question(R.string.question_religion, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, true),
            //and add more!


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        falseButton = findViewById(R.id.false_btn);
        trueButton = findViewById(R.id.true_btn);
        questionTextView = findViewById(R.id.question_text_view);
        nextButton = findViewById(R.id.next_button);
        previousButton = findViewById(R.id.previous_button);







        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case  R.id.false_btn:
                checkAnswer(false);
                break;

            case  R.id.true_btn:
                checkAnswer(true);
                break;

            case R.id.next_button:
                //go to next question
                currentQuestionIndex = (currentQuestionIndex + 1) % questions.length; //we are safe now
                updateQuestion();
                break;

            case R.id.previous_button:
                if(currentQuestionIndex>0){
                    currentQuestionIndex = (currentQuestionIndex - 1) % questions.length; //we are safe now
                    updateQuestion();
                }


        }

    }


    private void updateQuestion(){
        Log.d("Current", "OnClick:" + currentQuestionIndex);
        questionTextView.setText(questions[currentQuestionIndex].getAnswerResId());

    }


    private void checkAnswer(boolean userChosenAnswer){
        boolean answerIsTrue = questions[currentQuestionIndex].isAnswerTrue();

        int toastMessageId;

        if (userChosenAnswer == answerIsTrue){
            toastMessageId = R.string.correct_answer;
        } else {
            toastMessageId = R.string.wrong_answer;
        }

        Toast.makeText(MainActivity.this, toastMessageId, Toast.LENGTH_SHORT).show();
    }

}

