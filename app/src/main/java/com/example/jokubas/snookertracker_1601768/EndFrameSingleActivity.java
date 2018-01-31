package com.example.jokubas.snookertracker_1601768;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EndFrameSingleActivity extends AppCompatActivity {

    public static String PLAYER1_NAME = "player1";
    public static String PLAYER2_NAME = "player2";
    public static String PLAYER1_SCORE = "player1Score";
    public static String PLAYER2_SCORE = "player2Score";
    public static String PLAYER1_IMAGE = "player1Image";
    public static String PLAYER2_IMAGE = "player2Image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_frame_single);

        Intent intent = getIntent();
        String p1name = intent.getStringExtra(PLAYER1_NAME);
        String p2name = intent.getStringExtra(PLAYER2_NAME);
        int p1score = intent.getIntExtra(PLAYER1_SCORE, 0);
        int p2score = intent.getIntExtra(PLAYER2_SCORE, 0);

        TextView nameWinner = findViewById(R.id.winner);
        TextView nameLoser = findViewById(R.id.loser);
        TextView scoreWinner = findViewById(R.id.score_winner);
        TextView scoreLoser = findViewById(R.id.score_loser);

        if(p1score > p2score){
            nameWinner.setText(p1name);
            scoreWinner.setText(Integer.toString(p1score));
            nameLoser.setText(p2name);
            scoreLoser.setText(Integer.toString(p2score));
        } else {
            nameWinner.setText(p2name);
            scoreWinner.setText(Integer.toString(p2score));
            nameLoser.setText(p1name);
            scoreLoser.setText(Integer.toString(p1score));
        }

        Button exit = findViewById(R.id.exit_button);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO fix exit
                finish();
                Intent exitIntent = new Intent(Intent.ACTION_MAIN);
                exitIntent.addCategory(Intent.CATEGORY_HOME);
                exitIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(exitIntent);
            }
        });

        Button playAgain = findViewById(R.id.play_again);
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EndFrameSingleActivity.this, MainGameActivity.class));
            }
        });
    }
}
