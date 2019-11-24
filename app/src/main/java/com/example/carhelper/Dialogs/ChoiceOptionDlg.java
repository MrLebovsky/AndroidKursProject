package com.example.carhelper.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.fragment.app.DialogFragment;

import com.example.carhelper.Activities.MainMenu;
import com.example.carhelper.DBHelper.CarDB;
import com.example.carhelper.MainActivity;

@Deprecated
public class ChoiceOptionDlg extends DialogFragment {

    private static final String title = "Опции";
    private static final String message = "Выберите необходимое действие";
    private static final String button1String = "Перейти в основное меню";
    private static final String button2String = "Удалить автомобиль";

    private String carSign;
    private AlertDialog.Builder ad;
    private CarDB dBase;

    public ChoiceOptionDlg(final Context context, String carSign){

        this.carSign = carSign;
        dBase = new CarDB(context);
        dBase.open();

        ad = new AlertDialog.Builder(context);
        ad.setTitle(title);  // заголовок
        ad.setMessage(message); // сообщение
        ad.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                Intent intent = new Intent(context, MainMenu.class);
                context.startActivity(intent);
            }
        });
        ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

                delCar();
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });
        ad.setCancelable(true);
        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {

            }
        });
    }

    public void delCar(){
        dBase.deleteCar(carSign);
    }

    public void Show(){
        ad.show();
    }
}