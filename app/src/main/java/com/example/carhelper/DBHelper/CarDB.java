package com.example.carhelper.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.carhelper.ListItems.Item;

import java.util.ArrayList;

public class CarDB {

    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private ArrayList<Item> dataCars = new ArrayList<Item>();
    private static final String TAG = "CarDB";

    public CarDB(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void upgradeDB(){
        dbHelper.onUpgrade(database, 1,2);
    }

    public void close() {
        dbHelper.close();
    }


    public ArrayList<Item> getDataCars() {
        dataCars.clear();
        Cursor myCursor =
                database.rawQuery("SELECT * FROM CARS", null);
        while (myCursor.moveToNext()) {
            dataCars.add(new Item(myCursor.getString(0), myCursor.getString(1)));
        }
        myCursor.close();
        return dataCars;
    }

    public ArrayList<Item> getMaintenanceList(String iSign) {
        ArrayList<Item> dataMaintanence = new ArrayList<Item>();
        Cursor myCursor =
                database.rawQuery("SELECT * FROM CAR_MAINTENANCE WHERE CAR_SIGN = '" + iSign + "'", null);
        while (myCursor.moveToNext()) {
            dataMaintanence.add(new Item(myCursor.getString(3), myCursor.getString(1)));
        }
        myCursor.close();
        return dataMaintanence;
    }

    public String getCarInfoBySign(String iSign) {

        String res = "Нет данных";
        Cursor myCursor =
                database.rawQuery("SELECT * FROM CARS WHERE CAR_SIGN = '" + iSign + "'", null);
        while (myCursor.moveToNext()) {
            res = myCursor.getString(0) + "\nГ.Р.З.: "
                    + myCursor.getString(1) + "\nГод выпуска: " + myCursor.getString(2)
                    + "\nПробег: " + myCursor.getString(3);
        }
        myCursor.close();
        return res;
    }

    public void insertCar(String name, String year, String sign, int mileage) {
        ContentValues row = new ContentValues();

        row.put("NAME", name);
        row.put("CAR_SIGN", sign);
        row.put("YEAR", year);
        row.put("MILEAGE", mileage);

        database.insert("CARS", null, row);
    }

    @Deprecated
    public Cursor getTablesBD() {
        Cursor c = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        String s;
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                s = "Table name: " + c.getString(0);
                c.moveToNext();
            }
        }
        return c;
    }

    public void insertMaintenance(String carSign, String planDate, int mileage, String description) {
        //Cursor c = getTablesBD();
        try {
            ContentValues row = new ContentValues();
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //String date = sdf.format(planDate);

            row.put("CAR_SIGN", carSign);
            row.put("PLAN_DATE", planDate);
            row.put("MILEAGE", mileage);
            row.put("DESCRIPTION", description);
            database.insert("CAR_MAINTENANCE", null, row);
        } catch (Exception exp) {
            Log.d(TAG, exp.getMessage());
        }
    }

    public void deleteCar(String sing) {
        database.delete("CARS", "CAR_SIGN = '" + sing + "'", null);
        database.delete("CAR_MAINTENANCE", "CAR_SIGN = '" + sing + "'", null);
    }

    public void setDataCars(ArrayList<Item> dataCars) {
        this.dataCars = dataCars;
    }
}
