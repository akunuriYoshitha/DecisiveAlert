package com.example.yoshi.decisivealert;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by yoshi on 12/4/2016.
 */

public class MyDatabase extends SQLiteOpenHelper{
    SQLiteDatabase db;

    public MyDatabase(Context context) {
        super(context, "MyDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Calls(ID text primary key, Value text);");
        ContentValues cv = new ContentValues();
        cv.put("ID", "1");
        cv.put("Value", "nobody");
        db.insert("Calls",null,cv);
        db.execSQL("create table Messages(ID text primary key, Value text);");
        cv.put("ID", "1");
        db.insert("Messages",null,cv);
        db.execSQL("create table NumCalls(ID text primary key, Value text);");
        cv.put("ID", "1");
        cv.put("Value", 3);
        db.insert("NumCalls",null,cv);
        db.execSQL("create table SendSMS(ID text primary key, Value text);");
        cv.put("ID", "1");
        cv.put("Value", "no");
        db.insert("SendSMS",null,cv);
        db.execSQL("create table SMSText(ID text primary key, Value text);");
        cv.put("ID", "1");
        cv.put("Value", "Busy!!! Please call later...");
        db.insert("SMSText",null,cv);
        db.execSQL("create table Manual(ID text primary key, Value text);");
        cv.put("ID", "1");
        cv.put("Value", "no");
        db.insert("Manual",null,cv);
        db.execSQL("create table Sleeping(ID text primary key, Value text);");
        cv.put("ID", "1");
        cv.put("Value", "no");
        db.insert("Sleeping",null,cv);
        db.execSQL("create table CustomContacts(Number text primary key);");

        db.execSQL("create table Callers(MobileNum text primary key, count text);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Calls");
        db.execSQL("drop table if exists Messages");
        db.execSQL("drop table if exists NumCalls");
        db.execSQL("drop table if exists SendSMS");
        db.execSQL("drop table if exists SMSText");
        db.execSQL("drop table if exists Manual");
        db.execSQL("drop table if exists Sleeping");
        db.execSQL("drop table if exists CustomContacts");

        db.execSQL("drop table if exists Callers");
        onCreate(db);
    }

    public int insertCustomContacts (String Number)
    {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Number", Number);
        if (db.insert("CustomContacts", null, cv) != -1)
            return 1;
        else
            return 0;
    }


    public  int insertCallers(String MobileNum, String count)
    {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("MobileNum", MobileNum);
        cv.put("count", count);
        if (db.insert("Callers", null, cv) != -1)
            return 1;
        else
            return 0;
    }

    public boolean updateCallersData(String MobileNum, String count)
    {
        db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("count", count);
        String where = "MobileNum = " + MobileNum;
        db.update("Callers", cv, where, null);

        //String[] whereArgs = new String[] {String.valueOf(id)};

//        int count = db.update(Table_name, cv, where, null);
//        Log.d("hhhh", String.valueOf(count));
        return true;

    }

    public Cursor getCallersData ()
    {
        db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select MobileNum, count from Callers;", null);
        return result;
    }


    public void truncateCallers()
    {
        db = this.getWritableDatabase();
        db.execSQL("delete from Callers");
        Log.d("pppp", "Table truncated");
    }


    public String getValue(String table_name)
    {
        db = this.getReadableDatabase();
//        ArrayList<String> usernameslist = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select Value from " + table_name, null);

        if (cursor.moveToFirst())
        {
                return cursor.getString(0);
        }
        return null;
    }

    public boolean updateData( String Table_name, String id, String Value)
    {
        db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Value", Value);
        String where = "id= 1";
        //String[] whereArgs = new String[] {String.valueOf(id)};

        int count = db.update(Table_name, cv, where, null);
        Log.d("hhhh", String.valueOf(count));
        return true;

    }
}
