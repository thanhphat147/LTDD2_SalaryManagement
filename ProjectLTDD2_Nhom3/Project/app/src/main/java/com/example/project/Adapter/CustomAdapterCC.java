package com.example.project.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.project.Interface.UpdateChamCong;
import com.example.project.Interface.UpdatePhongBan;
import com.example.project.Model.ChamCong;
import com.example.project.R;

import java.util.ArrayList;

public class CustomAdapterCC extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ChamCong> data;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource, null);
        TextView tvMaNV= view.findViewById(R.id.tvMaNV);
        TextView tvNgayGhiSo = view.findViewById(R.id.tvNgayGhiSo);
        TextView tvSoNgayCong = view.findViewById(R.id.tvSoNgayCong);
        Button btnSua = view.findViewById(R.id.btnSua);
        Button btnXoa = view.findViewById(R.id.btnXoa);
        ChamCong chamCong = data.get(position);
        tvMaNV.setText(chamCong.getMaNV());
        tvNgayGhiSo.setText(chamCong.getNgayGhiSo());
        tvSoNgayCong.setText(chamCong.getSoNgayCong() + "");
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateChamCong.class);
                Bundle bundle = new Bundle();
                context.startActivity(intent);
            }
        });
        return  view;
    }
}
