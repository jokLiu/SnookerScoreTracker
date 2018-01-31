package com.example.jokubas.snookertracker_1601768;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class MainGameMultiActivity extends AppCompatActivity {

    private ScoreTrackMulti score;
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
        if (!pName1.equals(""))
            n1.setText(pName1);
        if (!pName2.equals(""))
            n2.setText(pName2);
        if (!pName3.equals(""))
            n3.setText(pName3);
        if (!pName4.equals(""))
            n4.setText(pName4);

        p1T1Score = (TextView) findViewById(R.id.score1_tm1);
        p2T1Score = (TextView) findViewById(R.id.score2_tm1);
        p3T2Score = (TextView) findViewById(R.id.score3_tm2);
        p4T2Score = (TextView) findViewById(R.id.score4_tm2);
        t1Score = (TextView) findViewById(R.id.score_tm1);
        t2Score = (TextView) findViewById(R.id.score_tm2);

        // TODO names might be empty
        score = new ScoreTrackMulti(pName1, pName2, pName3, pName4);
        setPlayers();
        setButtons();
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.red:
                score.updateScore(BallColour.RED);
                break;
            case R.id.yellow:
                score.updateScore(BallColour.YELLOW);
                break;
            case R.id.green:
                score.updateScore(BallColour.GREEN);
                break;
            case R.id.brown:
                score.updateScore(BallColour.BROWN);
                break;
            case R.id.blue:
                score.updateScore(BallColour.BLUE);
                break;
            case R.id.pink:
                score.updateScore(BallColour.PINK);
                break;
            case R.id.black:
                score.updateScore(BallColour.BLACK);
                break;
            case R.id.button_foul:
                score.foul();
                break;
            case R.id.button_next_player:
                score.nextPlayer();
                break;
            case R.id.button_frame_end:
                // TODO
                break;
            default:
                break;

        }

        setScoreText(p1T1Score, score.getPlayer1T1Score());
        setScoreText(p2T1Score, score.getPlayer2T1Score());
        setScoreText(p3T2Score, score.getPlayer1T2Score());
        setScoreText(p4T2Score, score.getPlayer2T2Score());
        setScoreText(t1Score, score.getTeam1Score());
        setScoreText(t2Score, score.getTeam2Score());
        setButtons();
        setPlayers();
    }

    private void setScoreText(TextView v, int score){
        v.setText(String.format(Locale.US, "%d", score));
    }


    private void setButtons() {
        boolean[] balls = score.getAvailableBalls();
        if (balls.length < 8) return;

        Button button;
        for (BallColour b : BallColour.values()) {
            int id = score.mapBallColourToButtonID(b);
            button = (Button) findViewById(id);
            if (button == null) continue;
            if (balls[b.getValue()]) {
                button.setEnabled(true);
                button.setAlpha(1f);

            } else {
                button.setEnabled(false);
                button.setAlpha(0.5f);
            }

        }
    }


    private void setPlayers() {
        int id = score.mapTurnToPlayerImageID();

        // player 1 team 1 image
        ImageView im = (ImageView) findViewById(R.id.p1_t1_image);
        if (R.id.p1_t1_image == id) {
            im.setAlpha(1f);
        } else {
            im.setAlpha(0.5f);
        }

        // player 2 team 1 image
        im = (ImageView) findViewById(R.id.p2_t1_image);
        if (R.id.p2_t1_image == id) {
            im.setAlpha(1f);
        } else {
            im.setAlpha(0.5f);
        }

        // player 3 team 2 image
        im = (ImageView) findViewById(R.id.p3_t2_image);
        if (R.id.p3_t2_image == id) {
            im.setAlpha(1f);
        } else {
            im.setAlpha(0.5f);
        }

        // player 4 team 2 image
        im = (ImageView) findViewById(R.id.p4_t2_image);
        if (R.id.p4_t2_image == id) {
            im.setAlpha(1f);
        } else {
            im.setAlpha(0.5f);
        }

    }
}
