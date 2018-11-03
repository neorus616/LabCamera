package com.ariel.ckazakov.labcamera;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class CameraActivity extends AppCompatActivity {

    ImageView photo;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        TextView textView = findViewById(R.id.textView);
        String name = Objects.requireNonNull(super.getIntent().getExtras()).getString("name");
        textView.setText(String.format("Hello  %s", name));

        Button buttonCapture = findViewById(R.id.buttonCapture);
        photo = findViewById(R.id.photo);


        buttonCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) (data != null ? requireNonNull(data.getExtras()).get("data") : null);
        photo.setImageBitmap(bitmap);
    }
}
