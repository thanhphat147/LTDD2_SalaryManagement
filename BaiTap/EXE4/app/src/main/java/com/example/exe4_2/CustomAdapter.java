package com.example.exe4_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<String> data;

    public CustomAdapter(Context context, int resource, ArrayList<String> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource, null);
        ImageView imgPerson = view.findViewById(R.id.imgPerson);
        TextView tvSDT = view.findViewById(R.id.tvSDT);
        //SDT sdt = data.get(position);
        String a = data.get(position);
        tvSDT.setText(a);
        return  view;
    }
}
