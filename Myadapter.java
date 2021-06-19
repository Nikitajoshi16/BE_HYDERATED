package com.example.bottomnavigationactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Myadapter extends ArrayAdapter<String> {
    String [] settings;
    int [] icons ;
    Context context;
    public Myadapter(@NonNull Context context, String[] setting , int[] icon) {
        super(context, R.layout.listviewitem);
        this.settings = setting;
        this.icons = icon;
        this.context = context;
    }

    @Override
    public int getCount() {
        return settings.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.listviewitem,parent,false );
        TextView textView = convertView.findViewById(R.id.textView);
        ImageView imageView = convertView.findViewById(R.id.imageView2);
        imageView.setImageResource(icons[position]);
        textView.setText(settings[position]);
        return convertView;
    }
}
