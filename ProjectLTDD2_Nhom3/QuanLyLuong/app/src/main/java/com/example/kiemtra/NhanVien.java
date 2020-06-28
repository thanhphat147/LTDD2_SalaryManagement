package com.example.kiemtra;

public class NhanVien {
    String MaNV, tenNV, ngaySinh, MaPB, tienLuong;

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
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

    public String getMaPB() {
        return MaPB;
    }

    public void setMaPB(String maPB) {
        MaPB = maPB;
    }

    public String getTienLuong() {
        return tienLuong;
    }

    public void setTienLuong(String tienLuong) {
        this.tienLuong = tienLuong;
    }

    @Override
    public String toString() {
        return "PhieuThu{" +
                "MaNV='" + MaNV + '\'' +
                ", tenNV='" + tenNV + '\'' +
                ", ngaySinh='" + ngaySinh + '\'' +
                ", MaPB='" + MaPB + '\'' +
                ", tienLuong='" + tienLuong + '\'' +
                '}';
    }
}

