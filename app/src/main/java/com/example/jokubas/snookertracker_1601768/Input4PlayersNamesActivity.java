package com.example.jokubas.snookertracker_1601768;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Input4PlayersNamesActivity extends AppCompatActivity {
    public static String name1 = "pName1";
    public static String name2 = "pName2";
    public static String name3 = "pName3";
    public static String name4 = "pName4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input4_players_names);
    }

    public void onClickPlayMulti(View v){
        // call the main function for playing the game
        // which contains the grid of all buttons and scores
        Intent intent = new Intent(Input4PlayersNamesActivity.this, MainGameMultiActivity.class);

//        EditText n1 = (EditText) findViewById(R.id.name1);
//        EditText n2 = (EditText) findViewById(R.id.name2);
//        EditText n3 = (EditText) findViewById(R.id.name3);
//        EditText n4 = (EditText) findViewById(R.id.name4);
//
//        intent.putExtra(name1, n1.getText().toString());
//        intent.putExtra(name2, n2.getText().toString());
//        intent.putExtra(name3, n3.getText().toString());
//        intent.putExtra(name4, n4.getText().toString());

        startActivity(intent);
    }
}
