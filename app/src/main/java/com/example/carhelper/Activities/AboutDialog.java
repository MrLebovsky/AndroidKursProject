package com.example.carhelper.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.carhelper.R;
import com.example.carhelper.UIHelper.HeaderActivity;

public class AboutDialog extends AppCompatActivity  implements HeaderActivity {

    ImageView imageView;

    public void makeHeaderActivity() {
        getSupportActionBar().setTitle("Справка");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainThemeColor)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_dialog);
        makeHeaderActivity();
        imageView = findViewById(R.id.imageView2);

    }
}
