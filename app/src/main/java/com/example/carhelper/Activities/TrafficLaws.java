package com.example.carhelper.Activities;

import com.example.carhelper.R;
import com.example.carhelper.UIHelper.HeaderActivity;
import com.example.carhelper.ListItems.State;
import com.example.carhelper.ListAdapters.StateAdapter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TrafficLaws extends AppCompatActivity implements HeaderActivity {

    private List<State> states = new ArrayList();
    ListView pddList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_laws2);
        pddList = findViewById(R.id.pddList);
        makeHeaderActivity();
        setInitialData();
        StateAdapter stateAdapter = new StateAdapter(this, R.layout.list_item_pdd, states);
        pddList.setAdapter(stateAdapter);
    }

    private void setInitialData() {
        states.add(new State("1.19 \"Опасная обочина\".\n", "Участок дороги, на котором съезд на обочину опасен.\n" +
                "\n", R.mipmap.znak1));
        states.add(new State("1.32 \"Затор\".\n", "Участок дороги, на котором образовался затор.\n" +
                "\n", R.mipmap.znak2));
        states.add(new State("4.2.2 \"Объезд препятствия слева\".\n", "Указывает, что объезд различного рода препятствий на проезжей части (в том числе островков безопасности) разрешается со стороны, указанной стрелкой.\n" +
                "\n", R.mipmap.znak6));
        states.add(new State("1.33 \"Прочие опасности\".\n", "Участок дороги, на котором имеются опасности, не предусмотренные другими предупреждающими знаками.\n" +
                "\n", R.mipmap.znak3));
        states.add(new State("2.4 \"Уступите дорогу\".\n", "Водитель должен уступить дорогу транспортным средствам, движущимся по пересекаемой дороге, а при наличии таб.8.13 - по главной.\n" +
                "\n", R.mipmap.znak4));
        states.add(new State("4.3 \"Круговое движение\".\n", "Движение организовано по кругу в направлении стрелок.\n" +
                "\n", R.mipmap.znak7));
        states.add(new State("2.6 \"Преимущество встречного движения\".\n", "Запрещается въезд на узкий участок дороги, если это может затруднить встречное движение. Водитель должен уступить дорогу встречным транспортным средствам, находящимся на узком участке или противоположном подъезде к нему.\n" +
                "\n", R.mipmap.znak5));
    }

    @Override
    public void makeHeaderActivity() {
        getSupportActionBar().setTitle("Справочник ПДД");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainThemeColor)));
    }
}
