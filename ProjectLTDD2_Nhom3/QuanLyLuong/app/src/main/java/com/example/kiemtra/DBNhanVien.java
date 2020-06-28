package com.example.kiemtra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBNhanVien {
    DBHelper dbHelper;
    public DBNhanVien(Context context){
        dbHelper = new DBHelper(context);
    }

    public void save(NhanVien nhanVien){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("manv",nhanVien.getMaNV());
        values.put("tennv",nhanVien.getTenNV());
        values.put("ngaysinh",nhanVien.getNgaySinh());
        values.put("maphong",nhanVien.getMaPB());
        values.put("tienluong",nhanVien.getTienLuong());
        db.insert("NhanVien",null, values);
    }

    public void save1( ArrayList<NhanVien> nhanVien){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for(int i = 0; i < nhanVien.size(); i++)
        {
            save(nhanVien.get(i));
        }
    }

    public ArrayList<NhanVien> read(){
        ArrayList<NhanVien> data = new ArrayList<>();
        String sql="select * from NhanVien";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            NhanVien nhanVien = new NhanVien();
            nhanVien.setMaNV(cursor.getString(0));
            nhanVien.setTenNV(cursor.getString(1));
            nhanVien.setNgaySinh(cursor.getString(2));
            nhanVien.setMaPB(cursor.getString(3));
            nhanVien.setTienLuong(cursor.getString(4));
            data.add(nhanVien);
        }while(cursor.moveToNext());
        return data;
    }


}

