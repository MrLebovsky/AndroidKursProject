package com.example.carhelper.ListAdapters;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carhelper.R;
import com.example.carhelper.ListItems.Item;

public class CarListAdapter extends BaseAdapter {

    ArrayList<Item> data = new ArrayList<Item>();
    Context context;

    public CarListAdapter(Context context, ArrayList<Item> arr) {
        if (arr != null) {
            data = arr;
        }
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int num) {
        // TODO Auto-generated method stub
        return data.get(num);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int i, View someView, ViewGroup arg2) {
        //Получение объекта inflater из контекста
        LayoutInflater inflater = LayoutInflater.from(context);
        //Если someView (View из ListView) вдруг оказался равен
        //null тогда мы загружаем его с помошью inflater
        if (someView == null) {
            someView = inflater.inflate(R.layout.list_item, arg2, false);
        }
        //Обявляем наши текствьюшки и связываем их с разметкой
        TextView header = someView.findViewById(R.id.item_headerText);
        TextView subHeader = someView.findViewById(R.id.item_subHeaderText);

        //Устанавливаем в каждую текствьюшку соответствующий текст
        // сначала заголовок
        header.setText(data.get(i).getHeader());
        // потом подзаголовок
        subHeader.setText(data.get(i).getSubHeader());
        return someView;
    }

    public void updateList(ArrayList<Item> inArray) {
        data = inArray;
        notifyDataSetChanged();
    }

}