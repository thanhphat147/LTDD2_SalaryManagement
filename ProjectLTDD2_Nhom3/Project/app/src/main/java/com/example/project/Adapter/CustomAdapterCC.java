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

import com.example.project.Database.DBChamCong;
import com.example.project.Database.DBNhanVien;
import com.example.project.Interface.ChamCong.MainActivityChamCong;
import com.example.project.Interface.ChamCong.UpdateChamCong;
import com.example.project.Model.ChamCong;
import com.example.project.Model.NhanVien;
import com.example.project.R;

import java.util.ArrayList;

public class CustomAdapterCC extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ChamCong> data;
    ArrayList<NhanVien> nhanVien = new ArrayList<>();

    public CustomAdapterCC(Context context, int resource, ArrayList<ChamCong> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    private static class Holder {
        TextView tvMaNV, tvTenNV, tvNgayChamCong, tvSoNgayCong;
        ImageButton btnSua, btnXoa;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        View view = convertView;
        CustomAdapterCC.Holder holder = null;
        if (view == null) {
            holder = new CustomAdapterCC.Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.tvMaNV = view.findViewById(R.id.tvMaNV);
            holder.tvTenNV = view.findViewById(R.id.tvHoTen);
            holder.tvNgayChamCong = view.findViewById(R.id.tvNgayChamCong);
            holder.tvSoNgayCong = view.findViewById(R.id.tvSoNgayCong);
            holder.btnXoa = view.findViewById(R.id.btnXoa);
            holder.btnSua = view.findViewById(R.id.btnSua);
            view.setTag(holder);
        } else
            holder = (CustomAdapterCC.Holder) view.getTag();


        final ChamCong chamCong = data.get(position);
        holder.tvMaNV.setText(chamCong.getMaNV());
        holder.tvNgayChamCong.setText(chamCong.getThangChamCong());
        holder.tvSoNgayCong.setText(chamCong.getSoNgayCong());
        DBNhanVien dbNhanVien = new DBNhanVien(getContext());
        nhanVien = dbNhanVien.LayNhanVien(chamCong.getMaNV());
        holder.tvTenNV.setText(nhanVien.get(0).getTenNV());
        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateChamCong.class);
                Bundle bundle = new Bundle();
                bundle.putString("manv", chamCong.getMaNV());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBChamCong dbChamCong = new DBChamCong(getContext());
                dbChamCong.xoaChamCong(chamCong);
                Intent intent = new Intent(getContext(), MainActivityChamCong.class);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
