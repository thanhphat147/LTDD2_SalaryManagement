package com.example.kiemtra;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class DanhSach extends AppCompatActivity {
    ListView lvDanhsach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        DBNhanVien dbNhanVien = new DBNhanVien(this);
        lvDanhsach = findViewById(R.id.lvDanhSach);
        CustomAdapter adapter = new CustomAdapter(this, R.layout.layout_nhanvien, dbNhanVien.read());
        lvDanhsach.setAdapter(adapter);
        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
