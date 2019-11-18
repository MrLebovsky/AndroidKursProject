package com.example.carhelper.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carhelper.R;
import com.example.carhelper.UIHelper.HeaderActivity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FuelCalc extends AppCompatActivity implements HeaderActivity {

    static final String TAG = "FuelCalc";
    private EditText consumptionBenzine, passedDistance, costBenzine;
    private TextView averageConsumption, avegareCost;
    Double consumption, distance, cost;
    Button calc;

    public void makeHeaderActivity() {
        getSupportActionBar().setTitle("Калькулятор расхода топлива");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainThemeColor)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_calc);
        makeHeaderActivity();

        consumptionBenzine = findViewById(R.id.editText5);
        passedDistance = findViewById(R.id.editText6);
        costBenzine = findViewById(R.id.editText9);
        calc = findViewById(R.id.button3);
        averageConsumption = findViewById(R.id.textView22);
        avegareCost = findViewById(R.id.textView28);


        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parseTheData()) {
                    averageConsumption.setText(String.valueOf(calcAverageConsumption()));
                    avegareCost.setText(String.valueOf(calcCost()));
                } else {
                    ShowErrorMess();
                }

            }
        });
    }

    protected void ShowErrorMess() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FuelCalc.this);
        builder.setTitle("Ошибка ввода")
                .setMessage("Введены некорректные данные!")
                //.setIcon(R.drawable.ic_android_cat)
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public boolean parseTheData() {
        try {
            consumption = Double.parseDouble(consumptionBenzine.getText().toString());
            distance = Double.parseDouble(passedDistance.getText().toString());
            cost = Double.parseDouble(costBenzine.getText().toString());

            return true;
        } catch (NumberFormatException e) {
            Log.d(TAG, "Error: " + e.getMessage());
            return false;
        }
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double calcAverageConsumption() {
        if (distance != 0)
            return round(((100 * consumption) / distance),2);
        else return 0;
    }

    public double calcCost() {
        return round((consumption * cost),2);
    }


}
