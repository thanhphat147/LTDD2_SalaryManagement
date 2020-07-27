package com.example.project.Interface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.project.Adapter.CustomAdapterTK;
import com.example.project.Adapter.CustomAdapterTU;
import com.example.project.Model.TamUng;
import com.example.project.Model.ThongKe;
import com.example.project.R;

import java.util.ArrayList;

public class MainActivityThongKe extends AppCompatActivity {

    ListView lvThongKe;
    ArrayList<ThongKe> data_TK = new ArrayList<>();
    CustomAdapterTK adapter_TK;

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
        KhoiTao();
        adapter_TK = new CustomAdapterTK(MainActivityThongKe.this, R.layout.custom_view_tk, data_TK);
        lvThongKe.setAdapter(adapter_TK);
    }

    private void KhoiTao(){
        data_TK = new ArrayList<ThongKe>();
        data_TK.add(GetThongKe1());
        data_TK.add(GetThongKe2());
    }

    private ThongKe GetThongKe1() {
        ThongKe thongKe = new ThongKe();
        thongKe.setMaNV("NV001");
        thongKe.setHoTen("Nguyen Van A");
        thongKe.setGioiTinh("Nam");
        thongKe.setSoNgayCong(27);
        thongKe.setTienTamUng(200000);
        thongKe.setTienLuong(7800000);
        return thongKe;
    }
    private ThongKe GetThongKe2() {
        ThongKe thongKe = new ThongKe();
        thongKe.setMaNV("NV002");
        thongKe.setHoTen("Tran Thi B");
        thongKe.setGioiTinh("Nu");
        thongKe.setSoNgayCong(29);
        thongKe.setTienTamUng(0);
        thongKe.setTienLuong(8500000);
        return thongKe;
    }

    private void setControl() {
        lvThongKe = findViewById(R.id.lvThongKe);
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