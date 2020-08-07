package com.example.project.Interface.ThongKe;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project.Database.DBChamCong;
import com.example.project.Database.DBNhanVien;
import com.example.project.Model.ThongKe;
import com.example.project.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BieuDoThongKe extends AppCompatActivity {
    BarChart chart;
    TextView tvTenNV;
    ArrayList<ThongKe> thongKe = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bieudo_thongke);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();

    }

    private void setEvent() {
        String manv = getIntent().getExtras().getString("manv");
        final DBNhanVien dbNhanVien = new DBNhanVien(getApplicationContext());
        thongKe = dbNhanVien.layThongKeBieuDo(manv);
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < thongKe.size(); i++) {
            tvTenNV.setText(thongKe.get(i).getTenNV());
            int luong = 0;
            int soNgayCong = Integer.parseInt(thongKe.get(i).getSoNgayCong());
            int heSoLuong = Integer.parseInt(thongKe.get(i).getHeSoLuong());
            int tamUng = Integer.parseInt(thongKe.get(i).getTienTamUng());
            luong = (heSoLuong * soNgayCong);
            thongKe.get(i).setLuongCoBan(luong + "");
            int thucLanh = 0;
            thucLanh = luong - tamUng;
            thongKe.get(i).setLuongThucLanh(thucLanh + "");
            entries.add(new BarEntry(Integer.parseInt(thongKe.get(i).getLuongThucLanh()), i));
        }
        BarDataSet dataSet = new BarDataSet(entries, "Dữ liệu Lương");
        DBChamCong dbChamCong = new DBChamCong(getApplicationContext());
        ArrayList<String> labels = new ArrayList<String>();
        labels = dbChamCong.layDSNgayCham();

        BarData data = new BarData(labels, dataSet);

        chart.setData(data);

        data.setValueTextColor(Color.BLUE);
        dataSet.setBarShadowColor(Color.WHITE);
        dataSet.setValueTextSize(15);
        chart.setDrawBarShadow(true);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.animateY(3000, Easing.EasingOption.EaseInOutBounce);

        chart.invalidate();

    }

    private void setControl() {
        chart = findViewById(R.id.barchart);
        tvTenNV = findViewById(R.id.tvTenNV);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}