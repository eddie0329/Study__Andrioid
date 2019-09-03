package com.example.sqlite_practice2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    SQLiteOpenHelper helper;
    String dbName = "sf_file.db";
    int dbVersion = 1;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new SQLiteOpenHelper(this, dbName, null, dbVersion);

        try{
            db = helper.getWritableDatabase();
        } catch (SQLException e){
            e.printStackTrace();
            Log.d("myapp", "cannot open database");
            finish();
        }

        insert();

        select();

    }

    void insert() {
        db.execSQL("INSERT INTO mytable (name) VALUES('seo')");
        db.execSQL("INSERT INTO mytable (name) VALUES('park')");
        db.execSQL("INSERT INTO mytable (name) VALUES('choi')");
        db.execSQL("INSERT INTO mytable (name) VALUES('kim')");
        db.execSQL("INSERT INTO mytable (name) VALUES('lee')");
        db.execSQL("INSERT INTO mytable (name) VALUES('heo')");
        Log.d("myapp", "insert completed");
    }

    void select() {
        Cursor c = db.rawQuery("SELECT * FROM mytable", null);

        while (c.moveToNext()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            Log.d("myapp", "id: " + id + "   " + "name: " + name);
        }
    }
}
