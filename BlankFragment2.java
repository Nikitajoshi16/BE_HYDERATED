package com.example.bottomnavigationactivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.fragment.app.Fragment;

import android.provider.AlarmClock;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottomnavigationactivity.ITEM.items;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Boolean ischecked;
    ListView listView;
    SharedPreferences preferences;
    TextView targetT;
    TextView quantity;
    Boolean frstt;
    Spinner editu;
    ImageButton editq;
    ImageButton editt;
    Button ok1;
    int alarmhr;
    int alarmmin;
    int goal;
    EditText tt;
    int targetml;
    String un;
    int adding ;
    TextView edit;
    TextView button;
    TextView name;
    TextView weight;
    TextView wtime;
    TextView stime;
    TextView unit;
    Switch s2;

    String na ;
    int we;
    String wth;
    String wtm;
    String sth;
    String stm;

    ImageButton ename;
    ImageButton eweit ;
            ImageButton ewtime;
    ImageButton estime;


String[] spin ;
ArrayAdapter<String> arrayAdapter ;


    Sqlite_helper db ;
    database database;
   List<items> itemsList = new ArrayList<>();
    items item = new items();


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


    public BlankFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment2 newInstance(String param1, String param2) {
        BlankFragment2 fragment = new BlankFragment2();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank2, container,false);
        listView = v.findViewById(R.id.listview);
        String []names = {"General Settings" , "Personal information", "Notifications", "Rate us" };
        int[] icon = {R.drawable.settings ,R.drawable.profile,R.drawable.notification ,R.drawable.rate };
        Myadapter myadapter = new Myadapter(getActivity(),names , icon);
        listView.setAdapter(myadapter);
        goal = preferences.getInt("target" , 0);
       // Log.d("tag", "onCreateView: added" + goal);
        adding = preferences.getInt("add" , 200);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                switch (position){
                    case 0:
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        final View v =getLayoutInflater().inflate(R.layout.generalsettings ,null);
                        builder.setView(v);
                        goal = preferences.getInt("target" , 0);
                        Log.d("tag", "onCreateView: added" + goal);

                        quantity = v.findViewById(R.id.quantity);
                        targetT = v.findViewById(R.id.targettext);
                        ok1 = v.findViewById(R.id.button2);
                        editq = v.findViewById(R.id.editq);
                        editt = v.findViewById(R.id.edittarget);
                        editu = v.findViewById(R.id.editunit);
                        itemsList = db.getAllprofile();

                        for (items i : itemsList){
                            if(i.getUnit().equals("Kg")){
                                spin = new String[]{"Kg" , "Lbs"};
                            }
                            else{
                                spin = new String[]{"Lbs" , "Kg"};

                            }

                        }

                        arrayAdapter = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_spinner_item , spin);

                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        editu.setAdapter(arrayAdapter);






