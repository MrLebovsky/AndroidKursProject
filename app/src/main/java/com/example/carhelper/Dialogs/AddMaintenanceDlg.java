package com.example.carhelper.Dialogs;

import com.example.carhelper.DBHelper.CarDB;
import com.example.carhelper.R;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddMaintenanceDlg extends AppCompatActivity implements TextWatcher {

    private EditText datePlanET, descriptionET;
    private Button AddMaintenanceBtn;
    private String iDate, iDescription;
    CarDB dBase = new CarDB(this);
    public static final String TAG = "AddMaintenanceDlg";

    public void makeHeaderActivity() {
        getSupportActionBar().setTitle("Планирование ТО и ремонта");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.mainThemeColor)));
    }

    public static Calendar parseDateString(String date) {
        SimpleDateFormat DATE_FORMAT_PARSER = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        DATE_FORMAT_PARSER.setLenient(false);
        try {
            calendar.setTime(DATE_FORMAT_PARSER.parse(date));
        } catch (ParseException e) {
            Log.d(TAG, e.getMessage());
            return null;
        }
        return calendar;
    }

    public static boolean isValidDate(String date) {
        Calendar calendar = parseDateString(date);
        if (calendar != null) {
            int year = calendar.get(Calendar.YEAR);
            int thisYear = Calendar.getInstance().get(Calendar.YEAR);

            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            return (year == thisYear && (month >= 0
                    && month <= 11) && (day > 0 && day <= 31));
        } else {
            return false;
        }
    }

    private boolean parseTheData() {
        if (!isValidDate(datePlanET.getText().toString()))
            return false;
        else return descriptionET.getText().toString().length() <= 500;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_maintenance_dlg);
        makeHeaderActivity();
        dBase.open();

        datePlanET = findViewById(R.id.editText7);
        descriptionET = findViewById(R.id.editText8);
        AddMaintenanceBtn = findViewById(R.id.button8);

        datePlanET.addTextChangedListener(this);

        AddMaintenanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!parseTheData()) {
                    ShowErrorMess("Внесены некорректные данные!");
                } else {
                    try {
                        dBase.insertMaintenance(getIntent().getStringExtra("сarSign"),
                                datePlanET.getText().toString(), descriptionET.getText().toString());
                    } catch (Exception exp) {
                        Log.d(TAG, "Error: " + exp.getMessage());
                    }
                    setResult(Activity.RESULT_OK);
                    finish();
                }
            }
        });
    }

    protected void ShowErrorMess(String mess) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddMaintenanceDlg.this);
        builder.setTitle("Ошибка ввода")
                .setMessage(mess)
                .setCancelable(false)
                .setNegativeButton("Ок",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();

        alert.show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        datePlanET.setSelection(datePlanET.getText().toString().length());
        String input = datePlanET.getText().toString();
        if (input.length() == 0)
            return;

        if (input.length() <= 4) {
            try {
                Integer.valueOf(input);
            } catch (Exception exp) {
                if (input.length() >= 2) {
                    ShowErrorMess("Пожалуйста, заполните дату в соответствии с форматом!");
                    datePlanET.setText(datePlanET.getText().toString().substring(0, input.length() - 1));
                } else {
                    ShowErrorMess("Пожалуйста, заполните дату в соответствии с форматом!");
                    datePlanET.setText("");
                }
            }
        } else {
            if (input.length() == 5) {
                if (input.charAt(4) != '-') {
                    ShowErrorMess("Пожалуйста, заполните дату в соответствии с форматом!");
                    datePlanET.setText(datePlanET.getText().toString().substring(0, input.length() - 1));
                }
            } else if (input.length() == 6 || input.length() == 7
                    || input.length() == 9 || input.length() == 10) {
                if (Character.isDigit(input.charAt(input.length() - 1))) {

                } else {
                    ShowErrorMess("Пожалуйста, заполните дату в соответствии с форматом!");
                    datePlanET.setText(datePlanET.getText().toString().substring(0, input.length() - 1));
                }
            } else if (input.length() == 8) {
                if (input.charAt(7) != '-') {
                    ShowErrorMess("Пожалуйста, заполните дату в соответствии с форматом!");
                    datePlanET.setText(datePlanET.getText().toString().substring(0, input.length() - 1));
                }
            } else if (input.length() > 10) {
                ShowErrorMess("Пожалуйста, заполните дату в соответствии с форматом!");
                datePlanET.setText(datePlanET.getText().toString().substring(0, input.length() - 1));
            }
        }
    }
}
