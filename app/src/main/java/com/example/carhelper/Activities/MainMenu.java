package com.example.carhelper.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carhelper.Dialogs.AboutDlg;
import com.example.carhelper.R;
import com.example.carhelper.UIHelper.HeaderActivity;

public class MainMenu extends AppCompatActivity  implements HeaderActivity {

    TextView carInfo;
    Button openMapBtn, aboutBtn, goToMainDisplatBtn, fuelCalcBtn;

    public void makeHeaderActivity() {
        getSupportActionBar().setTitle("Помощник автомобилиста");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainThemeColor)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeHeaderActivity();
        setContentView(R.layout.activity_main_menu);

        openMapBtn = findViewById(R.id.button7);
        fuelCalcBtn = findViewById(R.id.button9);
        goToMainDisplatBtn = findViewById(R.id.button11);
        aboutBtn = findViewById(R.id.button10);
        carInfo = findViewById(R.id.textView7);
        carInfo.setText(getIntent().getStringExtra("сarInfo"));
    }

    public void onClickHomePage(View v) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    public void onClickAbout(View v) {
        Intent intent = new Intent(this, AboutDlg.class);
        startActivityForResult(intent, 1);
    }

    public void onClickOpenMap(View v) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivityForResult(intent, 1);
    }

    public void onClickFuelCalc(View v) {
        Intent intent = new Intent(this, FuelCalc.class);
        startActivityForResult(intent, 1);
    }

    public void onClickTrafficLaws(View v) {
        Intent intent = new Intent(this, TrafficLaws.class);
        startActivityForResult(intent, 1);
    }

    public void onClickMaintenancePlan(View v) {
        Intent intent = new Intent(this, MaintenancePlan.class);
        intent.putExtra("сarSign", getIntent().getStringExtra("сarSign"));
        startActivityForResult(intent, 1);
    }

}
