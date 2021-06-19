package com.example.bottomnavigationactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bottomnavigationactivity.ITEM.items;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sqlite_helper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Drinkwater";
    public static final int DB_VERSION = 1;
    public Sqlite_helper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("CREATE TABLE PROFILE (" + "id INTEGER PRIMARY KEY ," + "Name TEXT," +
                 "Weight TEXT," + "unit TEXT," + "wakeuptimehrs TEXT ," + "Sleeptimehrs TEXT,"
                 + "wakeuptimemin TEXT ," + "Sleeptimemin TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        String DROP_TABLE = String.valueOf("DROP TABLE IF EXISTS");
        db.execSQL(DROP_TABLE +"PROFILE");

        //Create a table again
        onCreate(db);

    }

    public void addprofile(items item){
       SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name" , item.getName());
        values.put("Weight" , item.getWeight());
        values.put("unit" , item.getUnit());
        values.put("wakeuptimehrs" , item.getWtimehrs());
        values.put("Sleeptimehrs" , item.getStimehrs());
        values.put("wakeuptimemin" , item.getWtimemin());
        values.put("Sleeptimemin" , item.getStimemin());
      //  values.put("target" , item.getTaget());
       // values.put("completed" , item.getCompleted());

        db.insert("PROFILE" , null , values);
        db.close();
    }
    public  int updateprofile(items item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name" , item.getName());
        values.put("Weight" , item.getWeight());
        values.put("unit" , item.getUnit());
        values.put("wakeuptimehrs" , item.getWtimehrs());
        values.put("Sleeptimehrs" , item.getStimehrs());
        values.put("wakeuptimemin" , item.getWtimemin());
        values.put("Sleeptimemin" , item.getStimemin());
      //  values.put("target" , item.getTaget());
       // values.put("completed" , item.getCompleted());
       return db.update("PROFILE" , values ,"id =?" , new String[]{String.valueOf(item.getId())});

    }
    public void deletprofile(int id){
     SQLiteDatabase db = this.getWritableDatabase();
     items item = new  items();
     db.delete("PROFILE" ,"id =?" , new  String[]{String.valueOf(item.getId())});
     db.close();
    }

    public List<items> getAllprofile() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<items> itemList = new ArrayList<>();
        items item = new items();


        Cursor cursor = db.query("PROFILE" ,
                new String[]{"id" , "Weight" , "Name" , "wakeuptimehrs" , "Sleeptimehrs" ,"unit",
                        "wakeuptimemin","Sleeptimemin" } ,
                null , null , null , null , null);



        if (cursor.moveToFirst()) {
            do {

                item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                item.setName(cursor.getString(cursor.getColumnIndex("Name")));
                item.setWeight(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Weight"))));
                item.setUnit(cursor.getString(cursor.getColumnIndex("unit")));
                item.setStimehrs(cursor.getString(cursor.getColumnIndex("Sleeptimehrs")));
                item.setWtimehrs(cursor.getString(cursor.getColumnIndex("wakeuptimehrs")));
                item.setStimemin(cursor.getString(cursor.getColumnIndex("Sleeptimemin")));
                item.setWtimemin(cursor.getString(cursor.getColumnIndex("wakeuptimemin")));
              //  item.setTaget(cursor.getInt(cursor.getColumnIndex("target")));
                //item.setCompleted(Integer.parseInt(cursor.getString(cursor.getColumnIndex("completed"))));


                //Add to arraylist
                itemList.add(item);
            } while (cursor.moveToNext());
        }
        return itemList;

    }
    public items getprofile(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        items item = new items();
        Cursor cursor = db.query("PROFILE" ,
                new String[]{"id" , "Weight" , "Name" , "wakeuptimehrs" , "Sleeptimehrs" ,"unit",
                        "wakeuptimemin","Sleeptimemin" } ,
                null , null , null , null , "Name");

       if(cursor != null){
           cursor.moveToFirst();

           item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
           item.setName(cursor.getString(cursor.getColumnIndex("Name")));
           item.setWeight(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Weight"))));
           item.setUnit(cursor.getString(cursor.getColumnIndex("unit")));
           item.setStimehrs(cursor.getString(cursor.getColumnIndex("Sleeptimehrs")));
           item.setWtimehrs(cursor.getString(cursor.getColumnIndex("wakeuptimehrs")));
           item.setStimemin(cursor.getString(cursor.getColumnIndex("Sleeptimemin")));
           item.setWtimemin(cursor.getString(cursor.getColumnIndex("wakeuptimemin")));
          // item.setTaget(cursor.getInt(cursor.getColumnIndex("target")));
        // item.setCompleted(Integer.parseInt(cursor.getString(cursor.getColumnIndex("completed"))));

       }

       return item;
    }
    public int getItemsCount() {
        String countQuery = "SELECT * FROM " +"PROFILE";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();

    }


}
