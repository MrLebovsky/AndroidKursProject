package com.example.carhelper.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.carhelper.UIHelper.Item;

import java.util.ArrayList;

public class CarDB {

    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private ArrayList<Item> dataCars = new ArrayList<Item>();

    public CarDB(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
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

    public String getCarInfoBySign(String iSign){

        String res = "Нет данных";
        Cursor myCursor =
                database.rawQuery("SELECT * FROM CARS WHERE CAR_SIGN = '"+ iSign +"'", null);
        while (myCursor.moveToNext()) {
           res =  myCursor.getString(0) + "\nГ.Р.З.: "
                   + myCursor.getString(1) + "\nГод выпуска: " +  myCursor.getString(2)
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

    public void deleteCar(String sing){
        database.delete("CARS","CAR_SIGN = '" + sing + "'", null );
    }

    public void setDataCars(ArrayList<Item> dataCars) {
        this.dataCars = dataCars;
    }
}
