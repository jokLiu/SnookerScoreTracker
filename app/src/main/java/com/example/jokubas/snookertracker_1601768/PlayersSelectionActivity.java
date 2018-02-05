package com.example.jokubas.snookertracker_1601768;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * The type Players selection activity.
 */
public class PlayersSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_selection);
    }


    /**
     * On 2 players click.
     * Method is called when a user decides to play 2 player snooker.
     *
     * @param view the view
     */
    public void on2PlayersClick(View view) {
        Intent intent = new Intent(PlayersSelectionActivity.this, Input2PlayersNamesActivity.class);
        startActivity(intent);
    }

    /**
     * On 4 players click.
     * Method is called when a user decides to play 4 player snooker.
     *
     * @param view the view
     */
    public void on4PlayersClick(View view) {
        Intent intent = new Intent(PlayersSelectionActivity.this, Input4PlayersNamesActivity.class);
        startActivity(intent);
    }
}
