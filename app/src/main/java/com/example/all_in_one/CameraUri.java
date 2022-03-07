package com.example.all_in_one;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CameraUri extends AppCompatActivity {

    private static final int pic_id = 123;
    ImageView click_image_id;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
    private int REQUEST_CODE_PERMISSIONS = 1001;
    private String imageFilePath;
    private boolean isUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_uri);

        click_image_id = findViewById(R.id.click_image);

        Intent intent = getIntent();
        isUri = intent.getBooleanExtra("uri", false);
        if(allPermissionsGranted()) {

            intentOperations(); //start camera if permission has been granted by user
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp;
        File storageDir =
                getFilesDir();
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        imageFilePath = image.getAbsolutePath();
        return image;
    }

    protected void intentOperations() {
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (isUri) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.e("error", ex.toString());
            }
            if (photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(this, this.getPackageName() + ".fileprovider", photoFile);
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            } else {
                Log.e("error", "photo null");
            }
        }
        startActivityForResult(camera_intent, pic_id);
    }

    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pic_id) {
            Bitmap photo;

            if (isUri) {
                photo = BitmapFactory.decodeFile(imageFilePath);
            } else {
                photo = (Bitmap) data.getExtras()
                        .get("data");
            }

            click_image_id.setImageBitmap(photo);
        }
    }

    private boolean allPermissionsGranted() {

        for(String permission : REQUIRED_PERMISSIONS){
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                intentOperations(); //start camera if permission has been granted by user
            } else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        }
    }
}