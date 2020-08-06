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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.Database.DBNhanVien;
import com.example.project.Database.DBTamUng;
import com.example.project.Interface.TamUng.MainActivityTamUng;
import com.example.project.Interface.TamUng.UpdateTamUng;
import com.example.project.Model.NhanVien;
import com.example.project.Model.TamUng;
import com.example.project.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CustomAdapterTU extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<TamUng> data;
    ArrayList<NhanVien> nhanVien = new ArrayList<>();
    Locale localeVN = new Locale("vi", "VN");
    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

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

    private static class Holder {
        TextView tvMaNV, tvTenNV, tvNgayUng, tvSoTien, tvSoPhieu;
        ImageButton btnXoa, btnSua;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        CustomAdapterTU.Holder holder = null;
        if (view == null) {
            holder = new CustomAdapterTU.Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.tvSoPhieu = view.findViewById(R.id.tvSoPhieu);
            holder.tvMaNV = view.findViewById(R.id.tvMaNV);
            holder.tvTenNV = view.findViewById(R.id.tvHoTen);
            holder.tvNgayUng = view.findViewById(R.id.tvNgayTamUng);
            holder.tvSoTien = view.findViewById(R.id.tvTienTamUng);
            holder.btnXoa = view.findViewById(R.id.btnXoa);
            holder.btnSua = view.findViewById(R.id.btnSua);


            view.setTag(holder);
        } else
            holder = (CustomAdapterTU.Holder) view.getTag();

        final TamUng tamUng = data.get(position);


        holder.tvSoPhieu.setText(tamUng.getSoPhieu());
        holder.tvMaNV.setText(tamUng.getMaNV());
        holder.tvSoTien.setText(currencyVN.format(Integer.parseInt(tamUng.getSoTienUng())));
        holder.tvNgayUng.setText(tamUng.getNgayTamUng());
        DBNhanVien dbNhanVien = new DBNhanVien(getContext());
        nhanVien = dbNhanVien.LayNhanVien(tamUng.getMaNV());
        holder.tvTenNV.setText(nhanVien.get(0).getTenNV());

        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateTamUng.class);
                Bundle bundle = new Bundle();
                bundle.putString("sophieu", tamUng.getSoPhieu());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBTamUng dbTamUng = new DBTamUng(getContext());
                dbTamUng.xoaTamUng(tamUng);
                Intent intent = new Intent(getContext(), MainActivityTamUng.class);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
