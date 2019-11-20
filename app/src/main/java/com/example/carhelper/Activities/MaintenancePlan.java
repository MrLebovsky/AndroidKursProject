package com.example.carhelper.Activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carhelper.DBHelper.CarDB;
import com.example.carhelper.Dialogs.AddMaintenanceDlg;
import com.example.carhelper.ListAdapters.CarListAdapter;
import com.example.carhelper.ListItems.Item;
import com.example.carhelper.R;
import com.example.carhelper.UIHelper.HeaderActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MaintenancePlan extends AppCompatActivity implements HeaderActivity {

    TextView carInfo;
    ArrayList<Item> dataMaintenance = new ArrayList<Item>();
    ListView listView;
    CarDB dBase = new CarDB(this);
    CarListAdapter mAdapter;

    private String carSign;

    @Override
    public void makeHeaderActivity() {
        getSupportActionBar().setTitle("Планирование ТО и ремонта");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainThemeColor)));
    }

    private void initDB() {
        dBase.open();
        dataMaintenance = dBase.getMaintenanceList(carSign);
        carInfo.setText(dBase.getCarInfoBySign(carSign));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_plan);
        makeHeaderActivity();

        carSign = getIntent().getStringExtra("сarSign");
        carInfo = findViewById(R.id.textView15);
        listView = findViewById(R.id.listViewTO);

        initDB();


        mAdapter = new CarListAdapter(this, dataMaintenance);
        listView.setAdapter(mAdapter);
    }

    public void onClickAddMaintenancePlan(View v) {
        Intent intent = new Intent(this, AddMaintenanceDlg.class);
        intent.putExtra("сarSign", carSign);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        dataMaintenance = dBase.getMaintenanceList(carSign);
        mAdapter.updateList(dataMaintenance);
    }
}
