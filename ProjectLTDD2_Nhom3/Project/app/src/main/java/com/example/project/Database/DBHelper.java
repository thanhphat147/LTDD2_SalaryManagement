package com.example.project.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "QLNhanVien", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlNhanVien = "create table NhanVien(manv text primary key unique not null, tennv text, ngaysinh text, gioitinh text, tenphong text, tienluong int)";
        db.execSQL(sqlNhanVien);

        String sqlChamCong = "Create table ChamCong (manv text primary key unique not null, ngaychamcong date primary key, songaycong int)";
        db.execSQL(sqlChamCong);

        String sqlPhongBan = "create table PhongBan(mapb text primary key unique not null, tenpb text)";
        db.execSQL(sqlPhongBan);

        String sqlTamUng = "Create table TamUng (manv text primary key unique not null, ngayghicong date primary key, tienung int)";
        db.execSQL(sqlTamUng);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
