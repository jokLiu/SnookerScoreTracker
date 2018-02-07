package com.example.jokubas.snookertracker_1601768;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type End frame multi activity.
 */
public class EndFrameMultiActivity extends AppCompatActivity {

    /**
     * The constant PLAYER1T1_NAME.
     */
    public static String PLAYER1T1_NAME = "player1t1";
    /**
     * The constant PLAYER2T1_NAME.
     */
    public static String PLAYER2T1_NAME = "player2t1";
    /**
     * The constant PLAYER1T2_NAME.
     */
    public static String PLAYER1T2_NAME = "player1t2";
    /**
     * The constant PLAYER2T2_NAME.
     */
    public static String PLAYER2T2_NAME = "player2t2";

    /**
     * The constant PLAYER1T1_SCORE.
     */
    public static String PLAYER1T1_SCORE = "player1t1Score";
    /**
     * The constant PLAYER2T1_SCORE.
     */
    public static String PLAYER2T1_SCORE = "player2t1Score";
    /**
     * The constant PLAYER1T2_SCORE.
     */
    public static String PLAYER1T2_SCORE = "player1t2Score";
    /**
     * The constant PLAYER2T2_SCORE.
     */
    public static String PLAYER2T2_SCORE = "player2t2Score";

    /**
     * The constant TEAM1_SCORE.
     */
    public static String TEAM1_SCORE = "team1Score";
    /**
     * The constant TEAM2_SCORE.
     */
    public static String TEAM2_SCORE = "team2Score";

    /**
     * The constant PLAYER1T1_IMAGE.
     */
    public static String PLAYER1T1_IMAGE = "player1t1Image";
    /**
     * The constant PLAYER2T1_IMAGE.
     */
    public static String PLAYER2T1_IMAGE = "player2t1Image";
    /**
     * The constant PLAYER1T2_IMAGE.
     */
    public static String PLAYER1T2_IMAGE = "player1t2Image";
    /**
     * The constant PLAYER2T2_IMAGE.
     */
    public static String PLAYER2T2_IMAGE = "player2t2Image";

    private byte[] p1t1Image;
    private byte[] p2t1Image;
    private byte[] p1t2Image;
    private byte[] p2t2Image;

    private String p1t1name;
    private String p2t1name;
    private String p1t2name;
    private String p2t2name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_frame_multi);

        // get the intent
        Intent intent = getIntent();

        // get players names from the game
        p1t1name = intent.getStringExtra(PLAYER1T1_NAME);
        p2t1name = intent.getStringExtra(PLAYER2T1_NAME);
        p1t2name = intent.getStringExtra(PLAYER1T2_NAME);
        p2t2name = intent.getStringExtra(PLAYER2T2_NAME);

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
        if (team1Score > team2Score)
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
                Intent exitIntent = new Intent(EndFrameMultiActivity.this, PlayersSelectionActivity.class);
                startActivity(exitIntent);

            }
        });


    }

    /**
     * On click play again single game.
     * all the images and names are passed to the game.
     *
     * @param v the view
     */
    public void onClickPlayAgain(View v) {
        Intent intent = new Intent(EndFrameMultiActivity.this, MainGameMultiActivity.class);

        intent.putExtra(Input4PlayersNamesActivity.P_NAME1, p1t1name);
        intent.putExtra(Input4PlayersNamesActivity.P_NAME2, p2t1name);
        intent.putExtra(Input4PlayersNamesActivity.P_NAME3, p1t2name);
        intent.putExtra(Input4PlayersNamesActivity.P_NAME4, p2t2name);

        addImage(intent, p1t1Image, Input4PlayersNamesActivity.P1T1_IMAGE);
        addImage(intent, p2t1Image, Input4PlayersNamesActivity.P2T1_IMAGE);
        addImage(intent, p1t2Image, Input4PlayersNamesActivity.P1T2_IMAGE);
        addImage(intent, p2t2Image, Input4PlayersNamesActivity.P2T2_IMAGE);

        startActivity(intent);
    }

    /**
     * @param intent    to the main game activity.
     * @param image     image to be added as extra.
     * @param extraName string for the extra image name.
     */
    private void addImage(Intent intent, byte[] image, String extraName) {
        intent.putExtra(extraName, image);
    }


    private void setTeams(String p1winner, String p2winner,
                          String p1loser, String p2loser,
                          byte[] p1winnerImage, byte[] p2winnerImage,
                          byte[] p1loserImage, byte[] p2loserImage,
                          int scoreWin, int scoreLost) {

        setTextView(p1winner, R.id.p1t1_name);
        setTextView(p2winner, R.id.p2t1_name);
        setTextView(p1loser, R.id.p3t2_name);
        setTextView(p2loser, R.id.p4t2_name);
        setTextView(Integer.toString(scoreWin), R.id.team1_score);
        setTextView(Integer.toString(scoreLost), R.id.team2_score);
        setImageView(p1winnerImage, R.id.p1t1, false);
        setImageView(p2winnerImage, R.id.p2t1, false);
        setImageView(p1loserImage, R.id.p3t2, true);
        setImageView(p2loserImage, R.id.p4t2, true);
    }

    /**
     *
     * @param image the image to be set
     * @param imgID id for the view
     * @param blur the boolean to determine if the image should be blured
     */
    private void setImageView(byte[] image, int imgID, boolean blur) {
        if (image != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            ImageView imgView = findViewById(imgID);
            imgView.setImageBitmap(bmp);
            if (blur) imgView.setAlpha(0.5f);
        }
    }

    /**
     *
     * @param text the text to be set
     * @param textID the view id
     */
    private void setTextView(String text, int textID) {
        TextView view = findViewById(textID);
        view.setText(text);
    }

    /**
     * Sets the leaders based on their individual score
     * 
     * @param players of the game
     */
    private void setLeaders(List<PlayerWrapper> players) {
        Collections.sort(players, Collections.<PlayerWrapper>reverseOrder());
        List<TextView> names = new ArrayList<>();
        names.add((TextView) findViewById(R.id.place1_name));
        names.add((TextView) findViewById(R.id.place2_name));
        names.add((TextView) findViewById(R.id.place3_name));
        names.add((TextView) findViewById(R.id.place4_name));

        List<TextView> scores = new ArrayList<>();
        scores.add((TextView) findViewById(R.id.place1_score));
        scores.add((TextView) findViewById(R.id.place2_score));
        scores.add((TextView) findViewById(R.id.place3_score));
        scores.add((TextView) findViewById(R.id.place4_score));


        for (int i = 0; i < 4; i++) {
            names.get(i).setText(players.get(i).name);
            scores.get(i).setText(Integer.toString(players.get(i).score));
        }


    }

    /**
     * Inner class for wrapping up the player information.
     */
    private class PlayerWrapper implements Comparable {
        /**
         * The Name.
         */
        String name;
        /**
         * The Score.
         */
        int score;

        /**
         * Instantiates a new Player wrapper.
         *
         * @param name  the name
         * @param score the score
         */
        public PlayerWrapper(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(@NonNull Object o) {
            PlayerWrapper p = (PlayerWrapper) o;
            return this.score - p.score;
        }
    }

}


