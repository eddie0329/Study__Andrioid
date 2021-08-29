package com.example.hello3;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

  ScrollView scrollView;
  ImageView imageView;
  BitmapDrawable bitmap;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.scroll_view);

    scrollView = findViewById(R.id.scrollView);
    imageView = findViewById(R.id.imageView);
    scrollView.setHorizontalScrollBarEnabled(true);

    Resources res = getResources();
    bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image1);
    int bitmapWidth = bitmap.getIntrinsicWidth();
    int bitmapHeight = bitmap.getIntrinsicHeight();

    imageView.setImageDrawable(bitmap);
    imageView.getLayoutParams().width = 10000;
    imageView.getLayoutParams().height = 200000;
  }

  public void onClick (View view) {
    changeImage();
  }

  private void changeImage() {
    Resources res = getResources();
    bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image2);
    int bitmapWidth = bitmap.getIntrinsicWidth();
    int bitmapHeight = bitmap.getIntrinsicHeight();
    imageView.setImageDrawable(bitmap);
    imageView.getLayoutParams().width = 1000;
    imageView.getLayoutParams().height = 200000;
  }
}