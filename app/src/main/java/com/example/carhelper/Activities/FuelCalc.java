package com.example.carhelper.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.carhelper.R;

public class FuelCalc extends AppCompatActivity {

    protected void makeHeaderActivity() {
        getSupportActionBar().setTitle("Калькулятор расхода топлива");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainThemeColor)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_calc);
        makeHeaderActivity();
    }
}
