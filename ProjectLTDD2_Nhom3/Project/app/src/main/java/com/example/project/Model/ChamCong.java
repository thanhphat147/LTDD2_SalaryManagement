package com.example.project.Model;

public class ChamCong {
    String maNV, ngayGhiSo;
    int soNgayCong;

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getNgayGhiSo() {
        return ngayGhiSo;
    }

    public void setNgayGhiSo(String ngayGhiSo) {
        this.ngayGhiSo = ngayGhiSo;
    }

    public int getSoNgayCong() {
        return soNgayCong;
    }

    public void setSoNgayCong(int soNgayCong) {
        this.soNgayCong = soNgayCong;
    }

    @Override
    public String toString() {
        return "ChamCong{" +
                "maNV='" + maNV + '\'' +
                ", ngayGhiSo='" + ngayGhiSo + '\'' +
                ", soNgayCong=" + soNgayCong +
                '}';
    }
}
