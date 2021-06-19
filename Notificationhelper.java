package com.example.bottomnavigationactivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class Notificationhelper extends ContextWrapper {
    public static final String channelid = "channelID";
    public static final String channelname= "channel";
    private  NotificationManager manager;
    public Notificationhelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
        createchannels();
    }}

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createchannels(){
        NotificationChannel channel = new NotificationChannel(channelid,channelname,
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setLightColor(R.color.colorPrimary);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getmanager().createNotificationChannel(channel);

    }
    public NotificationManager getmanager(){
        if(manager == null){
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return  manager;
    }
    public NotificationCompat.Builder getnotification(String title , String message){
        return  new NotificationCompat.Builder(getApplicationContext(),channelid)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_baseline_alarm_24);
    }
}
