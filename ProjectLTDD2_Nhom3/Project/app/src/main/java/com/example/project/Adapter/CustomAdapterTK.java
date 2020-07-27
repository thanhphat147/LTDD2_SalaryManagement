package com.example.project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.Interface.UpdateTamUng;
import com.example.project.Model.TamUng;
import com.example.project.Model.ThongKe;
import com.example.project.R;

import java.util.ArrayList;

public class CustomAdapterTK extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ThongKe> data;

    public CustomAdapterTK(Context context, int resource, ArrayList<ThongKe> data) {
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
        ImageView imgGioiTinh = view.findViewById(R.id.imgGioiTinh);
        TextView tvMaNV= view.findViewById(R.id.tvMaNV);
        TextView tvHoTen = view.findViewById(R.id.tvHoTen);
        TextView tvGioiTinh = view.findViewById(R.id.tvGioiTinh);
        TextView tvSoNgayCong = view.findViewById(R.id.tvSoNgayCong);
        TextView tvTienTamUng = view.findViewById(R.id.tvTienTamUng);
        TextView tvTienLuong = view.findViewById(R.id.tvTienLuong);
        ThongKe thongKe = data.get(position);
        tvMaNV.setText(thongKe.getMaNV());
        tvHoTen.setText(thongKe.getHoTen());
        if (thongKe.getGioiTinh() == "Nam")
            imgGioiTinh.setImageResource(R.drawable.men);
        if (thongKe.getGioiTinh() == "Nu")
            imgGioiTinh.setImageResource(R.drawable.woman);
        tvSoNgayCong.setText(thongKe.getSoNgayCong() + "");
        tvTienTamUng.setText(thongKe.getTienTamUng() + "");
        tvTienLuong.setText(thongKe.getTienLuong() + "");
        return  view;
    }
}
