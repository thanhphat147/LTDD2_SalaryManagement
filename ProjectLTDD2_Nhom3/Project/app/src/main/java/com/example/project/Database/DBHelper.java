package com.example.project.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "SQLQuanLyLuong", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlPhongBan = "Create table PhongBan (mapb text PRIMARY KEY UNIQUE NOT NULL , tenpb text) ";
        sqLiteDatabase.execSQL(sqlPhongBan);
        String sqlNhanVien = "Create table NhanVien (manv text PRIMARY KEY UNIQUE NOT NULL, tennv text, ngaysinh text, gioitinh text,mapb text, hesoluong text, hinh Blob)";
        sqLiteDatabase.execSQL(sqlNhanVien);
        String sqlTamUng = "Create table TamUng (sophieu text PRIMARY KEY UNIQUE NOT NULL, ngay text, sotien text, manv text)";
        sqLiteDatabase.execSQL(sqlTamUng);
        String sqlChamCong = "Create table ChamCong (manv text PRIMARY KEY UNIQUE NOT NULL, ngaycham text PRIMARY KEY NOT NULL, songaycong text)";
        sqLiteDatabase.execSQL(sqlChamCong);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}
