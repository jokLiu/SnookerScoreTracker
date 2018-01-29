package com.example.jokubas.snookertracker_1601768;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Input2PlayersNamesActivity extends AppCompatActivity {

    public static String name1 = "pName1";
    public static String name2 = "pName2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input2_players_names);
    }

    public void onClickPlay(View v){
        // call the main function for playing the game
        // which contains the grid of all buttons and scores
        Intent intent = new Intent(Input2PlayersNamesActivity.this, MainGameActivity.class);

        EditText n1 = (EditText) findViewById(R.id.name1);
        EditText n2 = (EditText) findViewById(R.id.name2);

        intent.putExtra(name1, n1.getText().toString());
        intent.putExtra(name2, n2.getText().toString());

        startActivity(intent);
    }
}
