package com.example.carhelper.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import com.example.carhelper.R;

public class MainMenu extends AppCompatActivity {

    TextView carInfo;

    protected void makeHeaderActivity(){
        getSupportActionBar().setTitle("Помощник автомобилиста");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainThemeColor)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeHeaderActivity();
        setContentView(R.layout.activity_main_menu);

        carInfo = findViewById(R.id.textView7);
        carInfo.setText(getIntent().getStringExtra("сarInfo"));
    }
}
