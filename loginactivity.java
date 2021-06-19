package com.example.bottomnavigationactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.provider.AlarmClock;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.bottomnavigationactivity.ITEM.items;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class loginactivity extends AppCompatActivity {

    EditText name ;
    EditText weight ;
    TextView hours1 ;
    TextView hours2 ;
    int hour1;
    int min1;
    int hour2;
    int min2 ;
    Button ok ;
    Spinner spin ;
    Sqlite_helper db = new Sqlite_helper(loginactivity.this);
    long currenttime = 0;
    int alarmhr = 11;
    int alarmmin = 22;
    String nm = "";
    int wht = 00;
    String am ="00";
    String pm = "00";
    String kg_lbs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        getSupportActionBar().hide();


        name = findViewById(R.id.name);
        weight = findViewById(R.id.weight);
        hours1 = findViewById(R.id.hours1);
        hours2 = findViewById(R.id.hours2);

        ok = findViewById(R.id.button);
        spin = findViewById(R.id.spinner);

        hours1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(loginactivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        //initialize hour and min
                        hour1 = h;
                        min1 = m;
                        Log.d("hours1", "onTimeSet: " + hour1 + " " + min1);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0,0,0,hour1,min1);

                        hours1.setText(DateFormat.format("hh:mm aa" , calendar));
                    }
                },12,0,false);
                timePickerDialog.updateTime(hour1,min1);
                timePickerDialog.show();
            }
        });
        hours2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(loginactivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        //initialize hour and min
                        hour2 = h;
                        min2 = m;
                        Log.d("hours2", "onTimeSet: " + hour2 + " " + min2);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0,0,0,hour2,min2);

                        hours2.setText(DateFormat.format("hh:mm aa" , calendar));
                    }
                },12,0,false);
                timePickerDialog.updateTime(hour2,min2);
                timePickerDialog.show();
            }
        });

    }



    public void onok(View view) {


             nm = name.getText().toString().trim();
            wht = Integer.parseInt(weight.getText().toString().trim());
         am = String.valueOf(hours1.getText());
        pm = String.valueOf(hours2.getText());
         kg_lbs = String.valueOf(spin.getSelectedItem());
        items item ;
       // Log.d("hh", "onok: " + hour2);

        if(!nm.isEmpty() && wht != 0 && !am.equals("00") &&!pm.equals("00")){
                item = new items();
                item.setName(nm);
                item.setWeight(wht);
                item.setUnit(kg_lbs);
                item.setWtimehrs(String.valueOf(hour1));
                item.setWtimemin(String.valueOf(min1));

                    item.setStimehrs(String.valueOf(hour2));
                item.setStimemin(String.valueOf(min2));
                // item.setCompleted(Integer.parseInt(String.valueOf("100")));
                db.addprofile(item);
                if(db != null){
                    List<items> itemsList = db.getAllprofile();
                    for(items it : itemsList){
                        Log.d("k", "onok: " + it.getStimehrs() + it.getStimemin());
                    }
                   }
                Intent intent = new Intent(loginactivity.this , MainActivity.class);
                startActivity(intent);
               }


        else{
            Toast.makeText(loginactivity.this,"Please fill all the fields" , Toast.LENGTH_SHORT)
                    .show();
        }







    }

}