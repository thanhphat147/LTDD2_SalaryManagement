package com.example.project.Interface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.project.Adapter.CustomAdapterCC;
import com.example.project.Model.ChamCong;
import com.example.project.R;

import java.util.ArrayList;

public class MainActivityChamCong extends AppCompatActivity {

    ListView lvChamCong;
    ArrayList<ChamCong> data_CC = new ArrayList<>();
    CustomAdapterCC adapter_CC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cham_cong);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
        adapter_CC = new CustomAdapterCC(MainActivityChamCong.this, R.layout.custom_view_cc, data_CC);
        lvChamCong.setAdapter(adapter_CC);
    }

    private void KhoiTao(){
        data_CC = new ArrayList<ChamCong>();
        data_CC.add(GetChamCong());
    }

    private ChamCong GetChamCong() {
        ChamCong chamCong = new ChamCong();
        chamCong.setMaNV("NV001");
        chamCong.setNgayGhiSo("20/02/2019");
        chamCong.setSoNgayCong(29);
        return chamCong;
    }

    private void setControl() {
        lvChamCong = findViewById(R.id.lvChamCong);
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
                Intent intent = new Intent(MainActivityChamCong.this, MainActivityChucNang.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}