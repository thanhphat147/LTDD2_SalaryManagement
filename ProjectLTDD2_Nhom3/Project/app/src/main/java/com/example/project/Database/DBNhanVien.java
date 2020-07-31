package com.example.project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.example.project.Model.NhanVien;

public class DBNhanVien {
    DBHelper dbHelper;

    public DBNhanVien(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void Them(NhanVien nhanVien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("manv", nhanVien.getMaNV());
        values.put("tennv", nhanVien.getTenNV());
        values.put("ngaysinh", nhanVien.getNgaySinh());
        values.put("gioitinh", nhanVien.getGioiTinh());
        values.put("tenphong", nhanVien.getTenPhong());
        values.put("tienluong", nhanVien.getBacLuong());
        values.put("imagenv", nhanVien.getImageNV());
        db.insert("NhanVien", null, values);
        db.close();
    }

    public void Sua(NhanVien nhanVien) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("manv", nhanVien.getMaNV());
        values.put("tennv", nhanVien.getTenNV());
        values.put("ngaysinh", nhanVien.getNgaySinh());
        values.put("gioitinh", nhanVien.getGioiTinh());
        values.put("tenphong", nhanVien.getTenPhong());
        values.put("tienluong", nhanVien.getBacLuong());
//        values.put("imagenv", nhanVien.getImageNV());
        db.update("NhanVien", values, "manv ='" + nhanVien.getMaNV() + "'", null);
        db.close();
    }


    public void Xoa(NhanVien nhanVien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("NhanVien", "manv ='" + nhanVien.getMaNV() + "'", null);
        db.close();
    }

    public ArrayList<NhanVien> LayDL() {
        ArrayList<NhanVien> data = new ArrayList<>();
        String sql = "select * from NhanVien";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        try {
            cursor.moveToFirst();
            do {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(cursor.getString(0));
                nhanVien.setTenNV(cursor.getString(1));
                nhanVien.setNgaySinh(cursor.getString(2));
                nhanVien.setGioiTinh(cursor.getString(3));
                nhanVien.setTenPhong(cursor.getString(4));
                nhanVien.setBacLuong(cursor.getString(5));
//                nhanVien.setImageNV(cursor.getString(6));
                data.add(nhanVien);
            }
            while (cursor.moveToNext());
            db.close();
        } catch (Exception ex) {
        }

        return data;
    }

    public ArrayList<NhanVien> LayDL(String manv) {
        ArrayList<NhanVien> data = new ArrayList<>();
        String sql = "select * from NhanVien where manv ='" + manv + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        try {
            cursor.moveToFirst();
            do {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(cursor.getString(0));
                nhanVien.setTenNV(cursor.getString(1));
                nhanVien.setNgaySinh(cursor.getString(2));
                nhanVien.setGioiTinh(cursor.getString(3));
                nhanVien.setTenPhong(cursor.getString(4));
                nhanVien.setBacLuong(cursor.getString(5));
//                nhanVien.setImageNV(cursor.getString(6));
                data.add(nhanVien);
            }
            while (cursor.moveToNext());
            db.close();
        } catch (Exception ex) {
        }


        return data;
    }


}
