package com.example.carhelper.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.carhelper.R;

public class AddCar_Dial extends AppCompatActivity{

    SQLiteDatabase myDB;
    ContentValues row;
    Button ok, cancel;
    private EditText nameET, car_singET, yearET, mileageET;
    static final String TAG = "AddCar_Dial";

    protected void makeHeaderActivity(){
        getSupportActionBar().setTitle("Помощник автомобилиста");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainThemeColor)));
    }

    private void parseTheData(){
        String name, car_sing, year;
        int mileage;

        try{
            name = nameET.getText().toString();
            year = yearET.getText().toString();
            car_sing = car_singET.getText().toString();
            mileage = Integer.parseInt(mileageET.getText().toString());

            row.put("NAME", name);
            row.put("CAR_SIGN", car_sing);
            row.put("YEAR", year);
            row.put("MILEAGE", mileage);

            myDB.insert("CARS", null, row);
        }
        catch(Exception exp){
            Log.d(TAG, "Error: " + exp.getMessage());
        }
    }

    protected boolean isGoodData(){
        if(nameET.getText().toString() != "" && yearET.getText().toString() != ""
        && car_singET.getText().toString() != "" && mileageET.getText().toString() != ""){
            return true;
        }
        else return false;
    }

    protected void ShowErrorMess(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AddCar_Dial.this);
        builder.setTitle("Ошибка ввода")
                .setMessage("Пожалуйста, заполните все данные!")
                //.setIcon(R.drawable.ic_android_cat)
                .setCancelable(false)
                .setNegativeButton("ОК, ща заполню",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeHeaderActivity();
        setContentView(R.layout.activity_add_car__dial);

        ok = findViewById(R.id.button);
        cancel = findViewById(R.id.button2);
        nameET = findViewById(R.id.editText);
        car_singET = findViewById(R.id.editText2);
        yearET = findViewById(R.id.editText3);
        mileageET = findViewById(R.id.editText4);

        row = new ContentValues();
        myDB = openOrCreateDatabase("my.db", MODE_PRIVATE, null);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGoodData()) {
                    parseTheData();
                    setResult(Activity.RESULT_OK);
                    finish();
                }else{
                    ShowErrorMess();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //close dialog
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }
}
