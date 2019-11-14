package com.example.carhelper;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carhelper.Activities.AddCarDial;
import com.example.carhelper.Activities.MainMenu;
import com.example.carhelper.DBHelper.CarDB;
import com.example.carhelper.UIHelper.Item;
import com.example.carhelper.UIHelper.CarListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> dataCars = new ArrayList<Item>();
    ListView listView;
    Button AddCarBtn;
    CarListAdapter mAdapter;
    CarDB dbase;

    protected void makeHeaderActivity() {
        getSupportActionBar().setTitle("Помощник автомобилиста");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainThemeColor)));
    }

    private void initDB(){
        dbase = new CarDB(this);
        dbase.open();
        dataCars = dbase.getDataCars();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeHeaderActivity();
        setContentView(R.layout.activity_main);
        AddCarBtn = findViewById(R.id.AddCarBtn);
        initDB();
        listView = findViewById(R.id.listView);
        mAdapter = new CarListAdapter(this, dataCars);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, MainMenu.class);
                Item selectedItem = (Item) mAdapter.getItem(position);
                intent.putExtra("сarInfo", selectedItem.getHeader());
                startActivity(intent);
            }
        });
    }

    public void onClickAddCar(View v) {
        Intent intent = new Intent(this, AddCarDial.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        dataCars = dbase.getDataCars();
        mAdapter.updateList(dataCars);
    }
}
