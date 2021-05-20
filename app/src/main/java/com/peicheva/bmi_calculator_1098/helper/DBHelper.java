package com.peicheva.bmi_calculator_1098.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class  DBHelper extends SQLiteOpenHelper {

    // Информация за базата данни
    private static final String DB_Name = "bmi";
    private static final int DB_VERSION = 1;

    // Име на таблицата
    public static final String TABLE_BMIDATA = "bmidata";

    // Колони на таблицата
    public static final String TABLE_BMIDATAID = "bmiid";
    public static final String TABLE_BMIDATE = "bmidate";
    public static final String TABLE_BMIWEIGHT = "bmiweight";
    public static final String TABLE_BMIHEIGHT = "bmiheight";
    public static final String TABLE_BMIVALUE = "bmivalue";
    public static final String TABLE_BMITYPE = "bmitype";


    public DBHelper(Context context) {
        super(context, DB_Name, null, DB_VERSION);
    }


    // Извиква се, когато се конфигурира връзката с базата данни.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }


    // Извиква се, когато базата данни е създадена за първи път.
    // Ако база данни вече съществува на диск със същата DATABASE_NAME, този метод няма да бъде извикан.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DATA_TABLE = "CREATE TABLE " + TABLE_BMIDATA +
                "(" +
                TABLE_BMIDATAID + " INTEGER PRIMARY KEY AUTOINCREMENT," + // Дефинираме първичния ключ.
                TABLE_BMIDATE + " TEXT," +
                TABLE_BMIWEIGHT + " TEXT," +
                TABLE_BMIHEIGHT + " TEXT," +
                TABLE_BMIVALUE + " TEXT," +
                TABLE_BMITYPE + " TEXT" +
                ")";


        db.execSQL(CREATE_DATA_TABLE);

    }


    // Този метод ще бъде извикан само ако база данни вече съществува на диск със същата DATABASE_NAME,
    // но DATABASE_VERSION е различна от версията на базата данни, която съществува на диска.
    // Най-простото изпълнение на този метод е DROP TABLE IF EXISTS на всички стари таблици и да ги пресъздадем.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_BMIDATA);
            onCreate(db);
        }
    }

}
