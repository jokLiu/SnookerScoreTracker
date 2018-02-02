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

import java.util.Locale;

public class MainGameActivity extends AppCompatActivity {

    private ScoreTrackSingle score;
    private TextView p1score;
    private TextView p2score;
    private byte[] player1Image;
    private byte[] player2Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        Intent intent = getIntent();

        String pName1 = intent.getStringExtra(Input2PlayersNamesActivity.name1);
        String pName2 = intent.getStringExtra(Input2PlayersNamesActivity.name2);

        player1Image = intent.getByteArrayExtra(Input2PlayersNamesActivity.image1);
        player2Image = intent.getByteArrayExtra(Input2PlayersNamesActivity.image2);

        if(player1Image  != null ) {
            Bitmap bmp1 = BitmapFactory.decodeByteArray(player1Image , 0, player1Image .length);
            ImageView imgView1 = findViewById(R.id.p1_image);
            imgView1.setImageBitmap(bmp1);
        }

        if(player2Image != null) {
            Bitmap bmp2 = BitmapFactory.decodeByteArray(player2Image, 0, player2Image.length);
            ImageView imgView2 = findViewById(R.id.p2_image);
            imgView2.setImageBitmap(bmp2);
        }

        TextView n1 = (TextView) findViewById(R.id.player1_name);
        TextView n2 = (TextView) findViewById(R.id.player3_name);

        n1.setText(pName1);
        n2.setText(pName2);

        p1score = (TextView) findViewById(R.id.score2_tm1);
        p2score = (TextView) findViewById(R.id.score3_tm2);

        score = new ScoreTrackSingle(pName1, pName2);

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

                Intent intent = new Intent(MainGameActivity.this, EndFrameSingleActivity.class);
                intent.putExtra(EndFrameSingleActivity.PLAYER1_NAME, score.getPlayer1T1Name());
                intent.putExtra(EndFrameSingleActivity.PLAYER2_NAME, score.getPlayer1T2Name());
                intent.putExtra(EndFrameSingleActivity.PLAYER1_SCORE, score.getPlayer1T1Score());
                intent.putExtra(EndFrameSingleActivity.PLAYER2_SCORE, score.getPlayer1T2Score());
                intent.putExtra(EndFrameSingleActivity.PLAYER1_IMAGE, player1Image);
                intent.putExtra(EndFrameSingleActivity.PLAYER2_IMAGE, player2Image);

                // TODO pass the images
                startActivity(intent);
                break;
            default:
                break;

        }

        p1score.setText(String.format(Locale.US, "%d", score.getPlayer1T1Score()));
        p2score.setText(String.format(Locale.US, "%d", score.getPlayer1T2Score()));
        setButtons();
        setPlayers();
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

    private void setPlayers(){
        int id = score.mapTurnToPlayerImageID();

        // player 1 image
        ImageView im = (ImageView) findViewById(R.id.p1_image);
        if(R.id.p1_image==id) {
            im.setAlpha(1f);
        } else {
            im.setAlpha(0.5f);
        }

        // player 2 image
        ImageView im2 = (ImageView)  findViewById(R.id.p2_image);
        if(R.id.p2_image==id) {
            im2.setAlpha(1f);
        } else {
            im2.setAlpha(0.5f);
        }

    }
}
