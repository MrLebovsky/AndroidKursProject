package com.example.carhelper;

import android.app.AlertDialog;
import android.content.Context;
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
import com.example.carhelper.UIHelper.CarListAdapter;
import com.example.carhelper.UIHelper.ChoiceOptionDlg;
import com.example.carhelper.UIHelper.HeaderActivity;
import com.example.carhelper.UIHelper.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements HeaderActivity {

    AlertDialog.Builder ad;
    Context context;
    ArrayList<Item> dataCars = new ArrayList<Item>();
    ListView listView;
    Button AddCarBtn;
    CarListAdapter mAdapter;
    CarDB dbase;

    public void makeHeaderActivity() {
        getSupportActionBar().setTitle("Помощник автомобилиста");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainThemeColor)));
    }

    private void initDB() {
        dbase = new CarDB(this);
        dbase.open();
        dataCars = dbase.getDataCars();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeHeaderActivity();

        context = MainActivity.this;

        initDB();
        setContentView(R.layout.activity_main);
        AddCarBtn = findViewById(R.id.AddCarBtn);
        listView = findViewById(R.id.listView);
        mAdapter = new CarListAdapter(this, dataCars);
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, MainMenu.class);
                Item selectedItem = (Item) mAdapter.getItem(position);

                intent.putExtra("сarInfo", selectedItem.getHeader());
                intent.putExtra("сarSign", selectedItem.getSubHeader());

                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int position, long arg3) {

                Item selectedItem = (Item) mAdapter.getItem(position);
                ChoiceOptionDlg dlg = new ChoiceOptionDlg(MainActivity.this, selectedItem.getSubHeader());
                dlg.Show();
                return true;
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
