package com.peicheva.bmi_calculator_1098.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;


public class BmiDatatable {

    com.peicheva.bmi_calculator_1098.helper.DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

            public BmiDatatable(Context context){
                dbHelper = new com.peicheva.bmi_calculator_1098.helper.DBHelper(context);
            }

            public void openDB(){
                sqLiteDatabase = dbHelper.getWritableDatabase();
            }

            public void closeDB(){
                sqLiteDatabase.close();
            }

            public void insertRecord(String date, String weight, String height, String value, String type){
                ContentValues contentValues = new ContentValues();
                contentValues.put(com.peicheva.bmi_calculator_1098.helper.DBHelper.TABLE_BMIDATE,date);
                contentValues.put(com.peicheva.bmi_calculator_1098.helper.DBHelper.TABLE_BMIWEIGHT,weight);
                contentValues.put(com.peicheva.bmi_calculator_1098.helper.DBHelper.TABLE_BMIHEIGHT,height);
                contentValues.put(com.peicheva.bmi_calculator_1098.helper.DBHelper.TABLE_BMIVALUE,value);
                contentValues.put(com.peicheva.bmi_calculator_1098.helper.DBHelper.TABLE_BMITYPE,type);

                sqLiteDatabase.insert(com.peicheva.bmi_calculator_1098.helper.DBHelper.TABLE_BMIDATA,null,contentValues);

            }

            public Cursor getAllRecords(){
                return sqLiteDatabase.rawQuery("select * from " + com.peicheva.bmi_calculator_1098.helper.DBHelper.TABLE_BMIDATA,null);
            }

            public  Cursor clearAllRecords(){
                try {
                    sqLiteDatabase.execSQL("delete from " + com.peicheva.bmi_calculator_1098.helper.DBHelper.TABLE_BMIDATA);

                } catch (SQLiteException e) {
                    e.printStackTrace();
                }
                return null;
            }

}
