package com.example.project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.example.project.Model.NhanVien;
import com.example.project.Model.PhongBan;
import com.example.project.Model.ThongKe;

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
        values.put("mapb", nhanVien.getMaPhong());
        values.put("hesoluong", nhanVien.getBacLuong());
        values.put("imagenv", nhanVien.getImage());
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
        values.put("mapb", nhanVien.getMaPhong());
        values.put("hesoluong", nhanVien.getBacLuong());
        values.put("imagenv", nhanVien.getImage());
        db.update("NhanVien", values, "manv ='" + nhanVien.getMaNV() + "'", null);
        db.close();
    }


    public void Xoa(NhanVien nhanVien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("NhanVien", "manv ='" + nhanVien.getMaNV() + "'", null);
        db.close();
    }

    public ArrayList<NhanVien> LayDSNhanVien() {
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
                nhanVien.setMaPhong(cursor.getString(4));
                nhanVien.setBacLuong(cursor.getString(5));
                nhanVien.setImage(cursor.getBlob(6));
                data.add(nhanVien);
            }
            while (cursor.moveToNext());
            db.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return data;
    }

    public ArrayList<NhanVien> LayNhanVien(String manv) {
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
                nhanVien.setMaPhong(cursor.getString(4));
                nhanVien.setBacLuong(cursor.getString(5));
                nhanVien.setImage(cursor.getBlob(6));
                data.add(nhanVien);
            }
            while (cursor.moveToNext());
            db.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return data;
    }

    //Kiểm tra mã nhân viên là duy nhất
    public boolean checkMaNhanVien(String manv) {
        boolean check = false;
        String sql = "SELECT count(*) FROM NhanVien WHERE manv LIKE \""+manv+"\" ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int count  = cursor.getInt(0);
        if(count > 0) {
            check = true;
        }
        return check;
    }

    public ArrayList<ThongKe> layDSThongKe() {
        ArrayList<ThongKe> data = new ArrayList<>();
        String sql = "select NhanVien.manv, NhanVien.tennv, PhongBan.tenpb, NhanVien.hesoluong, ChamCong.ngaychamcong, ChamCong.songaycong, TamUng.sotienung " +
                "from NhanVien INNER JOIN  PhongBan on PhongBan.mapb = NhanVien.mapb " +
                "INNER JOIN  ChamCong on NhanVien.manv = ChamCong.manv  " +
                "INNER JOIN TamUng on NhanVien.manv = TamUng.manv WHERE ChamCong.ngaychamcong = SUBSTR(TamUng.ngaytamung, 4, 10) ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                ThongKe thongKe = new ThongKe();
                thongKe.setMaNV(cursor.getString(0));
                thongKe.setTenNV(cursor.getString(1));
                thongKe.setTenPhong(cursor.getString(2));
                thongKe.setHeSoLuong(cursor.getString(3));
                thongKe.setNgayChamCong(cursor.getString(4));
                thongKe.setSoNgayCong(cursor.getString(5));
                thongKe.setTienTamUng(cursor.getString(6));
                data.add(thongKe);
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public ArrayList<ThongKe> layThongKe(String ngayChamCong) {
        String sql = "select NhanVien.manv, NhanVien.tennv, PhongBan.tenpb, NhanVien.hesoluong, ChamCong.ngaychamcong, ChamCong.songaycong, TamUng.sotienung " +
                "from NhanVien INNER JOIN  PhongBan on PhongBan.mapb = NhanVien.mapb " +
                "INNER JOIN  ChamCong on NhanVien.manv = ChamCong.manv  " +
                "INNER JOIN TamUng on NhanVien.manv = TamUng.manv WHERE ChamCong.ngaychamcong ='" + ngayChamCong + "' AND ChamCong.ngaychamcong = SUBSTR(TamUng.ngaytamung, 4, 10) ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<ThongKe> data = new ArrayList<>();

        try {
            cursor.moveToFirst();
            do {
                ThongKe thongKe = new ThongKe();
                thongKe.setMaNV(cursor.getString(0));
                thongKe.setTenNV(cursor.getString(1));
                thongKe.setTenPhong(cursor.getString(2));
                thongKe.setHeSoLuong(cursor.getString(3));
                thongKe.setNgayChamCong(cursor.getString(4));
                thongKe.setSoNgayCong(cursor.getString(5));
                thongKe.setTienTamUng(cursor.getString(6));
                data.add(thongKe);
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

    //lấy thống kê cho biểu đồ
    public ArrayList<ThongKe> layThongKeBieuDo(String manv) {
        String sql = "select NhanVien.manv, NhanVien.tennv, PhongBan.tenpb, NhanVien.hesoluong, ChamCong.ngaychamcong, ChamCong.songaycong, TamUng.sotienung from NhanVien INNER JOIN  PhongBan on PhongBan.mapb = NhanVien.mapb \n" +
                "  INNER JOIN  ChamCong on NhanVien.manv = ChamCong.manv INNER JOIN TamUng on NhanVien.manv = TamUng.manv WHERE Nhanvien.manv ='" + manv + "' AND ChamCong.ngaychamcong = SUBSTR(TamUng.ngaytamung, 4, 10)";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<ThongKe> data = new ArrayList<>();

        try {
            cursor.moveToFirst();
            do {
                ThongKe thongKe = new ThongKe();
                thongKe.setMaNV(cursor.getString(0));
                thongKe.setTenNV(cursor.getString(1));
                thongKe.setTenPhong(cursor.getString(2));
                thongKe.setHeSoLuong(cursor.getString(3));
                thongKe.setNgayChamCong(cursor.getString(4));
                thongKe.setSoNgayCong(cursor.getString(5));
                thongKe.setTienTamUng(cursor.getString(6));
                data.add(thongKe);
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }


    public ArrayList<ThongKe> locDSThongKe(String key) {
        ArrayList<ThongKe> data = new ArrayList<>();
        String sql = "select NhanVien.manv, NhanVien.tennv, PhongBan.tenpb, NhanVien.hesoluong, ChamCong.ngaychamcong, ChamCong.songaycong, TamUng.sotienung from NhanVien " +
                "INNER JOIN  PhongBan on PhongBan.mapb = NhanVien.mapb " +
                "INNER JOIN  ChamCong on NhanVien.manv = ChamCong.manv  " +
                "INNER JOIN TamUng on NhanVien.manv = TamUng.manv WHERE ChamCong.ngaychamcong LIKE \"%" + key + "%\" AND ChamCong.ngaychamcong = SUBSTR(TamUng.ngaytamung, 4, 10) ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                ThongKe thongKe = new ThongKe();
                thongKe.setMaNV(cursor.getString(0));
                thongKe.setTenNV(cursor.getString(1));
                thongKe.setTenPhong(cursor.getString(2));
                thongKe.setHeSoLuong(cursor.getString(3));
                thongKe.setNgayChamCong(cursor.getString(4));
                thongKe.setSoNgayCong(cursor.getString(5));
                thongKe.setTienTamUng(cursor.getString(6));
                data.add(thongKe);
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }


    public String layMaPhong(String tenPhong) {
        String maPhong = "";
        String sql = "SELECT mapb FROM PhongBan WHERE tenpb LIKE \"%" + tenPhong + "%\" ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                PhongBan phongBan = new PhongBan();
                phongBan.setMaPhong(cursor.getString(0));
                maPhong = phongBan.getMaPhong();
            }
            while (cursor.moveToNext());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return maPhong;
    }

    //Kiểm tra ráng buộc giữa Nhân viên và Tạm ứng khi xóa nhân viên
    public boolean checkXoaNhanVienTamUng(String maNV) {
        boolean check = false;
        String sql = "SELECT count(*) FROM TamUng WHERE manv LIKE \"%" + maNV + "%\" ";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if (count > 0) {
            check = true;
        }
        return check;
    }

    //Kiểm tra ráng buộc giữa Nhân viên và Chấm công khi xóa nhân viên
    public boolean checkXoaNhanVienChamCong(String maNV) {
        boolean check = false;
        String sql = "SELECT count(*) FROM ChamCong WHERE manv LIKE \"%" + maNV + "%\" ";
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
