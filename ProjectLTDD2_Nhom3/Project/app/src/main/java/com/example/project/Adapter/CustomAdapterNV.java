package com.example.project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.project.Database.DBNhanVien;
import com.example.project.Interface.AddChamCong;
import com.example.project.Interface.AddTamUng;
import com.example.project.Interface.MainActivityChamCong;
import com.example.project.Interface.MainActivityNhanVien;
import com.example.project.Interface.MainActivityTamUng;
import com.example.project.Interface.UpdateNhanVien;
import com.example.project.Model.NhanVien;
import com.example.project.R;
import java.util.ArrayList;



public class CustomAdapterNV extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<NhanVien> data;
    ArrayList<NhanVien> data_DS;

    public CustomAdapterNV(Context context, int resource, ArrayList<NhanVien> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.data_DS = new ArrayList<NhanVien>();
        this.data_DS.addAll(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }
    private static class Holder {
        TextView tvMaNV;
        TextView tvTenNV;
        TextView tvNgaySinh;
        TextView tvGioiTinh;
        TextView tvPhongBan;
        TextView tvLuong;
        ImageButton btnXoa;
        ImageButton btnSua;
        ImageButton btnChamCong;
        ImageButton btnTamUng;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if(view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.tvMaNV = view.findViewById(R.id.tvMaNV);
            holder.tvTenNV = view.findViewById(R.id.tvTenNV);
            holder.tvNgaySinh = view.findViewById(R.id.tvNgaySinh);
            holder.tvGioiTinh = view.findViewById(R.id.tvGioiTinh);
            holder.tvPhongBan = view.findViewById(R.id.tvPhongBan);
            holder.tvLuong = view.findViewById(R.id.tvLuong);
            holder.btnXoa = view.findViewById(R.id.btnXoa);
            holder.btnSua = view.findViewById(R.id.btnSua);
            holder.btnChamCong = view.findViewById(R.id.btnChamCong);
            holder.btnTamUng = view.findViewById(R.id.btnTamUng);
            view.setTag(holder);
        }
        else
            holder=(Holder)view.getTag();

        final NhanVien nhanVien = data.get(position);

        holder.tvMaNV.setText(nhanVien.getMaNV());
        holder.tvTenNV.setText(nhanVien.getTenNV());
        holder.tvNgaySinh.setText(nhanVien.getNgaySinh());
        holder.tvGioiTinh.setText(nhanVien.getGioiTinh());
        holder.tvPhongBan.setText(nhanVien.getTenPhong());
        holder.tvLuong.setText(nhanVien.getBacLuong());
        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateNhanVien.class);
                Bundle bundle = new Bundle();
                bundle.putString("ma",nhanVien.getMaNV());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XoaDL(nhanVien);
                notifyDataSetChanged();
                Intent intent = new Intent(context, MainActivityNhanVien.class);
                context.startActivity(intent);
            }
        });
        holder.btnChamCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddChamCong.class);
                Bundle bundle = new Bundle();
                bundle.putString("ma",nhanVien.getMaNV());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.btnTamUng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddTamUng.class);
                Bundle bundle = new Bundle();
                bundle.putString("ma",nhanVien.getMaNV());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return view;
    }

//    //filter
//    public void filter (String charText){
//        charText = charText.toLowerCase(Locale.getDefault());
//        data.clear();
//        if (charText.length()==0){
//            data.addAll(data_DS);
//        }
//        else {
//            for (SinhVien model : data_DS){
//                if (model.getTenSV().toLowerCase(Locale.getDefault())
//                        .contains(charText) ){
//                    data.add(model);
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }
    public void XoaDL(NhanVien nhanVien) {
        DBNhanVien dbNhanVien = new DBNhanVien(context);
        dbNhanVien.Xoa(nhanVien);
        dbNhanVien.LayDL();
    }
}
