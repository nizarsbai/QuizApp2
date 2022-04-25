package com.sbai.quizapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
public class Camera extends AppCompatActivity {
    Button btnCaptureImage;
    ImageView imageDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        btnCaptureImage=(Button)findViewById(R.id.btn_captureImage);
        imageDisplay=(ImageView) findViewById(R.id.imageCapture);
        btnCaptureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((MediaStore.ACTION_IMAGE_CAPTURE));
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode,resultCode,data);
        Bitmap bitmap =(Bitmap) data.getExtras().get("data");
        imageDisplay.setImageBitmap(bitmap);
    }
}