package com.example.carhelper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carhelper.Dialogs.AddCarDlg;
import com.example.carhelper.Activities.MainMenu;
import com.example.carhelper.DBHelper.CarDB;
import com.example.carhelper.ListAdapters.CarListAdapter;
import com.example.carhelper.UIHelper.AdviceService;
import com.example.carhelper.UIHelper.HeaderActivity;
import com.example.carhelper.ListItems.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements HeaderActivity {

    private static final String title = "Опции";
    private static final String message = "Выберите необходимое действие";
    private static final String button1String = "Перейти в основное меню";
    private static final String button2String = "Удалить автомобиль";

    private static final AdviceService adviceService = new AdviceService();
    private AlertDialog.Builder ad;
    private AlertDialog.Builder advicesDlg;

    private Context context;

    private ArrayList<Item> dataCars = new ArrayList<Item>();

    private ListView listView;
    private Button AddCarBtn;
    private Button AdvicesBtn;

    private CarListAdapter mAdapter;
    private CarDB dbase;

    private String carSign;
    private String сarInfo;
    private int adviceCount = 0;

    public void makeHeaderActivity() {
        getSupportActionBar().setTitle("Помощник автомобилиста");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainThemeColor)));
    }

    private void initDB() {
        dbase = new CarDB(this);
        dbase.open();
        //dbase.upgradeDB(); //пересоздание БДшки
        dataCars = dbase.getDataCars();
    }

    public void initAdvicesDlg(){
        advicesDlg = new AlertDialog.Builder(context);
        advicesDlg.setTitle("Полезные советы");
        advicesDlg.setMessage(adviceService.getAdviceMess(adviceCount));

        advicesDlg.setPositiveButton("Следующий совет", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                adviceCount++;
                if (adviceCount > 5)
                    adviceCount = 0;
                advicesDlg.setMessage(adviceService.getAdviceMess(adviceCount));
                advicesDlg.show();
            }
        });
        advicesDlg.setNegativeButton("Закрыть", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

            }
        });
    }

    public void initOptionDlg(){
        ad = new AlertDialog.Builder(context);
        ad.setTitle(title);  // заголовок
        ad.setMessage(message); // сообщение
        ad.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                Intent intent = new Intent(MainActivity.this, MainMenu.class);

                intent.putExtra("сarInfo", сarInfo);
                intent.putExtra("сarSign", carSign);

                startActivity(intent);
            }
        });
        ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                dbase.deleteCar(carSign);
                dataCars = dbase.getDataCars();
                mAdapter.updateList(dataCars);
            }
        });
        ad.setCancelable(true);
        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeHeaderActivity();

        context = MainActivity.this;

        initDB();
        initOptionDlg();
        initAdvicesDlg();

        setContentView(R.layout.activity_main);
        AddCarBtn = findViewById(R.id.AddCarBtn);
        AdvicesBtn = findViewById(R.id.button12);
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
                сarInfo = selectedItem.getHeader();
                carSign = selectedItem.getSubHeader();
                ad.show();
                return true;
            }
        });
    }

    public void onClickAdvicesBtn(View v) {
        advicesDlg.show();
    }

    public void onClickAddCar(View v) {
        Intent intent = new Intent(this, AddCarDlg.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        dataCars = dbase.getDataCars();
        mAdapter.updateList(dataCars);
    }
}
