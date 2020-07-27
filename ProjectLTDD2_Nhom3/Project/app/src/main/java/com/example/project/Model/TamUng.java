package com.example.project.Model;

public class TamUng {
    String maNV, ngay;
    int soPhieu, soTien;

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getSoPhieu() {
        return soPhieu;
    }

    public void setSoPhieu(int soPhieu) {
        this.soPhieu = soPhieu;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    @Override
    public String toString() {
        return "TamUng{" +
                "maNV='" + maNV + '\'' +
                ", ngay='" + ngay + '\'' +
                ", soPhieu=" + soPhieu +
                ", soTien=" + soTien +
                '}';
    }
}
