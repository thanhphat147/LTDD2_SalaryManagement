package com.example.project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.Model.PhongBan;

import java.util.ArrayList;
import java.util.List;

public class DBPhongBan {
    DBHelper dbHelper;

    public DBPhongBan(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void Them(PhongBan phongBan) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mapb", phongBan.getMaPhong());
        values.put("tenpb", phongBan.getTenPhong());
        db.insert("PhongBan", null, values);
        db.close();
    }

    public void Sua(PhongBan phongBan) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mapb", phongBan.getMaPhong());
        values.put("tenpb", phongBan.getTenPhong());
        db.update("PhongBan", values, "mapb = '" + phongBan.getMaPhong() + "'", null);
        db.close();
    }

    public void Xoa(PhongBan phongBan) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "Delete from PhongBan where mapb = '" + phongBan.getMaPhong() + "'";
        db.execSQL(sql);
        db.close();
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
            ex.printStackTrace();
        }
        db.close();
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
            ex.printStackTrace();
        }
        return data;
    }


    public ArrayList<String> LayDSPhong() {
        ArrayList<String> ds = new ArrayList<>();
        String sql = "select tenpb from PhongBan";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                PhongBan data = new PhongBan();
                String phong = "";
                data.setTenPhong(cursor.getString(0));
                phong = data.getTenPhong().toString();
                ds.add(phong);
            } while (cursor.moveToNext());
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return ds;
    }

    //lấy tên phòng từ mã phòng
    public String layTenPhong(String maPhong) {
        String tenPhong = "";
        String sql = "SELECT tenpb FROM PhongBan WHERE mapb LIKE \"%" + maPhong + "%\" ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        try {
            cursor.moveToFirst();
            do {
                PhongBan phongBan = new PhongBan();
                phongBan.setMaPhong(cursor.getString(0));
                tenPhong = phongBan.getMaPhong();
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tenPhong;
    }

    //Kiểm tra ràng buộc giữa PhongBan và NhanVien khi xóa phòng
    public boolean checkXoaPhong(String maPhong) {
        boolean check = false;
        String sql = "SELECT count(*) FROM NhanVien WHERE mapb LIKE \"" + maPhong + "\" ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if (count > 0) {
            check = true;
        }
        return check;
    }

    //Kiểm tra Mã phòng là duy nhất
    public boolean checkMaPhong(String maPhong) {
        boolean check = false;
        String sql = "SELECT count(*) FROM PhongBan WHERE mapb LIKE \"" + maPhong + "\" ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if (count > 0) {
            check = true;
        }
        return check;
    }
}
