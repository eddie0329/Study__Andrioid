package com.example.sqlite_practice2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {
    public SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("myapp", "onCreate Function from sqlite called");

        String sql = "CREATE TABLE mytable (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT" +
                    ")"
                    ;
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("myapp", "onUpgrade Function from sqlite called");

        String sql = "DROP TABLE mytable";
        sqLiteDatabase.execSQL(sql);

        onCreate(sqLiteDatabase);
    }
}
