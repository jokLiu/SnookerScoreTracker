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

public class MainGameMultiActivity extends AppCompatActivity {

    private ScoreTrackMulti score;
    private TextView p1T1Score;
    private TextView p2T1Score;
    private TextView p3T2Score;
    private TextView p4T2Score;
    private TextView t1Score;
    private TextView t2Score;
    private byte[] p1t1Image;
    private byte[] p2t1Image;
    private byte[] p1t2Image;
    private byte[] p2t2Image;

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
        TextView n1 = (TextView) findViewById(R.id.player1_name);
        TextView n2 = (TextView) findViewById(R.id.player2_name);
        TextView n3 = (TextView) findViewById(R.id.player3_name);
        TextView n4 = (TextView) findViewById(R.id.player4_name);

        // get the images
        p1t1Image = intent.getByteArrayExtra(Input4PlayersNamesActivity.imagep1t1);
        p2t1Image = intent.getByteArrayExtra(Input4PlayersNamesActivity.imagep2t1);
        p1t2Image = intent.getByteArrayExtra(Input4PlayersNamesActivity.imagep1t2);
        p2t2Image = intent.getByteArrayExtra(Input4PlayersNamesActivity.imagep2t2);

        // set the images received
        setImageView(p1t1Image, R.id.p1_t1_image);
        setImageView(p2t1Image, R.id.p2_t1_image);
        setImageView(p1t2Image, R.id.p3_t2_image);
        setImageView(p2t2Image, R.id.p4_t2_image);

        // set players names received
        // TODO If players names are empty leave the current names
        // TODO fix play again
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

        setRedBallsView();
        setPlayers();
        setButtons();
    }

    private void setRedBallsView() {
        int n = score.getNumberOfReds();
        setScoreText((TextView) findViewById(R.id.counterView), n);
    }


    private void setImageView(byte[] image, int imgID) {
        if (image != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            ImageView imgView = findViewById(imgID);
            imgView.setImageBitmap(bmp);
        }
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
                endGame();
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
        setRedBallsView();
        setButtons();
        setPlayers();
        checkGameEnded();
    }

    private void endGame() {
        Intent intent = new Intent(MainGameMultiActivity.this, EndFrameMultiActivity.class);
        intent.putExtra(EndFrameMultiActivity.PLAYER1T1_NAME, score.getPlayer1T1Name());
        intent.putExtra(EndFrameMultiActivity.PLAYER2T1_NAME, score.getPlayer2T1Name());
        intent.putExtra(EndFrameMultiActivity.PLAYER1T2_NAME, score.getPlayer1T2Name());
        intent.putExtra(EndFrameMultiActivity.PLAYER2T2_NAME, score.getPlayer2T2Name());

        intent.putExtra(EndFrameMultiActivity.PLAYER1T1_SCORE, score.getPlayer1T1Score());
        intent.putExtra(EndFrameMultiActivity.PLAYER2T1_SCORE, score.getPlayer2T1Score());
        intent.putExtra(EndFrameMultiActivity.PLAYER1T2_SCORE, score.getPlayer1T2Score());
        intent.putExtra(EndFrameMultiActivity.PLAYER2T2_SCORE, score.getPlayer2T2Score());

        intent.putExtra(EndFrameMultiActivity.TEAM1_SCORE, score.getTeam1Score());
        intent.putExtra(EndFrameMultiActivity.TEAM2_SCORE, score.getTeam2Score());

        intent.putExtra(EndFrameMultiActivity.PLAYER1T1_IMAGE, p1t1Image);
        intent.putExtra(EndFrameMultiActivity.PLAYER2T1_IMAGE, p2t1Image);
        intent.putExtra(EndFrameMultiActivity.PLAYER1T2_IMAGE, p1t2Image);
        intent.putExtra(EndFrameMultiActivity.PLAYER2T2_IMAGE, p2t2Image);

        // TODO pass the images
        startActivity(intent);
        // TODO
    }

    private void setScoreText(TextView v, int score) {
        v.setText(String.format(Locale.US, "%d", score));
    }


    private void checkGameEnded() {
        if (score.checkGameEnded())
            endGame();
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
