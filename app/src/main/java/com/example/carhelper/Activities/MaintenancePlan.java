package com.example.carhelper.Activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carhelper.DBHelper.CarDB;
import com.example.carhelper.R;
import com.example.carhelper.UIHelper.HeaderActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class MaintenancePlan extends AppCompatActivity implements HeaderActivity {

    TextView carInfo;
    CarDB dBase = new CarDB(this);

    private String carSign;

    @Override
    public void makeHeaderActivity() {
        getSupportActionBar().setTitle("Планирование ТО и ремонта");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainThemeColor)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_plan);
        makeHeaderActivity();
        dBase.open();
        carSign = getIntent().getStringExtra("сarSign");
        carInfo = findViewById(R.id.textView15);
        carInfo.setText(dBase.getCarInfoBySign(carSign));
    }
}
