package com.example.project.Interface.ThongKe;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.Database.DBNhanVien;
import com.example.project.Model.NhanVien;
import com.example.project.Model.ThongKe;
import com.example.project.R;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ChiTietThongKe extends AppCompatActivity {
    TextView tvMaNV, tvTenNV, tvNgayChamCong, tvTenPhongBan, tvHeSoLuong, tvSoNgayCong, tvTamUng, tvLuongCoBan, tvThucLanh;
    ArrayList<ThongKe> thongKe;
    Locale localeVN = new Locale("vi", "VN");
    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitiet_thongke);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        String ngaychamcong = getIntent().getExtras().getString("ngaycham");
        DBNhanVien dbNhanVien = new DBNhanVien(getApplicationContext());
        thongKe = dbNhanVien.layThongKe(ngaychamcong);
        tvMaNV.setText(thongKe.get(0).getMaNV());
        tvTenNV.setText(thongKe.get(0).getTenNV());
        tvNgayChamCong.setText(thongKe.get(0).getNgayChamCong());
        tvTenPhongBan.setText(thongKe.get(0).getTenPhong());
        tvHeSoLuong.setText(currencyVN.format(Integer.parseInt(thongKe.get(0).getHeSoLuong())));
        tvSoNgayCong.setText(thongKe.get(0).getSoNgayCong());
        tvTamUng.setText(currencyVN.format(Integer.parseInt(thongKe.get(0).getTienTamUng())));
        int luong = 0;
        int ngayCong = Integer.parseInt(thongKe.get(0).getSoNgayCong());
        int heSoLuong = Integer.parseInt(thongKe.get(0).getHeSoLuong());
        int tamUng = Integer.parseInt(thongKe.get(0).getTienTamUng());
        luong = (heSoLuong * ngayCong);
        thongKe.get(0).setLuongCoBan(luong + "");
        tvLuongCoBan.setText(currencyVN.format(Integer.parseInt(thongKe.get(0).getLuongCoBan())));
        int thucLanh = 0;
        thucLanh = luong - tamUng;
        thongKe.get(0).setLuongThucLanh(currencyVN.format(thucLanh));
        tvThucLanh.setText(thongKe.get(0).getLuongThucLanh());
    }

    private void setControl() {
        tvMaNV = findViewById(R.id.tvMaNhanVien);
        tvTenNV = findViewById(R.id.tvTenNV);
        tvNgayChamCong = findViewById(R.id.tvNgayChamCong);
        tvTenPhongBan = findViewById(R.id.tvTenPhongBan);
        tvHeSoLuong = findViewById(R.id.tvHeSoLuong);
        tvSoNgayCong = findViewById(R.id.tvSoNgayCong);
        tvTamUng = findViewById(R.id.tvTamUng);
        tvLuongCoBan = findViewById(R.id.tvLuongCB);
        tvThucLanh = findViewById(R.id.tvThucLanh);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}