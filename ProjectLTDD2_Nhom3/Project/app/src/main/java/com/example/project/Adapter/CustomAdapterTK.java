package com.example.project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.Interface.ThongKe.BieuDoThongKe;
import com.example.project.Interface.ThongKe.ChiTietThongKe;
import com.example.project.Model.ThongKe;
import com.example.project.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

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

    private static class Holder {
        TextView tvMaNV, tvTenNV, tvNgayChamCong, tvTenPhong, tvThucLanh;
        Button btnChiTiet, btnBieuDo;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        CustomAdapterTK.Holder holder = null;
        if (view == null) {
            holder = new CustomAdapterTK.Holder();
            view = LayoutInflater.from(context).inflate(resource, null);

            holder.tvMaNV = view.findViewById(R.id.tvMaNV);
            holder.tvTenNV = view.findViewById(R.id.tvTenNV);
            holder.tvNgayChamCong = view.findViewById(R.id.tvNgayChamCong);
            holder.tvTenPhong = view.findViewById(R.id.tvTenPhongBan);
            holder.tvThucLanh = view.findViewById(R.id.tvThucLanh);
            holder.btnChiTiet = view.findViewById(R.id.btnChiTiet);
            holder.btnBieuDo = view.findViewById(R.id.btnBieuDo);

            view.setTag(holder);
        } else
            holder = (CustomAdapterTK.Holder) view.getTag();
        final ThongKe thongKe = data.get(position);
        holder.tvMaNV.setText(thongKe.getMaNV());
        holder.tvTenNV.setText(thongKe.getTenNV());
        holder.tvTenPhong.setText(thongKe.getTenPhong());
        holder.tvNgayChamCong.setText(thongKe.getNgayChamCong());
        int luong = 0;
        int ngayCong = Integer.parseInt(thongKe.getSoNgayCong());
        int heSoLuong = Integer.parseInt(thongKe.getHeSoLuong());
        int tamUng = Integer.parseInt(thongKe.getTienTamUng());
        luong = (heSoLuong * ngayCong);
        thongKe.setLuongCoBan(luong + "");
        int thucLanh = 0;
        thucLanh = luong - tamUng;
        thongKe.setLuongThucLanh(thucLanh + "");
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

        holder.tvThucLanh.setText(currencyVN.format(Integer.parseInt(thongKe.getLuongThucLanh())));


        holder.btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChiTietThongKe.class);
                Bundle bundle = new Bundle();
                bundle.putString("ngaycham", thongKe.getNgayChamCong());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        holder.btnBieuDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BieuDoThongKe.class);
                Bundle bundle = new Bundle();
                bundle.putString("manv", thongKe.getMaNV());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
