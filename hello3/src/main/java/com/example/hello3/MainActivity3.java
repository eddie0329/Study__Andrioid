package com.example.hello3;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

  String imageDirection = "UP";
  ScrollView scrollViewUp;
  ScrollView scrollViewDown;
  ImageView imageView1;
  ImageView imageView2;
  BitmapDrawable bitmap;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.test1);

    // find scrollView and set horizontal bar
    scrollViewUp = findViewById(R.id.scrollViewUp);
    scrollViewUp.setHorizontalScrollBarEnabled(true);
    scrollViewDown = findViewById(R.id.scrollViewDown);
    scrollViewDown.setHorizontalScrollBarEnabled(true);

    // find imageView
    imageView1 = findViewById(R.id.imageView1);
    imageView2 = findViewById(R.id.imageView2);

    // set Image up
    Resources res = getResources();
    bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image1);
    int bitmapWidth = bitmap.getIntrinsicWidth();
    int bitmapHeight = bitmap.getIntrinsicHeight();
    imageView1.setImageDrawable(bitmap);
    imageView1.getLayoutParams().width = bitmapWidth;
    imageView1.getLayoutParams().height = bitmapHeight;
  }

  public void onClickUp(View view) {
    imageDirection = "UP";
    changeImage();
  }

  public void onClickDown(View view) {
    imageDirection = "DOWN";
    changeImage();
  }

  private void changeImage() {
    // set bitmap
    Resources res = getResources();
    bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image1);
    int bitmapWidth = bitmap.getIntrinsicWidth();
    int bitmapHeight = bitmap.getIntrinsicHeight();
    if (imageDirection.equals("UP")) {
      imageView1.setImageDrawable(bitmap);
      imageView2.setImageDrawable(null);
      imageView1.getLayoutParams().width = bitmapWidth;
      imageView1.getLayoutParams().height = bitmapHeight;
    } else {
      imageView2.setImageDrawable(bitmap);
      imageView1.setImageDrawable(null);
      imageView2.getLayoutParams().width = bitmapWidth;
      imageView2.getLayoutParams().height = bitmapHeight;
    }
  }
}