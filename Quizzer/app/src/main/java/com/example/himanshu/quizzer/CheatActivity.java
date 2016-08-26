package com.example.himanshu.quizzer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class CheatActivity extends AppCompatActivity {

    public final static String HINT = "hint";
    public final static String CHEAT = "cheat";
    public static final String SCORE = "score";
    public static final String LIFE = "life";
    public static final String QUES = "question";

    public static final String ANS = "ans";
    private int ans;
    private int score;
    private int life;
    private int prime;
    private int ques;
    private int cheat;

    Context context;
    int longduration;

    private String answer;


    private Button mShowCheat;
    private TextView mcheatAnswer;


    private static final String TAG = "CheatActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cheat = 0;
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mShowCheat = (Button) findViewById(R.id.ShowCheat);
        mcheatAnswer = (TextView) findViewById(R.id.cheatAnswer);

        Intent intent = getIntent();
        Log.d(TAG,"GOT That");
        String a = intent.getStringExtra(ANS);
//        Log.d(TAG,"GOT THIS ------"+a);
        try {
            ans = Integer.parseInt(a);
        }
        catch(NumberFormatException nfe)
        {
            Log.d(TAG,"GOt error");
        }
//        Log.d(TAG,"TRIED THIS --- "+ans);
        a = intent.getStringExtra(SCORE);
        score = Integer.parseInt(a);
        a = intent.getStringExtra(LIFE);
        life = Integer.parseInt(a);
        a = intent.getStringExtra(QUES);
        ques = Integer.parseInt(a);

        if(ans == 1)
        {
            answer = "a prime";
        }
        else
        {
            answer = "not a prime";
        }

        Log.d(TAG,"GOT THIS "+ans+";;;"+score+";;;"+life+";;;"+ques);



        mShowCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                cheat = 1;
                mcheatAnswer.setText(ques+" is "+ answer+" number.");
                longduration = Toast.LENGTH_LONG;
                String an = ques+" is "+ answer+" number.";
                Toast.makeText(context,an,longduration).show();
                Intent returnIntent = new Intent();
//              int cheat = 1;

                String a = Integer.toString(cheat);
                returnIntent.putExtra(CHEAT, a);
//              returnIntent.putExtra("HI","hahaha");
                setResult(RESULT_OK, returnIntent);
//                finish();


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
