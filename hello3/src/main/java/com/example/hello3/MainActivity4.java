package com.example.hello3;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;

public class MainActivity4 extends AppCompatActivity {
  EditText inputText;
  TextView inputCount;
  Button buttonSend;
  Button buttonClose;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.test2);

    // find inputText and inputCount
    inputText = findViewById(R.id.inputText);
    inputCount = findViewById(R.id.inputCount);
    // add watcher listener for input
    TextWatcher textWatcher = new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence str, int start, int count, int after) {
        byte[] bytes = null;
        try {
          bytes = str.toString().getBytes("KSC5601");
          int strCount = bytes.length;
          inputCount.setText(strCount + " / 80바이트");
        } catch(UnsupportedEncodingException ex) {
          ex.printStackTrace();
        }
      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {}

      @Override
      public void afterTextChanged(Editable strEditable) {
        String str = strEditable.toString();
        try {
          byte[] strBytes = str.getBytes("KSC5601");
          if(strBytes.length > 80) {
            strEditable.delete(strEditable.length()-2, strEditable.length()-1);
          }
        } catch(Exception ex) {
          ex.printStackTrace();
        }
      }
    };

    // find button
    buttonSend = findViewById(R.id.buttonSend);
    buttonClose = findViewById(R.id.buttonClose);
    // add event listener for button send
    buttonSend.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String message = inputText.getText().toString();
        Toast.makeText(getApplicationContext(), "전송할 메세지 \n" + message, Toast.LENGTH_LONG).show();
      }
    });
    // add event listener for button close
    buttonClose.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });

  }
}