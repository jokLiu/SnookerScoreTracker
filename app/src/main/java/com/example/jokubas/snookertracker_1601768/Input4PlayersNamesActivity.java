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
 * The type Input 4 players names activity.
 */
public class Input4PlayersNamesActivity extends AppCompatActivity {
    /**
     * The constant P1T1_IMAGE.
     */
    public static final String P1T1_IMAGE = "p1t1Image";
    /**
     * The constant P2T1_IMAGE.
     */
    public static final String P2T1_IMAGE = "p2t1Image";
    /**
     * The constant P1T2_IMAGE.
     */
    public static final String P1T2_IMAGE = "p1t2Image";
    /**
     * The constant P2T2_IMAGE.
     */
    public static final String P2T2_IMAGE = "p2t2Image";
    /**
     * The constant GET_FROM_GALLERY.
     */
    public static final int GET_FROM_GALLERY = 3;
    /**
     * The constant P_NAME1.
     */
    public static final String P_NAME1 = "pName1";
    /**
     * The constant P_NAME2.
     */
    public static final String P_NAME2 = "pName2";
    /**
     * The constant P_NAME3.
     */
    public static final String P_NAME3 = "pName3";
    /**
     * The constant P_NAME4.
     */
    public static final String P_NAME4 = "pName4";
    private int id = 0;
    private Bitmap p1T1Image = null;
    private Bitmap p2T1Image = null;
    private Bitmap p1T2Image = null;
    private Bitmap p2T2Image = null;
    private String[] extensions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input4_players_names);

        // get the initial images from the resources
        p1T1Image = p2T2Image = BitmapFactory.decodeResource(getResources(), R.drawable.p2);
        p2T1Image = p1T2Image = BitmapFactory.decodeResource(getResources(), R.drawable.p1);

        // init array for recording image extensions for later compression
        extensions = new String[4];
        Arrays.fill(extensions, "");
    }

    /**
     * On click start multi player game.
     *
     * @param v the v
     */
    public void onClickPlayMulti(View v) {

        Intent intent = new Intent(Input4PlayersNamesActivity.this, MainGameMultiActivity.class);

        // pass the names which were input by the user
        putExtraName(intent, (EditText) findViewById(R.id.name1_t1), P_NAME1);
        putExtraName(intent, (EditText) findViewById(R.id.name2_t1), P_NAME2);
        putExtraName(intent, (EditText) findViewById(R.id.name3_t2), P_NAME3);
        putExtraName(intent, (EditText) findViewById(R.id.name4_t2), P_NAME4);

        // pass the images which were uploaded by the user
        addImage(intent, p1T1Image, P1T1_IMAGE, extensions[0]);
        addImage(intent, p2T1Image, P2T1_IMAGE, extensions[1]);
        addImage(intent, p1T2Image, P1T2_IMAGE, extensions[2]);
        addImage(intent, p2T2Image, P2T2_IMAGE, extensions[3]);

        startActivity(intent);
    }

    /**
     *
     * @param intent  to the main game activity.
     * @param textView player name to be added.
     * @param extraName string for the extra text name.
     */
    private void putExtraName(Intent intent, EditText textView, String extraName) {
        String text = textView.getText().toString();
        if (text.equals(""))
            text = textView.getHint().toString();
        intent.putExtra(extraName, text);
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
        if (extension.equals(".jpg"))
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
    public void onClickBrowsePhotoMulti(View view) {
        id = view.getId();
        startActivityForResult(new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI),
                GET_FROM_GALLERY);

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
                    case R.id.imagep1t1:
                        p1T1Image = bitmap;
                        findViewById(R.id.upload).setVisibility(View.INVISIBLE);
                        imgIdx = 0;
                        break;
                    case R.id.imagep2t1:
                        p2T1Image = bitmap;
                        findViewById(R.id.upload2).setVisibility(View.INVISIBLE);
                        imgIdx = 1;
                        break;
                    case R.id.imagep1t2:
                        p1T2Image = bitmap;
                        findViewById(R.id.upload3).setVisibility(View.INVISIBLE);
                        imgIdx = 2;
                        break;
                    case R.id.imagep2t2:
                        p2T2Image = bitmap;
                        findViewById(R.id.upload4).setVisibility(View.INVISIBLE);
                        imgIdx = 3;
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
