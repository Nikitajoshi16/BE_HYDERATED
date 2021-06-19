package com.example.bottomnavigationactivity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
 Sqlite_helper sqliteHelper = new Sqlite_helper(MainActivity.this);
 //BlankFragment1 blankFragment1 = new BlankFragment1();
    SharedPreferences preferences ;

int alarmmin ;
int alarmhr ;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = this.getPreferences(MODE_PRIVATE);
        alarmmin = 12;
        alarmhr = 11;
        Log.d("pre", "onCreate: " + alarmmin);



       BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
       NavController navController = Navigation.findNavController(MainActivity.this
               ,R.id.fragment);

        NavigationUI.setupWithNavController(bottomNavigationView,navController);
       // blankFragment1.adding = savedInstanceState.getInt("added");

    }


    @Override
    protected void onPause() {
        super.onPause();
        preferences.edit().putInt("houralrm" ,alarmhr).apply();
        preferences.edit().putInt("minalrm" , alarmmin).apply();

    }
}