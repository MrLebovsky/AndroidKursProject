package com.example.carhelper;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carhelper.Activities.AddCar_Dial;
import com.example.carhelper.Activities.MainMenu;
import com.example.carhelper.UIHelper.Item;
import com.example.carhelper.UIHelper.MyAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> dataCars = new ArrayList<Item>();
    ListView listView;
    Button AddCarBtn;
    SQLiteDatabase myDB;
    MyAdapter mAdapter;

    private void initDB() {
        myDB = openOrCreateDatabase("my.db", MODE_PRIVATE, null);
        myDB.execSQL(
                "CREATE TABLE IF NOT EXISTS CARS (NAME VARCHAR(200), CAR_SIGN  VARCHAR(200), YEAR VARCHAR(4), MILEAGE INT)"
        );
        dataCars.clear();
        Cursor myCursor =
                myDB.rawQuery("SELECT * FROM CARS", null);
        while (myCursor.moveToNext()) {
            dataCars.add(new Item(myCursor.getString(0), myCursor.getString(1)));
        }
        myCursor.close();
    }

    protected void makeHeaderActivity() {
        getSupportActionBar().setTitle("Помощник автомобилиста");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainThemeColor)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeHeaderActivity();
        setContentView(R.layout.activity_main);
        AddCarBtn = findViewById(R.id.AddCarBtn);

        initDB();
        listView = findViewById(R.id.listView);
        mAdapter = new MyAdapter(this, dataCars);
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
        Intent intent = new Intent(this, AddCar_Dial.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        initDB();
        mAdapter.updateList(dataCars);
    }
}
