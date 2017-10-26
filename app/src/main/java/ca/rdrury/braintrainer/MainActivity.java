package ca.rdrury.braintrainer;

import android.os.CountDownTimer;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView rightWrong, score, timeLeft,toBeAdded,button0,button1,button2,button3,finalScore;
    Button playAgain;
    GridLayout answers;
    int rightAnswer;
    int locationOfRight;
    ArrayList<Integer>answerKey = new ArrayList<Integer>();
    int top=0;
    int bottom=0;

    public void beginGame(View view) {
        view.setVisibility(View.GONE);

        rightWrong = (TextView) findViewById(R.id.rightWrong);
        score = (TextView) findViewById(R.id.score);
        timeLeft = (TextView) findViewById(R.id.timeLeft);
        toBeAdded = (TextView) findViewById(R.id.toBeAdded);

        button0 = (TextView) findViewById(R.id.button0);
        button1 = (TextView) findViewById(R.id.button1);
        button2 = (TextView) findViewById(R.id.button2);
        button3 = (TextView) findViewById(R.id.button3);
        finalScore = (TextView)findViewById(R.id.finalScore);

        answers = (GridLayout) findViewById(R.id.answers);


        rightWrong.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        timeLeft.setVisibility(View.VISIBLE);
        toBeAdded.setVisibility(View.VISIBLE);
        answers.setVisibility(View.VISIBLE);


        Random r = new Random();
        int add1 = r.nextInt(20);
        int add2 = r.nextInt(20);
        rightAnswer = add1+add2;
        toBeAdded.setText(add1+" + "+add2);

        locationOfRight = r.nextInt(4);

        int incorrectAnswer;

        for (int i = 0; i<4; i++){

            if (i==locationOfRight){
                answerKey.add(rightAnswer);
            }
            else{
                incorrectAnswer = r.nextInt(20);
                while(incorrectAnswer == rightAnswer){
                    incorrectAnswer = r.nextInt(20);
                }
                answerKey.add(incorrectAnswer);
            }

        }
        button0.setText(Integer.toString(answerKey.get(0)));
        button1.setText(Integer.toString(answerKey.get(1)));
        button2.setText(Integer.toString(answerKey.get(2)));
        button3.setText(Integer.toString(answerKey.get(3)));

        answerKey.clear();



        playAgain = (Button)findViewById(R.id.playAgain);
        new CountDownTimer(31000, 1000) {
            public void onTick(long tilDone) {
                timeLeft.setText("" + (int) tilDone / 1000 + "");
            }

            public void onFinish() {
                timeLeft.setText("0");
                rightWrong.setVisibility(View.GONE);
                playAgain.setVisibility(View.VISIBLE);
                finalScore.setText("Final score:"+top+"/"+bottom);
                finalScore.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);

            }

        }.start();


    }
    public void chooseAnswer(View view){
        bottom++;
            if(view.getTag().toString().equals(Integer.toString(locationOfRight))){
                top++;
                rightWrong.setText("Right!");
            }
            else{
                rightWrong.setText("Wrong!");
            }
            score.setText(top+"/"+bottom);


        Random r = new Random();
        int add1 = r.nextInt(20);
        int add2 = r.nextInt(20);
        rightAnswer = add1+add2;
        toBeAdded.setText(add1+" + "+add2);

        locationOfRight = r.nextInt(4);

        int incorrectAnswer;

        for (int i = 0; i<4; i++){

            if (i==locationOfRight){
                answerKey.add(rightAnswer);
            }
            else{
                incorrectAnswer = r.nextInt(20);
                while(incorrectAnswer == rightAnswer){
                    incorrectAnswer = r.nextInt(20);
                }
                answerKey.add(incorrectAnswer);
            }

        }
        button0.setText(Integer.toString(answerKey.get(0)));
        button1.setText(Integer.toString(answerKey.get(1)));
        button2.setText(Integer.toString(answerKey.get(2)));
        button3.setText(Integer.toString(answerKey.get(3)));
        answerKey.clear();


    }
    public void restartGame(View view){
        top = 0;
        bottom = 0;
        rightWrong.setText("");
        score.setText("0/0");

        finalScore.setVisibility(View.GONE);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);



        beginGame(view);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
