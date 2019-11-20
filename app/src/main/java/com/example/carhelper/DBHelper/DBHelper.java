package com.example.carhelper.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_CAR_CREATE =
            "CREATE TABLE IF NOT EXISTS CARS (NAME VARCHAR(200), CAR_SIGN  VARCHAR(200) PRIMARY KEY, YEAR VARCHAR(4), MILEAGE INT)";

    private static final String DATABASE_CAR_MAINTENANCE =
            "CREATE TABLE IF NOT EXISTS CAR_MAINTENANCE (CAR_SIGN  VARCHAR(200), PLAN_DATE DATE, DESCRIPTION VARCHAR(500))";

    private static final String DATABASE_NAME = "my.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CAR_CREATE);
        db.execSQL(DATABASE_CAR_MAINTENANCE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS CARS");
        db.execSQL("DROP TABLE IF EXISTS CAR_MAINTENANCE");

        onCreate(db);
    }
}
