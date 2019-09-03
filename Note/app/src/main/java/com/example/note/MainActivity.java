package com.example.note;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView ImageView, ImageView2, ImageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView = findViewById(R.id.ImageView);
        ImageView2 = findViewById(R.id.ImageView2);
        ImageView3 = findViewById(R.id.ImageView3);


        //방법 1
        ImageView.setImageResource(R.drawable.ic_launcher_background);

        //방법 2
        Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher_background);
        ImageView2.setImageDrawable(drawable);


        //방법 3
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);
        ImageView3.setImageBitmap(bitmap);

    }
}
