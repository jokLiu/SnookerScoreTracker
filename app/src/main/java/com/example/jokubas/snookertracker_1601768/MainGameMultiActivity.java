package com.example.jokubas.snookertracker_1601768;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainGameMultiActivity extends AppCompatActivity {

    private ScoreTrackSingle score;
    private TextView p1T1Score;
    private TextView p2T1Score;
    private TextView p3T2Score;
    private TextView p4T2Score;
    private TextView t1Score;
    private TextView t2Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_multi);

        Intent intent = getIntent();

        // Get the players names from previous activity
        String pName1 = intent.getStringExtra(Input4PlayersNamesActivity.name1);
        String pName2 = intent.getStringExtra(Input4PlayersNamesActivity.name2);
        String pName3 = intent.getStringExtra(Input4PlayersNamesActivity.name3);
        String pName4 = intent.getStringExtra(Input4PlayersNamesActivity.name4);

        // get the text views of players' names from the current activity
        TextView n1 = (TextView) findViewById(R.id.name1_t1);
        TextView n2 = (TextView) findViewById(R.id.name2_t1);
        TextView n3 = (TextView) findViewById(R.id.name3_t2);
        TextView n4 = (TextView) findViewById(R.id.name4_t2);

        // set players names received
        // TODO If players names are empty leave the current names
        // maybe create helper method for this
        if(!pName1.equals(""))
            n1.setText(pName1);
        if(!pName2.equals(""))
            n2.setText(pName2);
        if(!pName3.equals(""))
            n3.setText(pName3);
        if(!pName4.equals(""))
            n4.setText(pName4);

        p1T1Score = (TextView) findViewById(R.id.score1_tm1);
        p2T1Score = (TextView) findViewById(R.id.score2_tm1);
        p3T2Score = (TextView) findViewById(R.id.score3_tm2);
        p4T2Score = (TextView) findViewById(R.id.score4_tm2);
        t1Score = (TextView) findViewById(R.id.score_tm1);
        t2Score = (TextView) findViewById(R.id.score_tm2);

        // TODO names might be empty
        score = new ScoreTrackSingle(pName1, pName2, pName3, pName4);
    }
}
