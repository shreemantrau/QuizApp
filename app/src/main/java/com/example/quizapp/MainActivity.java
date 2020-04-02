package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private final String SCORE_KEY="SCORE";
    private final String INDEX_KEY="Index";
    private TextView MtxtQuestion;
    private int mQuizQuestion;
    private Button btnTrue;
    private int mQuizIndex;
    private Button btnFalse;
    private ProgressBar mProgressBar;
    private TextView mQuizStatsTextView;
    private int user_score;

    private QuizModel[] questionCollection=new QuizModel[]{
            new QuizModel(R.string.q1, true),
            new QuizModel(R.string.q2,false),
            new QuizModel(R.string.q3,true),
            new QuizModel(R.string.q4,false),
            new QuizModel(R.string.q5,true),
            new QuizModel(R.string.q6,false),
            new QuizModel(R.string.q7,true),
            new QuizModel(R.string.q8,false),
            new QuizModel(R.string.q9,true),
            new QuizModel(R.string.q10,false)
    };
    final int USER_PROGRESS= (int)Math.ceil(100.0/questionCollection.length);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState !=null){
            user_score=savedInstanceState.getInt(SCORE_KEY);
            mQuizIndex=savedInstanceState.getInt(INDEX_KEY);
        }
       // else is not required because its already set to 0!
//        else {
//            user_score=0;
//            mQuizIndex=0;
//        }

        btnTrue=findViewById(R.id.buttonTrue);
        btnFalse=findViewById(R.id.buttonWrong);

        MtxtQuestion=findViewById(R.id.txtQuestion);
        QuizModel q1=questionCollection[mQuizIndex];
        mQuizQuestion=q1.getQuestion();
        MtxtQuestion.setText(questionCollection[mQuizIndex].getQuestion());
        mProgressBar=findViewById(R.id.quizPB);
        mQuizStatsTextView=findViewById(R.id.txtQuizStats);
        mQuizStatsTextView.setText(user_score+"");

//        View.OnClickListener myClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(v.getId()==R.id.buttonTrue) {
//                    Log.i("My App", "True button is tapped!");
//                    Toast myToastObject = Toast.makeText(getApplicationContext(),"Button True Tapped",Toast.LENGTH_SHORT);
//                    myToastObject.show();
//                }
//                else if(v.getId()== R.id.buttonWrong){
//                    Log.i("My App","Wrong button is tapped!");
//                }
//            }
//        };

        //btnTrue.setOnClickListener(myClickListener);
//        btnFalse.setOnClickListener(myClickListener);

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(questionCollection[mQuizIndex].isAnswer() == true){
//                    user_score++;
//                    Toast.makeText(getApplicationContext(),"Correct Answer!",Toast.LENGTH_SHORT).show();
//                    mQuizStatsTextView.setText(user_score+"");
//                    mProgressBar.incrementProgressBy(USER_PROGRESS);
//                }
//                else{
//                    user_score--;
//                    Toast.makeText(getApplicationContext(),"Wrong Answer",Toast.LENGTH_SHORT).show();
//                    mQuizStatsTextView.setText(user_score+"");
//                    mProgressBar.incrementProgressBy(USER_PROGRESS);
//                }
                evaluateAnswer(true);
                changeQuestionOnButtonClick();

            }
        });
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"Wrong button is tapped!",Toast.LENGTH_SHORT).show();
//                if(questionCollection[mQuizIndex].isAnswer() == false){
//                    user_score++;
//                    Toast.makeText(getApplicationContext(),"Correct Answer!",Toast.LENGTH_SHORT).show();
//                    mQuizStatsTextView.setText(user_score+"");
//                    mProgressBar.incrementProgressBy(USER_PROGRESS);
//                }
//                else{
//                    user_score--;
//                    Toast.makeText(getApplicationContext(),"Wrong Answer",Toast.LENGTH_SHORT).show();
//                    mQuizStatsTextView.setText(user_score+"");
//                    mProgressBar.incrementProgressBy(USER_PROGRESS);
//                }
                evaluateAnswer(false);
                changeQuestionOnButtonClick();

            }
        });

       // QuizModel model= new QuizModel(R.string.q1,true);
    }

    public void evaluateAnswer(boolean userAnswer){
        if(questionCollection[mQuizIndex].isAnswer()==userAnswer){
            user_score++;
            Toast.makeText(getApplicationContext(),"Correct Answer",Toast.LENGTH_SHORT).show();
            mQuizStatsTextView.setText(user_score+"");
            mProgressBar.incrementProgressBy(USER_PROGRESS);
        }
        else{
            user_score--;
            Toast.makeText(getApplicationContext(),"Wrong Answer",Toast.LENGTH_SHORT).show();
            mQuizStatsTextView.setText(user_score+"");
            mProgressBar.incrementProgressBy(USER_PROGRESS);
        }
    }

    public void changeQuestionOnButtonClick(){

        if(mQuizIndex<questionCollection.length-1) {
//***************  mQuizIndex=(mQuizIndex+1)%10;  this solves the if and the else without writing them***********
            mQuizIndex++;
            mQuizQuestion = questionCollection[mQuizIndex].getQuestion();
            MtxtQuestion.setText(mQuizQuestion);

        }
        else{
//            mQuizIndex=0;
//            mQuizQuestion = questionCollection[mQuizIndex].getQuestion();
//            MtxtQuestion.setText(mQuizQuestion);


            AlertDialog.Builder quizAlert = new AlertDialog.Builder(this);//this is similar to getApplicationContext() used in toast
            quizAlert.setCancelable(false);
            quizAlert.setTitle("The quiz is finished");
            quizAlert.setMessage("Your Score is "+user_score);
            quizAlert.setPositiveButton("Finish the quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            quizAlert.show();
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCORE_KEY,user_score);
        outState.putInt(INDEX_KEY,mQuizIndex);
    }



}
