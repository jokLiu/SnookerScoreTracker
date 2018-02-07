package com.example.jokubas.snookertracker_1601768;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * The type End frame single activity.
 */
public class EndFrameSingleActivity extends AppCompatActivity {

    /**
     * The constant PLAYER1_NAME.
     */
    public static String PLAYER1_NAME = "player1";
    /**
     * The constant PLAYER2_NAME.
     */
    public static String PLAYER2_NAME = "player2";
    /**
     * The constant PLAYER1_SCORE.
     */
    public static String PLAYER1_SCORE = "player1Score";
    /**
     * The constant PLAYER2_SCORE.
     */
    public static String PLAYER2_SCORE = "player2Score";
    /**
     * The constant PLAYER1_IMAGE.
     */
    public static String PLAYER1_IMAGE = "player1Image";
    /**
     * The constant PLAYER2_IMAGE.
     */
    public static String PLAYER2_IMAGE = "player2Image";

    private byte[] p1Image;
    private byte[] p2Image;
    private String p1name;
    private String p2name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_frame_single);

        // get the intent
        Intent intent = getIntent();

        // get players names from the game
        p1name = intent.getStringExtra(PLAYER1_NAME);
        p2name = intent.getStringExtra(PLAYER2_NAME);

        // get players scores from the game
        int p1score = intent.getIntExtra(PLAYER1_SCORE, 0);
        int p2score = intent.getIntExtra(PLAYER2_SCORE, 0);

        // get players images
        p1Image = intent.getByteArrayExtra(PLAYER1_IMAGE);
        p2Image = intent.getByteArrayExtra(PLAYER2_IMAGE);

        // get the views of the current layout
        TextView nameWinner = findViewById(R.id.winner);
        TextView nameLoser = findViewById(R.id.loser);
        TextView scoreWinner = findViewById(R.id.score_winner);
        TextView scoreLoser = findViewById(R.id.score_loser);
        ImageView imageWin = findViewById(R.id.win_img);
        ImageView imageLost = findViewById(R.id.lost_img);


        // set the views based on the score
        if (p1score > p2score) {
            nameWinner.setText(p1name);
            scoreWinner.setText(Integer.toString(p1score));
            Bitmap bmp1 = BitmapFactory.decodeByteArray(p1Image, 0, p1Image.length);
            imageWin.setImageBitmap(bmp1);

            nameLoser.setText(p2name);
            scoreLoser.setText(Integer.toString(p2score));
            Bitmap bmp2 = BitmapFactory.decodeByteArray(p2Image, 0, p2Image.length);
            imageLost.setImageBitmap(bmp2);
            imageLost.setAlpha(0.5f);

        } else {
            nameWinner.setText(p2name);
            scoreWinner.setText(Integer.toString(p2score));
            Bitmap bmp1 = BitmapFactory.decodeByteArray(p2Image, 0, p2Image.length);
            imageWin.setImageBitmap(bmp1);

            nameLoser.setText(p1name);
            scoreLoser.setText(Integer.toString(p1score));
            Bitmap bmp2 = BitmapFactory.decodeByteArray(p1Image, 0, p1Image.length);
            imageLost.setImageBitmap(bmp2);
            imageLost.setAlpha(0.5f);
        }

        // exit button for leaving the game
        Button exit = findViewById(R.id.exit_button);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exitIntent = new Intent(EndFrameSingleActivity.this, PlayersSelectionActivity.class);
                startActivity(exitIntent);
            }
        });

    }


    /**
     * On click play again single game.
     * all the images and names are passed to the game.
     *
     * @param v the v
     */
    public void onClickPlayAgainSingle(View v) {
        Intent intent = new Intent(EndFrameSingleActivity.this, MainGameActivity.class);

        intent.putExtra(Input2PlayersNamesActivity.P_NAME1, p1name);
        intent.putExtra(Input2PlayersNamesActivity.P_NAME2, p2name);

        intent.putExtra(Input2PlayersNamesActivity.image1, p1Image);
        intent.putExtra(Input2PlayersNamesActivity.image2, p2Image);

        startActivity(intent);
    }
}
