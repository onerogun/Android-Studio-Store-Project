package com.og.prj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HandleDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "user.db";
    public static final String TABLE_NAME = "user";
    public  static final String COLUMN_EMAIL = "email";
    public static final  String COLUMN_PASSWORD = "password";

    public HandleDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_EMAIL + " TEXT, " + COLUMN_PASSWORD + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());
        SQLiteDatabase database = getWritableDatabase();
        database.insert(TABLE_NAME,null,values);
        database.close();
    }

    public void deleteUser(String userName) {
        SQLiteDatabase database = getWritableDatabase();

        database.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_EMAIL + "=\"" + userName  + "\";");
    }

    public boolean userExists(User user) {
        SQLiteDatabase database = getReadableDatabase();
        String query = "SELECT " + COLUMN_EMAIL + " FROM " + TABLE_NAME + " WHERE " + COLUMN_EMAIL + "=\"" + user.getEmail()  + "\" " +     "AND "   + COLUMN_PASSWORD + "=\"" + user.getPassword() + "\";";

        Cursor c = database.rawQuery(query,null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_EMAIL)) != null) {
                return true;
            }
            c.moveToNext();
        }
        return false;

    }
}
