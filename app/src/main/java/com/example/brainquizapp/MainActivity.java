package com.example.brainquizapp;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView count,timer,question;
    Button button0,button1,button2,button3;
    Random rand;
    int answer;
    ArrayList<Integer> arrayList =new ArrayList<Integer>(4);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        generateQuestion();
        generateAnswer();
    }

    private void generateQuestion() {

        rand =new Random();
        int a=rand.nextInt(11);
        int b=rand.nextInt(11);
        answer=a+b;
        question.setText(a + " + " + b);
    }

    @SuppressLint("SetTextI18n")
    private void generateAnswer() {

        int randomPlace=rand.nextInt(4);
        for(int i=0; i<=3; i++){

            if (randomPlace==i){
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

    public void checkAnswer(View view){
    }


    private void initView() {

        count=findViewById(R.id.countTextView);
        timer=findViewById(R.id.timerTextView);
        question=findViewById(R.id.questionTextView);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
    }
}