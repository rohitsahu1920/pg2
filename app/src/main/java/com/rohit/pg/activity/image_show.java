package com.rohit.pg.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.rohit.pg.R;

public class image_show extends AppCompatActivity {

    ImageView imageView,back,share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);

        imageView = findViewById(R.id.set_image);
        back = findViewById(R.id.back);
        share = findViewById(R.id.share);


        Intent i = getIntent();
        Bundle b = i.getExtras();
        if(b != null)
        {
            Bitmap bitmap = (Bitmap) b.get("image");
            imageView.setImageBitmap(bitmap);
        }

    }
}
