package com.example.hello3;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  int imageIndex = 0;
  ImageView imageView;
  ImageView imageView2;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.frame_view);

    imageView = findViewById(R.id.imageView);
    imageView2 = findViewById(R.id.imageView2);
  }

  private void changeImage() {
    if (imageIndex == 0) {
      imageView.setVisibility(View.VISIBLE);
      imageView2.setVisibility(View.INVISIBLE);
      imageIndex = 1;
    } else {
      imageView.setVisibility(View.INVISIBLE);
      imageView2.setVisibility(View.VISIBLE);
      imageIndex = 0;
    }
  }

  public void onClick(View v) {
    changeImage();
  }
}