package com.example.thread_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //* Thread 클래스(run 함수의 구현 포함)를 별도로 정의하고,
    // Thread의 객체를 메인 Activity내에서 생성하고 Tread를 start시킴.

    int mainValue = 0;
    int backValue1 = 0;
    int backValue2 = 0;
    TextView tvMainValue;
    TextView tvBackValue1;
    TextView tvBackValue2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMainValue = (TextView)findViewById(R.id.tvMainValue);
        tvBackValue1 = (TextView)findViewById(R.id.tvBackValue1);
        tvBackValue2 = (TextView)findViewById(R.id.tvBackValue2);

        // 일반적인 자바 프로그래밍에서는 메인스레드가 종료되면, 작업스레도도 잘(?) 종료되지만
        // 안드로이드 액티비티에선 메인스레드가 종료되도 (어플이 종료되도) 작업스레드가
        // 종료되지 않는 경우가 있습니다.  그래서 setDaemon(true) 메소드를 통해
        // 메인스레드와 종료동기화를 시킵니다.

        // TODO


    } // end onCreate

    public void mOnClick(View v){
        mainValue++;
        tvMainValue
    } // end mOnClick()


    // TODO


    // TODO


}
