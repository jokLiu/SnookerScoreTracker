package com.example.jokubas.snookertracker_1601768;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Input4PlayersNamesActivity extends AppCompatActivity {
    public static String name1 = "pName1";
    public static String name2 = "pName2";
    public static String name3 = "pName3";
    public static String name4 = "pName4";
    public static final String imagep1t1 = "p1t1Image";
    public static final String imagep2t1 = "p2t1Image";
    public static final String imagep1t2 = "p1t2Image";
    public static final String imagep2t2 = "p2t2Image";
    public static final int GET_FROM_GALLERY = 3;
    private int id = 0;
    private Bitmap p1T1Image = null;
    private Bitmap p2T1Image = null;
    private Bitmap p1T2Image = null;
    private Bitmap p2T2Image = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input4_players_names);

        p1T1Image = p2T2Image = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                R.drawable.p1);

        p2T1Image = p1T2Image = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                R.drawable.p2);
    }

    public void onClickPlayMulti(View v){
        // call the main function for playing the game
        // which contains the grid of all buttons and scores
        Intent intent = new Intent(Input4PlayersNamesActivity.this, MainGameMultiActivity.class);

        EditText n1 = (EditText) findViewById(R.id.name1_t1);
        EditText n2 = (EditText) findViewById(R.id.name2_t1);
        EditText n3 = (EditText) findViewById(R.id.name3_t2);
        EditText n4 = (EditText) findViewById(R.id.name4_t2);

        intent.putExtra(name1, n1.getText().toString());
        intent.putExtra(name2, n2.getText().toString());
        intent.putExtra(name3, n3.getText().toString());
        intent.putExtra(name4, n4.getText().toString());

        addImage(intent, p1T1Image, imagep1t1);
        addImage(intent, p2T1Image, imagep2t1);
        addImage(intent, p1T2Image, imagep1t2);
        addImage(intent, p2T2Image, imagep2t2);

        startActivity(intent);
    }


    private void addImage(Intent intent, Bitmap image, String extraName) {
        if (image == null) return;

        intent.putExtra(extraName, image);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();

        intent.putExtra(extraName, b);
    }

    public void onClickBrowsePhotoMulti(View view) {
        id = view.getId();
        startActivityForResult(new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI),
                GET_FROM_GALLERY);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if (requestCode == GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ImageView imageView = findViewById(id);

                bitmap = Bitmap.createScaledBitmap(bitmap, imageView.getWidth(), imageView.getHeight(), true);
                imageView.setImageBitmap(bitmap);

                switch (id) {
                    case R.id.imagep1t1:
                        p1T1Image = bitmap;
                        findViewById(R.id.upload).setVisibility(View.INVISIBLE);
                        break;
                    case R.id.imagep2t1:
                        p2T1Image = bitmap;
                        findViewById(R.id.upload2).setVisibility(View.INVISIBLE);
                        break;
                    case R.id.imagep1t2:
                        p1T2Image = bitmap;
                        findViewById(R.id.upload3).setVisibility(View.INVISIBLE);
                        break;
                    case R.id.imagep2t2:
                        p2T2Image = bitmap;
                        findViewById(R.id.upload4).setVisibility(View.INVISIBLE);
                        break;
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
