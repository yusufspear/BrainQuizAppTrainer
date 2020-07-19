package com.example.brainquizapp;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView count,timer,question,displayResult;
    Button button0,button1,button2,button3, endButton;
    Random rand;
    int answer, locationOfCorrectAnswer, totalQuestionCount =0, correctAnswerCount =0;
    ArrayList<Integer> arrayList =new ArrayList<Integer>(4);
    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        playAgain(count);
    }

    @SuppressLint("SetTextI18n")
    private void generateQuestion() {

        timer.setText("15s");
        rand =new Random();
        int a=rand.nextInt(11);
        int b=rand.nextInt(11);
        answer=a+b;
        question.setText(a + " + " + b);
        generateAnswer();
        countDownTimer=new CountDownTimer(15100,1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long l) {
                int sec= (int) (l/1000);
                timer.setText(sec +"s");
            }

            @Override
            public void onFinish() {
                displayResult.setText("TIME OUT");
                endButton.setText("Play Again");
                endButton.setVisibility(View.VISIBLE);
            }
        };
        countDownTimer.start();
    }



    @SuppressLint("SetTextI18n")
    private void generateAnswer() {

        locationOfCorrectAnswer=rand.nextInt(4);
        arrayList.clear();
        for(int i=0; i<=3; i++){

            if (locationOfCorrectAnswer==i){
                arrayList.add(answer);
            }
            else {
                int temp=rand.nextInt(11);
                while (answer==temp){
                    temp=rand.nextInt(11);
                }
                arrayList.add(temp);
            }
        }
        button0.setText(Integer.toString(arrayList.get(0)));
        button1.setText(Integer.toString(arrayList.get(1)));
        button2.setText(Integer.toString(arrayList.get(2)));
        button3.setText(Integer.toString(arrayList.get(3)));
    }

    @SuppressLint("SetTextI18n")
    public void checkAnswer(View view){

        String tag=view.getTag().toString();
        if (Integer.toString(locationOfCorrectAnswer).equals(tag)){

            displayResult.setText("Correct Answer!");
            correctAnswerCount++;


        }else {
            displayResult.setText("Wrong Answer!");
        }
        totalQuestionCount++;
        count.setText(correctAnswerCount +"/"+ totalQuestionCount);
        countDownTimer.cancel();
        generateQuestion();

    }
    @SuppressLint("SetTextI18n")
    public void playAgain(View view){

        totalQuestionCount =0;
        correctAnswerCount =0;
        displayResult.setText("");
        endButton.setVisibility(View.INVISIBLE);
        count.setText(correctAnswerCount+"/"+totalQuestionCount);
        generateQuestion();
    }


    private void initView() {

        count=findViewById(R.id.countTextView);
        timer=findViewById(R.id.timerTextView);
        question=findViewById(R.id.questionTextView);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        displayResult=findViewById(R.id.resultTextView);
        endButton=findViewById(R.id.timeOutButton);
    }
}