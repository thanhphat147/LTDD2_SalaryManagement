package com.example.kiemtra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;


public class CustomAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<NhanVien> data;
    public CustomAdapter(Context context, int resource, ArrayList<NhanVien> data) {
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
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource, null);
        
        TextView tvMaNV = view.findViewById(R.id.tvMaNV);
        TextView tvTenNV = view.findViewById(R.id.tvTenNV);
        TextView tvNgaySinh = view.findViewById(R.id.tvNgaySinh);
        TextView tvMaPB = view.findViewById(R.id.tvMaPB);
        TextView tvLuong = view.findViewById(R.id.tvLuong);
        
        NhanVien nhanVien = new NhanVien();
        tvMaNV.setText(nhanVien.getMaNV());
        tvTenNV.setText(nhanVien.getTenNV());
        tvNgaySinh.setText(nhanVien.getNgaySinh());
        tvMaPB.setText(nhanVien.getMaPB());
        tvLuong.setText(nhanVien.getTienLuong());

        return view;
    }
}
