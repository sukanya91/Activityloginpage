package com.example.activity_login;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ScoreListAdapter extends ArrayAdapter<TotalScores> {
    //variables
    private Activity context;
    List<TotalScores> scores;

    //constructor that inherits from the super
    public ScoreListAdapter(Activity context, List<TotalScores> scores) {
        super(context, R.layout.activity_scores, scores);
        this.context = context;
        this.scores = scores;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //this method will instantiate our layout file from the layout_artist_list.xml into
        //our view objects


        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_scores
                , null
                , true);


        TotalScores score = scores.get(position);


        //replace later
        return listViewItem;
    }
}