quantity.setText(String.valueOf(adding));
                        targetT.setText(String.valueOf(goal));
                        final AlertDialog dialog = builder.create();
                        dialog.show();




                        editt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                             AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                             View view1 = getLayoutInflater().inflate(R.layout.target,null);
                             builder1.setView(view1);

                           final AlertDialog d =  builder1.create();
                           d.show();

                                 tt = view1.findViewById(R.id.tted);
                                Button update = view1.findViewById(R.id.update);

                                tt.setText(String.valueOf(goal));

                                update.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                       goal = Integer.parseInt(String.valueOf(tt.getText()));
                                       preferences.edit().putInt("target" , goal).apply();
                                       targetT.setText(String.valueOf(goal));

                                       d.dismiss();
                                    }
                                });

                            }
                        });
                        editq.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                                View view1 = getLayoutInflater().inflate(R.layout.glass,null);
                                builder2.setView(view1);
                               final AlertDialog d = builder2.create();
                               d.show();

                                  Button button = view1.findViewById(R.id.button3);
                                  RadioButton ml100 = view1.findViewById(R.id.hundml);
                                RadioButton ml200 = view1.findViewById(R.id.twohundml);
                                RadioButton ml300 = view1.findViewById(R.id.threehundml);
                                RadioButton ml400 = view1.findViewById(R.id.fourhundml);
                                RadioButton ml500 = view1.findViewById(R.id.fivehundml);

                                ml100.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        adding = 100;
                                        quantity.setText(String.valueOf(adding));
                                    }
                                });

                                ml200.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        adding = 200;

                                        quantity.setText(String.valueOf(adding));

                                    }
                                });

                                ml300.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        adding = 300;

                                        quantity.setText(String.valueOf(adding));
                                    }

                                });

                                ml400.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    adding = 400;
                                    quantity.setText(String.valueOf(adding));
                                    }
                                });

                                ml500.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        adding = 500;

                                        quantity.setText(String.valueOf(adding));

                                    }
                                });


                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        preferences.edit().putInt("add" , adding).apply();
                                        d.dismiss();

                                    }
                                });

                            }
                        });

                        ok1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                preferences.edit().putInt("target" , goal).apply();
                                preferences.edit().putInt("add" , adding);
                                itemsList = db.getAllprofile();
                                for(items i : itemsList) {
                                    na = i.getName();
                                    we = i.getWeight();
                                    wth = i.getWtimehrs();
                                    sth = i.getStimehrs();
                                    wtm = i.getWtimemin();
                                    stm = i.getStimemin();

                                }
                                    items it = new items();
                                    it.setName(na);
                                    it.setWeight(we);
                                    it.setWtimehrs(wth);
                                    it.setWtimemin(wtm);
                                    it.setStimehrs(sth);
                                    it.setStimemin(stm);
                                    it.setUnit(String.valueOf(editu.getSelectedItem()));
                                    db.addprofile(it);


                                    dialog.dismiss();

                            }
                        });
                        break;
                    case 1:

                        final  AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                        View v1 = getLayoutInflater().inflate(R.layout.profileinfo,null);

                         name = v1.findViewById(R.id.textView17);
                         weight = v1.findViewById(R.id.textweight);
                         wtime = v1.findViewById(R.id.texttime);
                         stime = v1.findViewById(R.id.textsleep);
                         unit = v1.findViewById(R.id.unit);
                         itemsList = db.getAllprofile();

                         for(items i : itemsList){
                              na = i.getName();
                              we =i.getWeight();
                              wth = i.getWtimehrs() ;
                              sth = i.getStimehrs() ;
                              wtm = i.getWtimemin();
                              stm = i.getStimemin();
                              un = i.getUnit();


                             name.setText(String.valueOf(i.getName()));
                             weight.setText(String.valueOf(i.getWeight()));
                             wtime.setText(String.valueOf(new DecimalFormat("00").
                                     format(Integer.parseInt(i.getWtimehrs())) + " : " + new DecimalFormat("00").
                                     format(Integer.parseInt(i.getWtimemin()))));
                             stime.setText(String.valueOf(new DecimalFormat("00").
                                     format(Integer.parseInt(i.getStimehrs())) + " : " +
                                     new DecimalFormat("00").
                                     format(Integer.parseInt(i.getStimemin()))));

                             unit.setText(String.valueOf(i.getUnit()));
                         }





                        name.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                                View view1 = getLayoutInflater().inflate(R.layout.editpro , null);
                                builder2.setView(view1);
                                edit = view1.findViewById(R.id.edit);
                                button = view1.findViewById(R.id.updateb);
                                edit.setText(name.getText());
                                final AlertDialog dialog1 = builder2.create();
                                dialog1.show();
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        items it = new items();
                                      it.setName(edit.getText().toString().trim());
                                      it.setWeight(we);
                                      it.setWtimehrs(wth);
                                      it.setWtimemin(wtm);
                                      it.setStimehrs(sth);
                                      it.setStimemin(stm);
                                      it.setUnit(un);
                                      db.addprofile(it);

                                      itemsList = db.getAllprofile();
                                      for(items i : itemsList){
                                          Log.d("tag", "onClick: "  + i.getName());
                                          na = i.getName();
                                      }

                                      name.setText(edit.getText().toString());



                                      dialog1.dismiss();
                                    }
                                });
                            }
                        });
                        weight.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                                View view1 = getLayoutInflater().inflate(R.layout.editpro , null);
                                builder2.setView(view1);
                                edit = view1.findViewById(R.id.edit);
                                button = view1.findViewById(R.id.updateb);
                                edit.setText(weight.getText());
                                final AlertDialog dialog1 = builder2.create();
                                dialog1.show();
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        items it = new items();
                                        it.setName(na);
                                        it.setWeight(Integer.parseInt(edit.getText().toString()));
                                        it.setWtimehrs(wth);
                                        it.setWtimemin(wtm);
                                        it.setStimehrs(sth);
                                        it.setStimemin(stm);
                                        it.setUnit(un);
                                        db.addprofile(it);
                                        itemsList = db.getAllprofile();
                                        for(items i : itemsList){
                                            weight.setText(String.valueOf(i.getWeight()));
                                        }
                                        for( items item:itemsList) {Log.d("hii", "onCreateView: "
                                                + item.getWeight());

                                                if(String.valueOf(item.getUnit()).equals("Kg")){
                                                    targetml =(((Integer.parseInt(String.valueOf
                                                            (item.getWeight())))*2) *2/3)*29;

                                                    preferences.edit().putInt("target" , targetml).apply();

                                                }
                                                else if(String.valueOf(item.getUnit()).equals("Lbs")){
                                                    targetml =(Integer.parseInt(String.valueOf
                                                            (item.getWeight())) *2/3)*29;

                                                    preferences.edit().putInt("target" , targetml).apply();

                                                }}
                                        dialog1.dismiss();
                                    }
                                });
                            }

                        });


                        builder1.setView(v1);
                        AlertDialog dialog1 = builder1.create();
                        dialog1.show();
                        break;
                        case 2:
                            final  AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                            View v2 = getLayoutInflater().inflate(R.layout.notification,null);
                            builder2.setView(v2);
                            final AlertDialog dialog2 = builder2.create();
                            dialog2.show();

                              s2 = v2.findViewById(R.id.switch1);
                              Button button = v2.findViewById(R.id.button4);
                               ischecked = preferences.getBoolean("checked",s2.isChecked());
                               s2.setChecked(ischecked);
                                 frstt = preferences.getBoolean("firstt" , false);
                               button.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       ischecked = s2.isChecked();

                                       if(ischecked){
                                           Log.d("issfrr", "onClick: " + frstt + ischecked);
                                           if(!frstt){
                                               frstt = true;

                                               setalarm();





                                           }
                                           }
                                       else{
                                           cancelalarm();
                                           frstt = false;
                                       }
                                       preferences.edit().putBoolean("checked" ,ischecked ).apply();
                                       preferences.edit().putBoolean("firstt" ,frstt).apply();
                                       dialog2.dismiss();

                                   }
                               });





                }
            }
        });



        return v;

    }



    private void cancelalarm() {
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(),alert.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(),1,intent,0);

        alarmManager.cancel(pendingIntent);
    }

    private void setalarm() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE,39);
        calendar.set(Calendar.SECOND,0);

       // Intent resultIntent = new Intent(getActivity(), MainActivity.class);
// Create the TaskStackBuilder and add the intent, which inflates the back stack
      //  TaskStackBuilder stackBuilder = TaskStackBuilder.create(getActivity());
       // stackBuilder.addNextIntentWithParentStack(resultIntent);

//PendingIntent pendingIntent2 = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(),alert.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(),1,intent,0);
       alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + 60*1000*60*1,
              60*60*1000*1,
               pendingIntent);

       // NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity());
      //  builder.setContentIntent(pendingIntent2);


//        Intent intent =new Intent(AlarmClock.ACTION_SET_ALARM);
//
//        intent.putExtra(AlarmClock.EXTRA_HOUR,alarmhr);
//        intent.putExtra(AlarmClock.EXTRA_MINUTES,alarmmin);
//        intent.putExtra(AlarmClock.EXTRA_MESSAGE , "Hey!! its time to have some water... don't ignore me");
//         intent.putExtra(AlarmClock.EXTRA_SKIP_UI ,false);

//
//        if(intent.resolveActivity(getActivity().getPackageManager()) !=null){
//            startActivity(intent);}
//        else {
//            Toast.makeText(getActivity(),"there is no alamclock app",Toast.LENGTH_SHORT).show();
//        }

    }


}