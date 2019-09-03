package com.example.activity_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge;
    Button Button_ChangeView, Button_TerminateView, Button_Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button_ChangeView = findViewById(R.id.Button_ChangeView);
        Button_TerminateView = findViewById(R.id.Button_TermianteView);
        Button_Submit = findViewById(R.id.Button_Submit);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);


        Button_ChangeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);

                intent.putExtra("num1" ,1);
                intent.putExtra("num2", 2);
                intent.putExtra("num3", 3);

                if(etAge.getText().toString() == "" || etName.getText().toString() == "") {
                    Toast.makeText(view.getContext(), "Fill the all sheets", Toast.LENGTH_LONG);
                }
                else {
                    String name = etName.getText().toString();
                    int age = Integer.parseInt(etAge.getText().toString());

                    Person person = new Person(name, age);
                    intent.putExtra("Person", person);
                    startActivity(intent);
                }


            }
        });

        Button_TerminateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
