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

/**
 * The type Input 2 players names activity.
 */
public class Input2PlayersNamesActivity extends AppCompatActivity {

    /**
     * The constant P_NAME1.
     */
    public static final String P_NAME1 = "pName1";
    /**
     * The constant P_NAME2.
     */
    public static final String P_NAME2 = "pName2";
    /**
     * The constant image1.
     */
    public static final String image1 = "image1";
    /**
     * The constant image2.
     */
    public static final String image2 = "image2";

    /**
     * The constant GET_FROM_GALLERY.
     */
    public static final int GET_FROM_GALLERY = 3;
    private int id = 0;
    private Bitmap p1Image = null;
    private Bitmap p2Image = null;
    private String[] extensions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input2_players_names);

        // get the initial images from the resources
        p1Image = BitmapFactory.decodeResource(getResources(),  R.drawable.p1);
        p2Image = BitmapFactory.decodeResource(getResources(),  R.drawable.p2);

        // init array for recording image extensions for later compression
        extensions = new String[2];
        Arrays.fill(extensions, "");
    }

    /**
     * On click play.
     * When play button is clicked the game recording is started.
     *
     * @param v the view
     */
    public void onClickPlay(View v) {
        Intent intent = new Intent(Input2PlayersNamesActivity.this, MainGameActivity.class);

        // pass the names which were input by the user
        putExtraName(intent, (EditText)findViewById(R.id.name1), P_NAME1);
        putExtraName(intent, (EditText)findViewById(R.id.name2), P_NAME2);

        // pass the images which were uploaded by the user
        addImage(intent, p1Image, image1, extensions[0]);
        addImage(intent, p2Image, image2, extensions[1]);

        startActivity(intent);
    }

    /**
     *
     * @param intent to the main game activity.
     * @param image image to be added as extra.
     * @param extraName string for the extra image name.
     * @param extension image extension for the image compression.
     */
    private void addImage(Intent intent, Bitmap image, String extraName, String extension) {
        if (image == null) return;
        intent.putExtra(extraName, image);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if(extension.equals(".jpg"))
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        else
            image.compress(Bitmap.CompressFormat.PNG, 50, baos);
        byte[] b = baos.toByteArray();

        intent.putExtra(extraName, b);
    }

    /**
     * On click browse photo.
     * Upload avatar photos of the users.
     *
     * @param view the view
     */
    public void onClickBrowsePhoto(View view) {
        id = view.getId();
        startActivityForResult(new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI),
                GET_FROM_GALLERY);
    }

    /**
     *
     * @param intent  to the main game activity.
     * @param textView player name to be added.
     * @param extraName string for the extra text name.
     */
    private void putExtraName(Intent intent, EditText textView, String extraName){
        String text = textView.getText().toString();
        if(text.equals(""))
            text = textView.getHint().toString();
        intent.putExtra(extraName, text);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // After the image was loaded process it
        if (requestCode == GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ImageView imageView = findViewById(id);
                bitmap = Bitmap.createScaledBitmap(bitmap, imageView.getWidth(), imageView.getHeight(), true);
                imageView.setImageBitmap(bitmap);

                // record the image selected
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

                // get the current image extension for later compression
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
