package com.example.himanshu.quizzer;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public final static String HINT = "hint";
    public final static String CHEAT = "cheat";
    public static final String SCORE = "score";
    public static final String LIFE = "life";
    public static final String QUES = "question";
    public static final String ANS = "ans";
//    public static final String CHEAT = "cheat";
//    public static final String HINT = "hint";
    public static final int cheatAct = 1;
    public static final int hintAct = 2;
    int aloo;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mHintButton;
    private Button mCheatButton;


    private TextView mscore;
    private TextView mtextViewer;
    private TextView mlife;

    public int cheat;
    public int hint;

    private int score;
    private int life;
    private int prime;
    Context context;
    CharSequence toast;
    int duration;
    int longduration;

    private Integer[] primes;

    void setques()
    {
        cheat = 0;
        Random rand = new Random();
        prime = rand.nextInt(1000) + 1;
        int all = rand.nextInt(500) + 1;
        int index;
        int len = primes.length;

        Log.d(TAG,all+"- all");
        if(all%2 == 0)
        {
            index = rand.nextInt(len)+1;
            prime = primes[index];
        }
        else
        {

        }
        Log.d(TAG, "The number is " + prime);
        displayState();
    }

    void displayState()
    {
        String b = new String();
        b = Integer.toString(score);
        mscore.setText(b);
        String c = new String();
        c = Integer.toString(life);
        mlife.setText(c);
        String a = new String();
        a = Integer.toString(prime);
        mtextViewer.setText(a);
    }

    int checkAnswer()
    {
        int i = 0;
        int ans = 0; //false
        for(i = 0; i < primes.length; i++)
        {
            if(primes[i] == prime)
            {
                ans = 1;    // true its a prime number
                break;
            }
        }
        return ans;
    }


    private static final String TAG = "QuizActivity";
    int checkCorrect(Boolean a)
    {
        Boolean pri = false;
        int i = 0;
        for(i = 0; i < primes.length; i++)
        {
            if(primes[i] == prime)
            {
                pri = true;
                break;
            }
        }
        if(pri == a)
        {
            if(cheat == 0)
            {
                score++;
            }
            else
            {
                String msg = "You have used the cheat.";
                Toast.makeText(context,msg,duration).show();
            }
            if(hint == 1)
            {
                String msg = "You have used the hint.";
                hint = 0;
                Toast.makeText(context,msg,duration).show();
            }
            return 1;   //he's right
        }
        else
        {
            life--;
            if(life<=0)
            {
                String msg = "GAME OVER. FINAL SCORE IS "+score+".";
                Toast.makeText(context,msg,longduration).show();
                life=3;
                score=0;
                Random rand = new Random();
                prime = rand.nextInt(1000) + 1;



            }
            return 0;   // he's wrong
        }
    }
    public void openHint() {
        int ans = checkAnswer();
        Intent intent = new Intent(this, HintActivity.class);
        intent.putExtra(HINT,hint);

        String a = Integer.toString(score);
        intent.putExtra(SCORE,a);

        a = Integer.toString(cheat);
        intent.putExtra(CHEAT,a);

        a = Integer.toString(life);
        intent.putExtra(LIFE,a);

        a = Integer.toString(prime);
        intent.putExtra(QUES,a);

        a = Integer.toString(ans);
        intent.putExtra(ANS,a);
        startActivityForResult(intent,hintAct);
    }

    public void openCheat()
    {
        int ans = checkAnswer();
        Intent intent = new Intent(this, CheatActivity.class);
        intent.putExtra(HINT,hint);

        String a = Integer.toString(score);
        intent.putExtra(SCORE,a);

        a = Integer.toString(cheat);
        intent.putExtra(CHEAT,a);

        a = Integer.toString(life);
        intent.putExtra(LIFE,a);

        a = Integer.toString(prime);
        intent.putExtra(QUES,a);

        a = Integer.toString(ans);
        intent.putExtra(ANS,a);
        startActivityForResult(intent,cheatAct);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode)
        {
            case cheatAct:
                if(resultCode == RESULT_OK) {
                    Log.d(TAG, "lets take something");
                    //                if(cheat == 1) {
                    Log.d(TAG, "RESULT OKAY!!!");
                    String usedCheat;
                    usedCheat = data.getStringExtra(CHEAT);

                    cheat = Integer.parseInt(usedCheat);
                    Log.d(TAG, "you used the cheat -----" + cheat);
                    //                }
                }
            break;

            case hintAct:
                if(resultCode == RESULT_OK) {

                    String usedHint;
                    usedHint = data.getStringExtra(HINT);

                    hint = Integer.parseInt(usedHint);
                    Log.d(TAG, "you used the cheat -----" + hint);
                    //                }
                }
                break;

            default:


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hint = 0;
        cheat = 0;

        mTrueButton = (Button) findViewById(R.id.TrueButton);
        mFalseButton = (Button) findViewById(R.id.FalseButton);
        mNextButton = (Button) findViewById(R.id.NextButton);
        mHintButton = (Button) findViewById(R.id.HintButton);
        mCheatButton = (Button) findViewById(R.id.CheatButton);

        mtextViewer = (TextView) findViewById(R.id.textViewer);
        mscore  = (TextView) findViewById(R.id.score);
        mlife = (TextView) findViewById(R.id.life);

        // for toast
        context = getApplicationContext();
        duration = Toast.LENGTH_SHORT;
        longduration = Toast.LENGTH_LONG;
        if(savedInstanceState == null)
        {
            Random rand = new Random();
            prime = rand.nextInt(1000) + 1;
            score = 0;
            life = 3;
        }
        mHintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHint();

            }
        });

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCheat();
            }
        });
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d(TAG, "Clicked True");
                int res = checkCorrect(true);

                if(res == 1)
                {
                    Toast.makeText(context,"Correct",duration).show();
                    // the answer is correct
                }
                else
                {
                    Toast.makeText(context,"Incorrect",duration).show();
                    //the answer is incorrect
                }

                Log.d(TAG,"Got result "+res);
                setques();
            }

    });
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d(TAG, "Clicked False");
                int res = checkCorrect(false);

                if(res == 1)
                {
                    Toast.makeText(context,"Correct",duration).show();
                    // the answer is correct
                }
                else
                {
                    Toast.makeText(context,"Incorrect",duration).show();
                    //the answer is incorrect
                }
                Log.d(TAG,"Got result "+res);
                setques();
            }
