package com.example.jokubas.snookertracker_1601768;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PlayersSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_selection);
    }


    public void on2PlayersClick(View view) {
        Intent intent = new Intent(PlayersSelectionActivity.this, Input2PlayersNamesActivity.class);
        startActivity(intent);
        // Should turn on the window with 2 names to input
    }

    public void on4PlayersClick(View view) {
        Intent intent = new Intent(PlayersSelectionActivity.this, Input4PlayersNamesActivity.class);
        startActivity(intent);
        // should turn on the window with 4 names to input
    }
}
