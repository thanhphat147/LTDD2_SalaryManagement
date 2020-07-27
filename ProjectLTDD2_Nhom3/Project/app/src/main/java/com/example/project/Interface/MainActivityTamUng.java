package com.example.project.Interface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.project.Adapter.CustomAdapterCC;
import com.example.project.Adapter.CustomAdapterTU;
import com.example.project.Model.ChamCong;
import com.example.project.Model.TamUng;
import com.example.project.R;

import java.util.ArrayList;

public class MainActivityTamUng extends AppCompatActivity {

    ListView lvTamUng;
    ArrayList<TamUng> data_TU;
    CustomAdapterTU adapter_TU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tam_ung);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
//        setControl();
//        setEvent();
    }

//    private void setEvent() {
////        KhoiTao();
//        adapter_TU = new CustomAdapterTU(MainActivityTamUng.this, R.layout.custom_view_tu, data_TU);
//        lvTamUng.setAdapter(adapter_TU);
//    }

//    private void KhoiTao(){
//        data_TU = new ArrayList<TamUng>();
//        data_TU.add(GetTamUng());
//    }
//
//    private TamUng GetTamUng() {
//        TamUng tamUng = new TamUng();
//        tamUng.setMaNV("NV001");
//        tamUng.setSoPhieu(1);
//        tamUng.setNgay("12/03/2020");
//        tamUng.setSoTien(500000);
//        return tamUng;
//    }

//    private void setControl() {
//        lvTamUng = findViewById(R.id.lvTamUng);
//    }

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
                Intent intent = new Intent(MainActivityTamUng.this, MainActivityChucNang.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}