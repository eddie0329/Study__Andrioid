package com.example.sqlite_practice3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    EditText etName, etAge, etAddress;
    Button btnInsert, btnUpdate, btnDelete, btnSelect;
    String dbName = "sql_student.db";
    MySQLiteOpenHelper helper;
    SQLiteDatabase db;
    int dbVersion = 1;
    String tableName = "student";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //find views
        tvResult = findViewById(R.id.tvResult);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etAddress = findViewById(R.id.etAddress);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnSelect = findViewById(R.id.btnSelect);

        helper = new MySQLiteOpenHelper(this, dbName, null, dbVersion);

        try {
            db = helper.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d("myapp", "cannot open the database");
            finish();
        }



        //Insert data
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String age = etAge.getText().toString().trim();
                String address = etAddress.getText().toString().trim();

                //exceptions
                if("".equals(name) || "".equals(age) || "".equals(address)) {
                    return;
                }

                int a;
                try {
                    a = Integer.parseInt(age);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return;
                }

                insert(name, a, address);
            }
        });

        //SELECT DATA
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvResult.setText("");
                select();
            }
        });


    } // end of onCreate

    void insert(String name, int age, String address) {
        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("age", age);
        values.put("address", address);

        long result = db.insert(tableName, null, values);

        tvResult.setText(result + "row INSERT 성공");
    }


    void select() {
        Cursor c = db.query(tableName, null, null,
                             null, null,null,null);


        while(c.moveToNext()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            int age = c.getInt(2);
            String address = c.getString(3);

            String result = "id: " + id + "   " + "name: " + name + "   " + "age: " + age + "   " +
                            "address: " + address;
            tvResult.append("\n" + result);
        }
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);


    }






}//end of activity
