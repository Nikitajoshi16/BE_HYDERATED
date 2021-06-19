package com.example.bottomnavigationactivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bottomnavigationactivity.ITEM.items;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment1<var> extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ListView listView;
    TextView target ;
    String savedd = "";
     TextView complete ;private int adding ;
    ArrayList<String> arrayList  = new ArrayList<>();
   ArrayList<Integer> icon = new ArrayList<>();
   database database = null;
   String currdate = "";
   TextView gototext ;
  //  SharedPreferences preferences =this.getActivity().getPreferences(Context.MODE_PRIVATE) ;
  SharedPreferences preferences;
int index ;
   private  Sqlite_helper db = null;
    List<items> itemsList = new ArrayList<>();
    items item = new items();
    int x;
      button_adapter adapter;
  // Gson gson = new Gson();



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        db = new Sqlite_helper(activity);
        database = new database(activity);
        // preferences = activity.getPreferences(Context.MODE_PRIVATE);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       preferences = context.getSharedPreferences("pref", 0);
  }


    public BlankFragment1() {
        // Required empty public constructor
    }

int targetml;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment1 newInstance(String param1, String param2) {
        BlankFragment1 fragment = new BlankFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }




    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // getting current date


        Date d = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy" , Locale.getDefault());
        currdate = df.format(d);

        savedd = preferences.getString("saveddate" , "date");
      //  preferences.edit().putBoolean("running" , true);



        // Inflate the  layout for this fragment
       View v = inflater.inflate(R.layout.fragment_blank1, container,false);
        final FloatingActionButton add = v.findViewById(R.id.floatingActionButton2);
        target = v.findViewById(R.id.target);
        gototext = v.findViewById(R.id.gototext);
        complete = v.findViewById(R.id.completed);
         listView = v.findViewById(R.id.listview);
        adding =   preferences.getInt("highscore" , 0);
        targetml = preferences.getInt("target" , 0);
        target.setText(String.valueOf(targetml));
        complete.setText(String.valueOf(adding));
        firsttime();

        if(savedd .equals(currdate)){
        int size =  preferences.getInt("size" , 0);
        for (int i = 0 ; i<size ; i++){
            arrayList.add(preferences.getString(String.valueOf(i), " "));
            icon.add(preferences.getInt( "icon" + i , 0));
        }
            adapter = new button_adapter(getActivity(),arrayList,icon);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    index = i;
                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity()) ;
                    View mview = getLayoutInflater().inflate(R.layout.dialogbox,null);
                    final EditText text =mview.findViewById(R.id.ml);
                    Button button = mview.findViewById(R.id.delete);
                    Button ok = mview.findViewById(R.id.update);
                    text.setText(arrayList.get(index));
                    alert.setView(mview);


                    final AlertDialog alertDialog = alert.create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.show();
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            adding = adding - Integer.parseInt(arrayList.get(index));

                            arrayList.set(index ,text.getText().toString());
                            adding = adding + Integer.parseInt(arrayList.get(index));
                            completedtext(adding);
                            adapter.notifyDataSetChanged();
                            String newtext = arrayList.get(index);
                            alertDialog.dismiss();
                            text.setText(newtext);
                        }
                    });
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            adding = adding- Integer.parseInt(arrayList.get(index));
                            completedtext(adding);
                            arrayList.remove(index);
                            adapter.notifyDataSetChanged();
                            alertDialog.dismiss();

                        }
                    });
                }
            });




        }
        else {
            // else if day changed
            adding = 0;

            complete.setText("0000");
        }



        itemsList = db.getAllprofile();
        for( items item:itemsList) {Log.d("hii", "onCreateView: " + item.getWeight());
            if(targetml == 0){
                if(String.valueOf(item.getUnit()).equals("Kg")){
                    targetml =(((Integer.parseInt(String.valueOf(item.getWeight())))*2) *2/3)*29;
                    target.setText(String.valueOf(targetml));
                }
                else if(String.valueOf(item.getUnit()).equals("Lbs")){
                    targetml =(Integer.parseInt(String.valueOf(item.getWeight())) *2/3)*29;
                    target.setText(String.valueOf(targetml));

                }
            }
            else{
                target.setText(String.valueOf(targetml));
            }
        }


       // target.setText(String.valueOf(itemsList.size()));



         add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 if(adding < Integer.parseInt(target.getText().toString())){
        arrayList.add(String.valueOf(preferences.getInt("add" , 200)));
        icon.add( preferences.getInt("icont",R.drawable.glass2));
                 adding = adding + preferences.getInt("add" , 200);
                // for (items items :itemsList){
                 completedtext(adding);

                  adapter = new button_adapter(getActivity(),arrayList,icon);

                 listView.setAdapter(adapter);
                     if(adding >=Integer.parseInt(target.getText().toString())){
                         AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                         View view1 = getLayoutInflater().inflate(R.layout.hurray , null);
                         ImageButton yeah = view1.findViewById(R.id.imageButton2);
                         builder.setView(view1);

                         final AlertDialog dialog = builder.create();
                         dialog.show();

                         yeah.setOnClickListener(new View.OnClickListener() {
                             @Override
                             public void onClick(View view) {
                                 dialog.dismiss();
                             }
                         });


                     }
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        index = i;
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity()) ;
        View mview = getLayoutInflater().inflate(R.layout.dialogbox,null);
        final EditText text =mview.findViewById(R.id.ml);
        Button button = mview.findViewById(R.id.delete);
        Button ok = mview.findViewById(R.id.update);
        text.setText(arrayList.get(index));
        alert.setView(mview);


        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adding = adding - Integer.parseInt(arrayList.get(index));

                arrayList.set(index ,text.getText().toString());
                adding = adding + Integer.parseInt(arrayList.get(index));
                completedtext(adding);
                adapter.notifyDataSetChanged();
                String newtext = arrayList.get(index);
                alertDialog.dismiss();
                text.setText(newtext);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adding = adding- Integer.parseInt(arrayList.get(index));
                completedtext(adding);
                arrayList.remove(index);
                adapter.notifyDataSetChanged();
                alertDialog.dismiss();

            }
        });
    }
});



             }
                 else{
                     preferences.edit().putBoolean("running" , false).apply();
                 }


             }
         });
            // adding = preferences.getInt("completed" , 0);
             //completedtext(adding);
        return v;
    }
    public void firsttime(){
        String firsttime = preferences.getString("firstinstall" , "");
        if(firsttime.equals("Yes")){
        Log.d("gg", "firsttime: ");}

        else{
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("firstinstall" , "Yes");
            editor.apply();
            //   running = preferences2.getBoolean("running" , false);
            // preferences.edit().putBoolean("running" , running).apply();
            gototext.setText("Go to Settings > Notifications and switch on notifications to start getting reminders");

            Handler handler2 = new Handler();
            handler2.postDelayed(new Runnable() {
                @Override
                public void run() {
                  gototext.setText("");

                }
            },10000);}

    }

    @Override
    public void onPause() {
        super.onPause();
        preferences.edit().putInt("highscore", adding).apply();
       preferences.edit().putInt("target" , Integer.parseInt(String.valueOf(target.getText()))).apply();
     int i = preferences.getInt("target" , 0);
  //     preferences.edit().putInt("add" , preferences.getInt("add" , 200));
     preferences.edit().putInt("icont" , R.drawable.glass2).apply();
      //Log.d("tag", "onPause: " + i);
    preferences.edit().putString("saveddate" , currdate).apply();

      for(int j = 0; j<arrayList.size() ; j++){
         preferences.edit().putString(String.valueOf(j) , arrayList.get(j)).apply();
      }
      for (int i1 = 0; i1<icon.size(); i1++){
         preferences.edit().putInt("icon" + i1 , icon.get(i1)).apply();
      }

      preferences.edit().putInt("size" , arrayList.size()).apply();



            Log.d("saved", "onPause: " + savedd + " " + currdate);




    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
       // button_adapter button_adapter = new button_adapter(getActivity(),arrayList,icon);
                outState.putStringArrayList("listvalue" , arrayList);
                outState.putIntegerArrayList("listicon" ,icon);
    }



    public void completedtext(int hs){

       // x = preferences.getInt("highscore", 0);
        complete.setText(String.valueOf(hs));
   }



}