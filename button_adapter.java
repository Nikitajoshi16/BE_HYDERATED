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

import java.util.ArrayList;

public class button_adapter extends ArrayAdapter<String> {
    ArrayList<String> add = new ArrayList<>();
    ArrayList<Integer> glasses = new ArrayList<>();
    Context context;

    public button_adapter(@NonNull Context context, ArrayList<String> addml , ArrayList<Integer> glass) {
        super(context, R.layout.buttonlist);

        this.add = addml;
        this.glasses = glass;
        this.context = context;

    }

    public int getCount() {
        return add.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.buttonlist,parent,false );
        TextView textView = convertView.findViewById(R.id.textView2);
        ImageView imageView = convertView.findViewById(R.id.imageView);
        imageView.setImageResource(glasses.get(position));
        textView.setText(add.get(position));
        return convertView;
    }


}


