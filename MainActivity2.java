package com.example.bottomnavigationactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MainActivity2 extends AppCompatActivity {
    private  Notificationhelper  notificationhelper;
    Sqlite_helper db = new Sqlite_helper(MainActivity2.this);
    loginactivity loactivity = new loginactivity();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        SharedPreferences preferences2 = getPreferences(MODE_PRIVATE);
        SharedPreferences preferences = getSharedPreferences("preference" , MODE_PRIVATE);


        String firsttime = preferences.getString("firsttimeinstall" , "");
        if(firsttime.equals("Yes")){


           // if(!loactivity.nm.equals("") && loactivity.wht != 0 && !loactivity.am.equals("00")
                //    &&!loactivity.pm.equals("00")){
                Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                        startActivity(intent);

                    }
                }, 4000);


           // }

//else{ Handler handler2 = new Handler();
//                handler2.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent intent = new Intent(MainActivity2.this , loginactivity.class);
//                        startActivity(intent);
//
//                    }
//                },4000);
//            }


           }

        else{
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("firsttimeinstall" , "Yes");
            editor.apply();
         //   running = preferences2.getBoolean("running" , false);
           // preferences.edit().putBoolean("running" , running).apply();

            Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity2.this , loginactivity.class);
                    startActivity(intent);

                }
            },4000);}


      //  Log.d("hh", "run: hii" + running);




    }



    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("preference" , MODE_PRIVATE);

    }
}