package com.example.project.Model;

public class NhanVien {
    String maNV, tenNV, ngaySinh, gioiTinh, tenPhong, imageNV, bacLuong;


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

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getImageNV() {
        return imageNV;
    }

    public void setImageNV(String imageNV) {
        this.imageNV = imageNV;
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
                ", gioiTinh='" + gioiTinh + '\'' +
                ", tenPhong='" + tenPhong + '\'' +
                ", imageNV='" + imageNV + '\'' +
                ", bacLuong=" + bacLuong +
                '}';
    }
}
