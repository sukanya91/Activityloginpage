package com.example.activity_login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class scores extends AppCompatActivity {

    ListView listViewScores;

    DatabaseReference db;

    List<TotalScores> scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        Intent intent = getIntent();
        db = FirebaseDatabase.getInstance().getReference("scores").child(intent.getStringExtra("SCORE_ID"));
        listViewScores = findViewById(R.id.listViewScores);
        scores = new ArrayList<>();

    }
    @Override
    protected void onStart() {
        super.onStart();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                String value = dataSnapshot.getValue()(String.class);
//                Log.d(TAG, "Value is:" +value);
                scores.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    TotalScores score = postSnapshot.getValue(TotalScores.class);
                    scores.add(score);
                }
                ScoreListAdapter myScoreAdapter = new ScoreListAdapter(scores.this, scores);
                        listViewScores.setAdapter(myScoreAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
