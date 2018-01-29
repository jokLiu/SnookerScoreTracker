package com.example.jokubas.snookertracker_1601768;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainGameActivity extends AppCompatActivity {

    private ScoreTracker score;
    private TextView p1score;
    private TextView p2score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        Intent intent = getIntent();

        String pName1 = intent.getStringExtra(Input2PlayersNamesActivity.name1);
        String pName2 = intent.getStringExtra(Input2PlayersNamesActivity.name2);

        TextView n1 = (TextView) findViewById(R.id.player1);
        TextView n2 = (TextView) findViewById(R.id.player2);
        p1score = (TextView) findViewById(R.id.score1);
        p2score = (TextView) findViewById(R.id.score2);

        n1.setText(pName1);
        n2.setText(pName2);

        score = new ScoreTracker(pName1, pName2);

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


        p1score.setText(String.format(Locale.US,"%d", score.getPlayer1T1Score()));
        p2score.setText(String.format(Locale.US,"%d", score.getPlayer1T2Score()));

    }
}
