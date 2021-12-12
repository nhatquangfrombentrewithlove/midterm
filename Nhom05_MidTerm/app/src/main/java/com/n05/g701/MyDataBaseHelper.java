package com.n05.g701;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {

        public static final int DB_VERSION = 1;
        public static final String DB_NAME = "product_db.sqlite";

        public static final String TBL_NAME = "Product_Table";
        public static final String COL_TASK_ID = "Product_Id";
        public static final String COL_TASK_NAME = "Product_Name";
        public static final String COL_TASK_HangSX = "Product_HangSX";
        public static final String COL_TASK_GIABAN = "Product_GiaBan";
        public static final String COL_TASK_PHOTO = "Product_Photo";


    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + "(" + COL_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TASK_NAME + " VARCHAR(200), " + COL_TASK_HangSX + " VARCHAR(100), " + COL_TASK_GIABAN + " REAL, " + COL_TASK_PHOTO + " BLOB)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TBL_NAME);
        onCreate(sqLiteDatabase);
    }
    public Cursor getData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql,null);
    }
    public void insertData(String productName, String HangSX, double GiaBan, byte[] Photo) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = "INSERT INTO " + MyDataBaseHelper.TBL_NAME + " VALUES(null, ?, ?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, productName);
            statement.bindString(2,HangSX);
            statement.bindDouble(3,GiaBan);
            statement.bindBlob(4,Photo);

            statement.executeInsert();

            db.execSQL(sql);
        } catch (Exception ex) {
            Log.e("error: ", ex.getMessage().toString());
        }
    }
    public void deleteData(int id){
        try{
            SQLiteDatabase db = getWritableDatabase();
            String sql = "DELETE FROM " + MyDataBaseHelper.TBL_NAME + " WHERE " + MyDataBaseHelper.COL_TASK_ID + " = " + id;
        }catch (Exception ex) {
            Log.e("error: ", ex.getMessage().toString());
        }
    }
    public void updateData(int id, String productName, String HangSX, double GiaBan, byte[] Photo){
        try{
            SQLiteDatabase db = getWritableDatabase();
            String sql = "UPDATE " + MyDataBaseHelper.TBL_NAME + " SET " + MyDataBaseHelper.COL_TASK_NAME + " = ?, " + MyDataBaseHelper.COL_TASK_HangSX + " = ?, " + MyDataBaseHelper.COL_TASK_GIABAN + " = ?, " + MyDataBaseHelper.COL_TASK_PHOTO + " = ?" + " WHERE " + MyDataBaseHelper.COL_TASK_ID + " = " + id;

            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, productName);
            statement.bindString(2,HangSX);
            statement.bindDouble(3,GiaBan);
            statement.bindBlob(4,Photo);

            statement.executeInsert();

            db.execSQL(sql);
        }catch (Exception ex) {
            Log.e("error: ", ex.getMessage().toString());
        }
    }


}



