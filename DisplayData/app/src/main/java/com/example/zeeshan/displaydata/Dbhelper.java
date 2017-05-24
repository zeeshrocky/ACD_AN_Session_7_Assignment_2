package com.example.zeeshan.displaydata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Dbhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Student.db";
    private static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";

    public Dbhelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DATABASE Created", "onCreate: ");
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS"+ TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name){
        SQLiteDatabase  db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COL_2,name);
        long result=db.insert(TABLE_NAME,null,values);
        if (result==-1) //return result != -1;
            return false;
        else
            return true;
    }
    public boolean deleteAllItems() {
        SQLiteDatabase  db=this.getWritableDatabase();
        int doneDelete;
        doneDelete = db.delete(TABLE_NAME, null , null);
        return doneDelete > 0;
    }
    public String[] SelectAllData() {
        try {
            String arrData[] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            String strSQL = "SELECT  NAME FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(strSQL, null);
            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()];
                    int i= 0;
                    do {
                        arrData[i] = cursor.getString(0);
                        i++;
                    } while (cursor.moveToNext());
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return arrData;
        } catch (Exception e) {

            return null;

        }
    }
}