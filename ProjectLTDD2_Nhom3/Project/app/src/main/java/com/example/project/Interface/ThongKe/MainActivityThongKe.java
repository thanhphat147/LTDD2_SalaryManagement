package com.example.project.Interface.ThongKe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.project.Adapter.CustomAdapterTK;
import com.example.project.Database.DBChamCong;
import com.example.project.Database.DBNhanVien;
import com.example.project.Interface.MainActivityChucNang;
import com.example.project.Model.ThongKe;
import com.example.project.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivityThongKe extends AppCompatActivity {

    ArrayList<ThongKe> thongKe = new ArrayList<>();
    ListView lvThongke;
    Spinner spNgayChamCong;
    ArrayList<String> sp_data;
    ArrayAdapter adapter_ngaycham;
    CustomAdapterTK adapterThongKe;
    Button btnLoc;
    TextView tvTongLuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thong_ke);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }


    private void setEvent() {
        final DBNhanVien dbNhanVien = new DBNhanVien(getApplicationContext());
        thongKe = dbNhanVien.layDSThongKe();
        int tongLuong = 0;
        for (int i = 0; i < thongKe.size(); i++) {
            int soNgayCong = Integer.parseInt(thongKe.get(i).getSoNgayCong());
            int heSoLuong = Integer.parseInt(thongKe.get(i).getHeSoLuong());
            int tamUng = Integer.parseInt(thongKe.get(i).getTienTamUng());
            int luong = soNgayCong * heSoLuong;
            float thucLanh = luong - tamUng;
            tongLuong += thucLanh;
        }
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        tvTongLuong.setText(currencyVN.format(tongLuong));
        adapterThongKe = new CustomAdapterTK(this, R.layout.custom_view_tk, thongKe);
        lvThongke.setAdapter(adapterThongKe);
        DBChamCong dbChamCong = new DBChamCong(getApplicationContext());
        sp_data = dbChamCong.layDSNgayCham();
        sp_data.add("");


        adapter_ngaycham = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sp_data);
        spNgayChamCong.setAdapter(adapter_ngaycham);
        spNgayChamCong.setSelection(getIndex(spNgayChamCong, ""));
        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thongKe = dbNhanVien.locDSThongKe(spNgayChamCong.getSelectedItem().toString());
                adapterThongKe = new CustomAdapterTK(MainActivityThongKe.this, R.layout.custom_view_tk, thongKe);
                lvThongke.setAdapter(adapterThongKe);
                int tongLuong = 0;
                for (int i = 0; i < thongKe.size(); i++) {
                    int soNgayCong = Integer.parseInt(thongKe.get(i).getSoNgayCong());
                    int heSoLuong = Integer.parseInt(thongKe.get(i).getHeSoLuong());
                    int tamUng = Integer.parseInt(thongKe.get(i).getTienTamUng());
                    int luong = soNgayCong * heSoLuong;
                    int thucLanh = luong - tamUng;
                    tongLuong += thucLanh;
                }
                Locale localeVN = new Locale("vi", "VN");
                NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
                tvTongLuong.setText(currencyVN.format(tongLuong));

            }
        });
    }

    //Hàm xử lý lấy vị trí tháng trong spinner
    private int getIndex(Spinner spinner, String myString) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                return i;
            }
        }

        return 0;
    }

    private void setControl() {
        lvThongke = findViewById(R.id.lvThongKe);
        spNgayChamCong = findViewById(R.id.spThangChamCong);
        btnLoc = findViewById(R.id.btnFilter);
        tvTongLuong = findViewById(R.id.tvTongLuong);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.btnHome:
                Intent intent = new Intent(MainActivityThongKe.this, MainActivityChucNang.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}