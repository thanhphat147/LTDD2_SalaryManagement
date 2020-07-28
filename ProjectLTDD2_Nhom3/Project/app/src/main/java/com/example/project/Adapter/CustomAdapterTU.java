package com.example.project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.project.Interface.UpdateChamCong;
import com.example.project.Interface.UpdateTamUng;
import com.example.project.Model.ChamCong;
import com.example.project.Model.TamUng;
import com.example.project.R;

import java.util.ArrayList;

public class CustomAdapterTU extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<TamUng> data;

    public CustomAdapterTU(Context context, int resource, ArrayList<TamUng> data) {
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
        TextView tvMaNV= view.findViewById(R.id.tvMaNV);
        TextView tvTenNV = view.findViewById(R.id.tvTenNV);
        TextView tvNgay = view.findViewById(R.id.tvNgay);
        TextView tvSoTien = view.findViewById(R.id.tvSoTien);
        ImageButton btnSua = view.findViewById(R.id.btnSua);
        ImageButton btnXoa = view.findViewById(R.id.btnXoa);
        TamUng tamUng = data.get(position);
        tvMaNV.setText(tamUng.getMaNV());
        tvTenNV.setText(tamUng.getSoPhieu() + "");
        tvNgay.setText(tamUng.getNgay());
        tvSoTien.setText(tamUng.getSoTien() + "");
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateTamUng.class);
                Bundle bundle = new Bundle();
                context.startActivity(intent);
            }
        });
        return  view;
    }
}
