package com.example.activity_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView TextView_Intent1, TextView_Intent2;
    Button Button_TeminateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();

        int a = intent.getIntExtra("num1", 0);
        int b = intent.getIntExtra("num2", 0);
        int c = intent.getIntExtra("num3", 0);

        TextView_Intent1 = findViewById(R.id.TextView_Intent1);
        Button_TeminateView = findViewById(R.id.Button_TermianteView);

        TextView_Intent1.setText(a + " : " + b + " : " + c);

        Button_TeminateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView_Intent2 = findViewById(R.id.TextView_Intent2);

        Person person = (Person)intent.getSerializableExtra("Person");

        TextView_Intent2.setText("Name: " + person.getName() + "      " +
                                "Age: " + person.getAge());

    }
}
