package com.example.travelguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE USER(EMAIL TEXT PRIMARY KEY,FNAME TEXT, LNAME TEXT," +
                " PASSWORD TEXT,PDEST TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertUser(User user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", user.getEmail());
        contentValues.put("FNAME", user.getFirstname());
        contentValues.put("LNAME", user.getLastname());
        contentValues.put("PASSWORD", user.getPassword());
        contentValues.put("PDEST", user.getpDestination());
        sqLiteDatabase.insert("USER", null, contentValues);
    }
    public Cursor getAllUsers() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM USER", null);
    }
    public Cursor getUser(String key) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM USER WHERE email=\'"
                + key+"\'", null);
    }
    public int editUser(User user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", user.getEmail());
        contentValues.put("FNAME", user.getFirstname());
        contentValues.put("LNAME", user.getLastname());
        contentValues.put("PASSWORD", user.getPassword());
        contentValues.put("PDEST", user.getpDestination());
        return sqLiteDatabase.update("USER", contentValues, "email=\'"
                +user.getEmail()+"\'", null);
    }
}