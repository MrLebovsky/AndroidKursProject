package com.example.carhelper.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.carhelper.R;

import java.util.List;

public class MainMenu extends AppCompatActivity {

    TextView carInfo;
    Button openMapBtn;

    protected void makeHeaderActivity() {
        getSupportActionBar().setTitle("Помощник автомобилиста");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainThemeColor)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeHeaderActivity();
        setContentView(R.layout.activity_main_menu);

        openMapBtn = findViewById(R.id.button7);
        carInfo = findViewById(R.id.textView7);
        carInfo.setText(getIntent().getStringExtra("сarInfo"));
    }

    public void onClickOpenMap(View v) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivityForResult(intent, 1);
    }

}
