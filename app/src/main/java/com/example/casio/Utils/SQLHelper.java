package com.example.casio.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.casio.models.User;


import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLHelper";
    static final String DB_NAME = "Database.db";
    static final String DB_USER_TABLE = "USER";
    static final String ID = "id";
    static final String USER_ID = "user_id";
    static final String USERNAME = "username";
    static final String PASSWORD = "password";
    static final int DB_VERSION = 1;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateUserTable = "CREATE TABLE USER ( " +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "username Text NOT NULL," +
                "password Text NOT NULL)";
        db.execSQL(queryCreateUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("drop table if exists " + DB_USER_TABLE);
            onCreate(db);
        }
    }

    public void insertUser(String username, String password) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put(USERNAME, username);
        contentValues.put(PASSWORD, password);
 
        sqLiteDatabase.insert(DB_USER_TABLE, null, contentValues);
        closeDB();
    }

    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();

        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.query(false, DB_USER_TABLE, null, null, null
                , null, null, null, null);
        if (cursor!=null&&cursor.getColumnCount()!=0){
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(ID));
                String username = cursor.getString(cursor.getColumnIndex(USERNAME));
                String password = cursor.getString(cursor.getColumnIndex(PASSWORD));
//            String date = cursor.getString(cursor.getColumnIndex("date"));
                users.add(new User(id,username,password));
            }
        }
        closeDB();
        return users;
    }
    public User getUser(String userName) {
        User user = new User();

        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM USER WHERE TRIM(username) = '"+ userName.trim()+"'", null);
        if (cursor!=null&&cursor.getColumnCount()!=0){
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(ID));
                String username = cursor.getString(cursor.getColumnIndex(USERNAME));
                String password = cursor.getString(cursor.getColumnIndex(PASSWORD));
//            String date = cursor.getString(cursor.getColumnIndex("date"));
                user =new User(id,username,password);
            }
        }
        closeDB();
        return user;
    }

    private void closeDB() {
        if (sqLiteDatabase != null) sqLiteDatabase.close();
        if (contentValues != null) contentValues.clear();
        if (cursor != null) cursor.close();
    }
}