//        mtextViewer.setText("Yes this works!");
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setques();
            }
        });
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){

        Log.i(TAG, "Inside onSaveInstance");

        savedInstanceState.putInt(SCORE,score);
        savedInstanceState.putInt(LIFE,life);
        savedInstanceState.putInt(QUES,prime);
        savedInstanceState.putInt(CHEAT,cheat);
        savedInstanceState.putInt(HINT,hint);

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
            cheat = savedInstanceState.getInt(CHEAT);
            hint = savedInstanceState.getInt(HINT);


        }
        Log.i(TAG, "Inside onRestoreInstance");
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(TAG, "Inside OnStart");
        mtextViewer.setText("Pehle set kiya");
        primes = new Integer[]{2,   3,   5,   7,  11,  13,  17,  19,  23,  29,  31,  37,  41,
                43,  47,  53,  59,  61,  67,  71,  73,  79,  83,  89,  97, 101,

                103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167,
                173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239,

                241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313,
                317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397,

                401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467,
                479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569,

                571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643,
                647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733,

                739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823,
                827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911,

                919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997 };
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG,"Inside OnPause");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"Inside OnREsume");
//        Random rand = new Random();
//        int prime = rand.nextInt(1000) + 1;
//        Log.d(TAG, "The number is " + prime);
//        mtextViewer.setText(prime);

        displayState();
//        mNextButton.setOnClickListener();


    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "Inside OnSTop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Inside OnDestroy");
    }



}