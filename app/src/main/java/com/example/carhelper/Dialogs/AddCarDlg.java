package com.example.carhelper.Dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carhelper.DBHelper.CarDB;
import com.example.carhelper.R;
import com.example.carhelper.UIHelper.HeaderActivity;

public class AddCarDlg extends AppCompatActivity  implements HeaderActivity {

    CarDB dBase = new CarDB(this);
    Button ok, cancel;
    private EditText nameET, car_singET, yearET, mileageET;
    static final String TAG = "AddCarDlg";

    public void makeHeaderActivity() {
        getSupportActionBar().setTitle("Помощник автомобилиста");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainThemeColor)));
    }

    private void parseTheData() {
        String name, sing, year;
        int mileage;

        try {
            name = nameET.getText().toString();
            year = yearET.getText().toString();
            sing = car_singET.getText().toString();
            mileage = Integer.parseInt(mileageET.getText().toString());

            dBase.insertCar(name, year, sing, mileage);
        } catch (Exception exp) {
            Log.d(TAG, "Error: " + exp.getMessage());
        }
    }

    protected boolean isGoodData() {
        return nameET.getText().length() != 0 && yearET.getText().length() != 0
                && car_singET.getText().length() != 0 && mileageET.getText().length() != 0;
    }

    protected void ShowErrorMess() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddCarDlg.this);
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
        dBase.open();

        ok = findViewById(R.id.button);
        cancel = findViewById(R.id.button2);
        nameET = findViewById(R.id.editText);
        car_singET = findViewById(R.id.editText2);
        yearET = findViewById(R.id.editText3);
        mileageET = findViewById(R.id.editText4);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isGoodData()) {
                    parseTheData();
                    setResult(Activity.RESULT_OK);
                    finish();
                } else {
                    ShowErrorMess();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }
}
