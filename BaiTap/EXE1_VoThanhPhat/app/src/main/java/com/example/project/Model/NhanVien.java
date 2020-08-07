package com.example.project.Model;

public class NhanVien {
    String maNV, tenNV, ngaySinh, maPhong, bacLuong;

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getBacLuong() {
        return bacLuong;
    }

    public void setBacLuong(String bacLuong) {
        this.bacLuong = bacLuong;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNV='" + maNV + '\'' +
                ", tenNV='" + tenNV + '\'' +
                ", ngaySinh='" + ngaySinh + '\'' +
                ", maPhong='" + maPhong + '\'' +
                ", bacLuong='" + bacLuong + '\'' +
                '}';
    }
}
