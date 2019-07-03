package com.example.activity_login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class homePage extends AppCompatActivity implements View.OnClickListener {

    ImageView input, output;
    Button rock, paper, scissors;
    TextView tv_score;
    Button btn_scores;

    int humanScore, computerScore = 0;
    int[] images = new int[]{
            R.mipmap.rock,
            R.mipmap.paper,
            R.mipmap.scissors

    };

    int userinput = 0;
    List<TotalScores> scores;

     DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        db = FirebaseDatabase.getInstance().getReference("score");

        input = (ImageView) findViewById(R.id.iv_input);
        output = (ImageView) findViewById(R.id.iv_output);
        rock = (Button) findViewById(R.id.btn_rock);
        paper = (Button) findViewById(R.id.btn_paper);
        scissors = (Button) findViewById(R.id.btn_scissors);
        tv_score =(TextView) findViewById(R.id.tv_score);
        btn_scores= (Button) findViewById(R.id.btn_scores);
        btn_scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homePage.this, scores.class);
                 startActivity(i);
            }
           
        });

        rock.setOnClickListener((View.OnClickListener) this);
        paper.setOnClickListener( this);
        scissors.setOnClickListener(this);

    }

//    @Override
//    protected void onStart(){
//        super.onStart();
//        //event listener for the db
//        db.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //clear our list
//                scores.clear();
//                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
//                    TotalScores score = postSnapshot.getValue(TotalScores.class);
//                    scores.add(score);
//                }
//                ScoreListAdapter myScoreAdapter = new ScoreListAdapter(homePage.this, scores );
//                //listViewScores.setAdapter(myScoreAdapter);
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

//
//      public void btn_scoresClick(View view) {
//
//          Intent i = new Intent(homePage.this, scores.class);
//          startActivity(i);
//
//      }

    public void onClick(View v){
        int id = v.getId();
        switch(id){
            case R.id.btn_rock: userinput = 1;
                input.setBackgroundResource(R.mipmap.rock);
                setOutput();
                break;

            case R.id.btn_paper: userinput = 2;
                input.setBackgroundResource(R.mipmap.paper);
                setOutput();
                break;
            case R.id.btn_scissors:userinput = 3;
                input.setBackgroundResource(R.mipmap.scissors);
                setOutput();
                break;
        }

    }
    private void setOutput(){
        int imageId = (int) (Math.random() * images.length);
        output.setBackgroundResource(images[imageId]);
        checkresult(imageId);
    }

    private  void checkresult(int imageId){
        if (userinput == 1 && imageId == 0){
            //user choose rock and computer choose Rock
            showresult(2);
        }
        else if(userinput == 1 && imageId == 1){
            //user choose rock and computer choose paper
            showresult(0);
        }
        else  if (userinput == 1 && imageId == 2){
            //user choose rock and computer choose scissors
            showresult(1);
        }
        else if(userinput == 2 && imageId == 0){
            //user choose paper and computer choose rock
            showresult(1);
        }
        else if(userinput == 2 && imageId == 1){
            //user choose paper and computer choose paper
            showresult(2);
        }
        else if(userinput == 2 && imageId == 2){
            //user choose paper and computer choose scissors
            showresult(0);
        }
        else if(userinput == 3 && imageId == 0) {
            //user choose scissors and computer choose rock
            showresult(0);
        }
        else if(userinput == 3 && imageId == 1) {
            //user choose scissors and computer choose paper
            showresult(1);
        }
        else if(userinput == 3 && imageId == 2) {
            //user choose scissors and computer choose scissors
            showresult(2);
        }

    }
    private void showresult(int result){


        if(result == 0){
            Toast.makeText(getApplicationContext(),"oh! You Lost :(",Toast.LENGTH_SHORT).show();
            computerScore++;
               String msg = "Score Human:"+Integer.toString(humanScore)+ "Computer score: "+Integer.toString(computerScore);
             tv_score.setText("Score Human:"+Integer.toString(humanScore)+ "Computer score: "+Integer.toString(computerScore));
            String score = db.push().getKey();
            db.child(score).setValue(msg);
        }
        else if(result == 1){
            Toast.makeText(getApplicationContext(),"You Won! Yeah! :)",Toast.LENGTH_SHORT).show();
            computerScore++;
             String msg = "Score Human:"+Integer.toString(humanScore)+ "Computer score: "+Integer.toString(computerScore);
            tv_score.setText("Score Human:"+Integer.toString(humanScore)+ "Computer score: "+Integer.toString(computerScore));
            String score = db.push().getKey();
            db.child(score).setValue(msg);
        }
       else if(result == 2){
            Toast.makeText(getApplicationContext(),"OOPS! It's a Tie! :P",Toast.LENGTH_SHORT).show();
            computerScore++;
            humanScore++;
             String msg = "Score Human:"+Integer.toString(humanScore)+ "Computer score: "+Integer.toString(computerScore);
            String score = db.push().getKey();   
            db.child(score).setValue(msg);
        }

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }







}
