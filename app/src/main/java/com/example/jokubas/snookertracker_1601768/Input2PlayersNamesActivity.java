package com.example.jokubas.snookertracker_1601768;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Input2PlayersNamesActivity extends AppCompatActivity {

    public static final String name1 = "pName1";
    public static final String name2 = "pName2";
    public static final String image1 = "image1";
    public static final String image2 = "image2";
    public static final int GET_FROM_GALLERY = 3;
    private int id = 0;
    private Bitmap p1Image = null;
    private Bitmap p2Image = null;
    private String[] extensions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input2_players_names);

        p1Image = BitmapFactory.decodeResource(getResources(),  R.drawable.p1);

        p2Image = BitmapFactory.decodeResource(getResources(),  R.drawable.p2);

        extensions = new String[2];
        Arrays.fill(extensions, "");
    }

    public void onClickPlay(View v) {
        // call the main function for playing the game
        // which contains the grid of all buttons and scores
        Intent intent = new Intent(Input2PlayersNamesActivity.this, MainGameActivity.class);

        putExtraName(intent, (EditText)findViewById(R.id.name1), name1);
        putExtraName(intent, (EditText)findViewById(R.id.name2), name2);


        addImage(intent, p1Image, image1, extensions[0]);
        addImage(intent, p2Image, image2, extensions[1]);

        startActivity(intent);
    }

    private void addImage(Intent intent, Bitmap image, String extraName, String extension) {
        if (image == null) return;

        intent.putExtra(extraName, image);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if(extension.equals(".jpg"))
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        else
            image.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();

        intent.putExtra(extraName, b);
    }

    public void onClickBrowsePhoto(View view) {
        id = view.getId();
        startActivityForResult(new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI),
                GET_FROM_GALLERY);
    }

    private void putExtraName(Intent intent, EditText textView, String extraName){
        String text = textView.getText().toString();
        if(text.equals(""))
            text = textView.getHint().toString();
        intent.putExtra(extraName, text);
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
                bitmap = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
                imageView.setImageBitmap(bitmap);

                int imgIdx = -1;
                switch (id) {
                    case R.id.imagep1t2:
                        imgIdx = 0;
                        p1Image = bitmap;
                        findViewById(R.id.textView2).setVisibility(View.INVISIBLE);
                        break;
                    case R.id.imagep2t2:
                        imgIdx = 1;
                        p2Image = bitmap;
                        findViewById(R.id.textView).setVisibility(View.INVISIBLE);
                        break;
                }

                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                if (cursor.moveToFirst() && imgIdx >= 0) {
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    extensions[imgIdx] = filePath.substring(filePath.lastIndexOf("."));
                }
                cursor.close();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
