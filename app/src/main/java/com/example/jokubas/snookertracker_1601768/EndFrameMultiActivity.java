package com.example.jokubas.snookertracker_1601768;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class EndFrameMultiActivity extends AppCompatActivity {

    public static String PLAYER1T1_NAME = "player1t1";
    public static String PLAYER2T1_NAME = "player2t1";
    public static String PLAYER1T2_NAME = "player1t2";
    public static String PLAYER2T2_NAME = "player2t2";

    public static String PLAYER1T1_SCORE = "player1t1Score";
    public static String PLAYER2T1_SCORE = "player2t1Score";
    public static String PLAYER1T2_SCORE = "player1t2Score";
    public static String PLAYER2T2_SCORE = "player2t2Score";

    public static String TEAM1_SCORE = "team1Score";
    public static String TEAM2_SCORE = "team2Score";

    public static String PLAYER1T1_IMAGE = "player1t1Image";
    public static String PLAYER2T1_IMAGE = "player2t1Image";
    public static String PLAYER1T2_IMAGE = "player1t2Image";
    public static String PLAYER2T2_IMAGE = "player2t2Image";

    private byte[] p1t1Image;
    private byte[] p2t1Image;
    private byte[] p1t2Image;
    private byte[] p2t2Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_frame_multi);

        // get the intent
        Intent intent = getIntent();

        // get players names from the game
        String p1t1name = intent.getStringExtra(PLAYER1T1_NAME);
        String p2t1name = intent.getStringExtra(PLAYER2T1_NAME);
        String p1t2name = intent.getStringExtra(PLAYER1T2_NAME);
        String p2t2name = intent.getStringExtra(PLAYER2T2_NAME);

        // get players scores from the game
        int p1t1score = intent.getIntExtra(PLAYER1T1_SCORE, 0);
        int p2t1score = intent.getIntExtra(PLAYER2T1_SCORE, 0);
        int p1t2score = intent.getIntExtra(PLAYER1T2_SCORE, 0);
        int p2t2score = intent.getIntExtra(PLAYER2T2_SCORE, 0);

        // get players images
        p1t1Image = intent.getByteArrayExtra(PLAYER1T1_IMAGE);
        p2t1Image = intent.getByteArrayExtra(PLAYER2T1_IMAGE);
        p1t2Image = intent.getByteArrayExtra(PLAYER1T2_IMAGE);
        p2t2Image = intent.getByteArrayExtra(PLAYER2T2_IMAGE);

        // add players to the list for ease of processing
        List<PlayerWrapper> players = new ArrayList<>();
        players.add(new PlayerWrapper(p1t1name, p1t1score));
        players.add(new PlayerWrapper(p2t1name, p2t1score));
        players.add(new PlayerWrapper(p1t2name, p1t2score));
        players.add(new PlayerWrapper(p2t2name, p2t2score));

        // set leaders based on their scores
        setLeaders(players);

        // get team scores from the game
        int team1Score = intent.getIntExtra(TEAM1_SCORE, 0);
        int team2Score = intent.getIntExtra(TEAM2_SCORE, 0);

        // set the winner team views based on the score
        if(team1Score > team2Score)
            setTeams(p1t1name, p2t1name, p1t2name, p2t2name,
                    p1t1Image, p2t1Image, p1t2Image, p2t2Image,
                    team1Score, team2Score);
        else
            setTeams(p1t2name, p2t2name, p1t1name, p2t1name,
                     p1t2Image, p2t2Image, p1t1Image, p2t1Image,
                    team2Score, team1Score);

        // setup the exit button listener
        Button exit = findViewById(R.id.exit_button2);
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

        // setup the play again button listener
        Button playAgain = findViewById(R.id.play_again2);
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EndFrameMultiActivity.this, MainGameMultiActivity.class));
            }
        });
    }

    private void setTeams(String p1winner, String p2winner,
                          String p1loser, String p2loser,
                          byte[] p1winnerImage, byte[] p2winnerImage,
                          byte[] p1loserImage, byte[] p2loserImage,
                          int scoreWin, int scoreLost){

        setTextView(p1winner, R.id.p1t1_name);
        setTextView(p2winner, R.id.p2t1_name);
        setTextView(p1loser, R.id.p3t2_name);
        setTextView(p2loser, R.id.p4t2_name);
        setTextView(Integer.toString(scoreWin), R.id.team1_score);
        setTextView(Integer.toString(scoreLost), R.id.team2_score);
        setImageView(p1winnerImage, R.id.p1t1);
        setImageView(p2winnerImage, R.id.p2t1);
        setImageView(p1loserImage, R.id.p3t2);
        setImageView(p2loserImage, R.id.p4t2);
    }

    private void setImageView(byte[] image, int imgID){
        if(image  != null ) {
            Bitmap bmp = BitmapFactory.decodeByteArray(image , 0, image.length);
            ImageView imgView = findViewById(imgID);
            imgView.setImageBitmap(bmp);
        }
    }

    private void setTextView(String text, int textID){
        TextView view = findViewById(textID);
        view.setText(text);
    }

    private void setLeaders(List<PlayerWrapper> players){
        Collections.sort(players,Collections.<PlayerWrapper>reverseOrder());
        List<TextView> names = new ArrayList<>();
        names.add((TextView)findViewById(R.id.place1_name));
        names.add((TextView)findViewById(R.id.place2_name));
        names.add((TextView)findViewById(R.id.place3_name));
        names.add((TextView)findViewById(R.id.place4_name));

        List<TextView> scores = new ArrayList<>();
        scores.add((TextView)findViewById(R.id.place1_score));
        scores.add((TextView)findViewById(R.id.place2_score));
        scores.add((TextView)findViewById(R.id.place3_score));
        scores.add((TextView)findViewById(R.id.place4_score));


        for(int i=0; i<4; i++){
            names.get(i).setText(players.get(i).name);
            scores.get(i).setText(Integer.toString(players.get(i).score));
        }


    }

    private class PlayerWrapper implements Comparable {
        String name;
        int score;

        public PlayerWrapper(String name, int score){
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(@NonNull Object o) {
            PlayerWrapper p = (PlayerWrapper)o;
            return this.score - p.score;
        }
    }

}


