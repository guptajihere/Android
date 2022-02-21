package com.example.zappycode.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;

    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);

        new CountDownTimer(30100,1000) {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Time up!");
                timerTextView.setText("0s");
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setClickable(false);
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);
            }
        }.start();
    }

    public void chooseAnswer(View view) {
       if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
           resultTextView.setText("Correct!");
           score++;
       } else {
           resultTextView.setText("Wrong :(");
       }
       numberOfQuestions++;
       scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
       newQuestion();
    }

    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }

    public void newQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(41);
        int b = rand.nextInt(41);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();  // to clear all the possible answers of the previous question

        for (int i=0; i<4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a+b);
            } else {
                int wrongAnswer = rand.nextInt(81);

                while (wrongAnswer == a+b) {
                    wrongAnswer = rand.nextInt(81);
                }

                answers.add(wrongAnswer);
            }

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Home screen");
        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);
        goButton = findViewById(R.id.goButton);

        goButton.setVisibility(View.VISIBLE);  //originally it is set to visible as soon as the app runs
        gameLayout.setVisibility(View.INVISIBLE); //originally all the views in the game layout will be set to invisible
    }
}
