package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ThreadImageActivity extends AppCompatActivity {


    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_image);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    final ImageView imageView = findViewById(R.id.ImageView);

                    URL url = new URL("https://post-phinf.pstatic.net/MjAxOTAzMjRfMjcz/MDAxNTUzMzkyNTQzMTkw.GWB0EUeTZ9fPX_OugeSwTStOUmNUq0zcracKr4hGR7kg.8zoty2s6foMP7jtTxLzIx5uWMfGSikM9WLAUQEOGYw0g.JPEG/3-1.jpg?type=w1200");
                    InputStream is = url.openStream();

                    final Bitmap bm = BitmapFactory.decodeStream(is);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bm);
                        }
                    });


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }




            }
        });

        t.start();
    }
}
