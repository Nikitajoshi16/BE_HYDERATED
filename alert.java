package com.example.bottomnavigationactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class alert extends BroadcastReceiver {
    Notificationhelper notificationhelper;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("onre", "onReceive: hii");
        notificationhelper =new Notificationhelper(context);
       NotificationCompat.Builder builder = notificationhelper.getnotification("Reminder to drink water"
              , "Hey!! its time to have some water... don't ignore me");
        notificationhelper.getmanager().notify(1, builder.build());

       // Toast.makeText(context.getApplicationContext(), "Alarm Manager just ran", Toast.LENGTH_LONG).show();
    }
}
