package com.example.himanshu.quizzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {
    public final static String HINT = "hint";
    public final static String CHEAT = "cheat";
    public static final String SCORE = "score";
    public static final String LIFE = "life";
    public static final String QUES = "question";

    public static final String ANS = "ans";
    private int ans;
    private int hint;
    private int score;
    private int life;
    private int prime;
    private int ques;
    private int cheat;

    private Button mshowHint;
    private TextView mhintAnswer;

    private static final String TAG = "HintActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        mshowHint = (Button) findViewById(R.id.showHint);
        mhintAnswer = (TextView) findViewById(R.id.hintAnswer);

        Intent intent = getIntent();
        Log.d(TAG,"GOT That");

        String a = intent.getStringExtra(ANS);
        ans = Integer.parseInt(a);

        a = intent.getStringExtra(SCORE);
        score = Integer.parseInt(a);

        a = intent.getStringExtra(LIFE);
        life = Integer.parseInt(a);

        a = intent.getStringExtra(QUES);
        ques = Integer.parseInt(a);

        mshowHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ques%2 == 0)
                {
                    mhintAnswer.setText("All even numbers greater than 2 are non-prime.");
                }
                else
                {
                    mhintAnswer.setText("Factorise the given number");
                }
                Intent returnIntent = new Intent();

                hint = 1;
                String a = Integer.toString(hint);
                returnIntent.putExtra(HINT, a);
//              returnIntent.putExtra("HI","hahaha");
                setResult(RESULT_OK, returnIntent);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){

        Log.i(TAG, "Inside onSaveInstance");

        savedInstanceState.putInt(SCORE,score);
        savedInstanceState.putInt(LIFE,life);
        savedInstanceState.putInt(QUES,prime);

        super.onSaveInstanceState(savedInstanceState);


    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        if(savedInstanceState != null)
        {
            prime = savedInstanceState.getInt(QUES);
            score = savedInstanceState.getInt(SCORE);
            life = savedInstanceState.getInt(LIFE);


        }
        Log.i(TAG, "Inside onRestoreInstance");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "Inside OnPause");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Inside OnREsume");



    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Inside OnDestroy");
    }


    @Override
    public void onStart() {
        super.onStart();


    }
}


