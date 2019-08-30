package com.rohit.pg.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.rohit.pg.R;

public class image_show extends AppCompatActivity {

    ImageView imageView,back,share;
    Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);

        imageView = findViewById(R.id.set_image);
        back = findViewById(R.id.back);
        share = findViewById(R.id.share);

        /*//back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),show_details.class);
                startActivity(intent);
            }
        });*/


        //share button
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_STREAM, Uri.parse(String.valueOf(image)));
                startActivity(Intent.createChooser(i,"Share Image"));

            }
        });


        Intent i = getIntent();
        Bundle b = i.getExtras();
        if(b != null)
        {
            byte[] bitmap = (byte[]) b.get("image");
            image = BitmapFactory.decodeByteArray(bitmap,0,bitmap.length);
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(bitmap,0,bitmap.length));
        }

    }
}
