package com.example.project.Model;

public class ThongKe {
    String maNV, hoTen, gioiTinh;
    int soNgayCong, tienTamUng, tienLuong;

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getSoNgayCong() {
        return soNgayCong;
    }

    public void setSoNgayCong(int soNgayCong) {
        this.soNgayCong = soNgayCong;
    }

    public int getTienTamUng() {
        return tienTamUng;
    }

    public void setTienTamUng(int tienTamUng) {
        this.tienTamUng = tienTamUng;
    }

    public int getTienLuong() {
        return tienLuong;
    }

    public void setTienLuong(int tienLuong) {
        this.tienLuong = tienLuong;
    }

    @Override
    public String toString() {
        return "ThongKe{" +
                "maNV='" + maNV + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", soNgayCong=" + soNgayCong +
                ", tienTamUng=" + tienTamUng +
                ", tienLuong=" + tienLuong +
                '}';
    }
}
