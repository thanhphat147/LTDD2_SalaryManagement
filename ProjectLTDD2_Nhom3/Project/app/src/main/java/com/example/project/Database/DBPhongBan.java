package com.example.project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.Model.PhongBan;

import java.util.ArrayList;

public class DBPhongBan {
    DBHelperPB dbHelper;

    public DBPhongBan(Context context) {
        dbHelper = new DBHelperPB(context);
    }

    public void Them(PhongBan phongBan) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mapb", phongBan.getMaPhong());
        values.put("tenpb", phongBan.getTenPhong());
        db.insert("PhongBan", null, values);
    }

    public void Sua(PhongBan phongBan) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mapb", phongBan.getMaPhong());
        values.put("tenpb", phongBan.getTenPhong());
        db.update("PhongBan", values, "mapb = '" + phongBan.getMaPhong() + "'", null);
    }

    public void Xoa(PhongBan phongBan) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "Delete from PhongBan where mapb = '" + phongBan.getMaPhong() + "'";
        db.execSQL(sql);
    }

    public ArrayList<PhongBan> LayDL() {
        ArrayList<PhongBan> data = new ArrayList<>();
        String sql = "Select * from PhongBan";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                PhongBan phongBan = new PhongBan();
                phongBan.setMaPhong(cursor.getString(0));
                phongBan.setTenPhong(cursor.getString(1));
                data.add(phongBan);
            } while (cursor.moveToNext());
        } catch (Exception ex){

        }
        return data;
    }

    public ArrayList<PhongBan> LayDL(String mapb) {
        ArrayList<PhongBan> data = new ArrayList<>();
        String sql = "select * from PhongBan where mapb = '" + mapb + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                PhongBan phongBan = new PhongBan();
                phongBan.setMaPhong(cursor.getString(0));
                phongBan.setTenPhong(cursor.getString(1));
                data.add(phongBan);
            } while (cursor.moveToNext());
        } catch (Exception ex){

        }
        return data;
    }
}
