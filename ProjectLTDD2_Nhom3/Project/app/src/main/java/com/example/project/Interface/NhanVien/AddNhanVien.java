package com.example.project.Interface.NhanVien;

import androidx.appcompat.app.ActionBar;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.project.Database.DBNhanVien;
import com.example.project.Database.DBPhongBan;
import com.example.project.Model.NhanVien;
import com.example.project.R;
import java.util.List;

public class AddNhanVien extends AppCompatActivity {

    EditText edtMaNV, edtTenNV, edtNgaySinh, edtLuong;
    RadioButton radNam, radNu;
    Spinner spPhongBan;
    Button btnAdd;
    ImageButton btnDatePicker;
    DBPhongBan dbPhongBan;
    List<String> dsPhong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nhan_vien);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        dbPhongBan = new DBPhongBan(this);
        dsPhong = dbPhongBan.LayDSPhong();
        ArrayAdapter<String> dataPhong = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, dsPhong);
        spPhongBan.setAdapter(dataPhong);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemDL();
                Toast.makeText(AddNhanVien.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddNhanVien.this, MainActivityNhanVien.class);
                startActivity(intent);
            }
        });

    }

    private void setControl() {
        edtMaNV = findViewById(R.id.txtMaNV);
        edtTenNV = findViewById(R.id.txtTenNV);
        edtNgaySinh = findViewById(R.id.txtNgaySinh);
        radNam = findViewById(R.id.radNam);
        radNu = findViewById(R.id.radNu);
        spPhongBan = findViewById(R.id.spTenPB);
        edtLuong = findViewById(R.id.edtLuong);
        btnDatePicker = findViewById(R.id.btnDatePicker);
        btnAdd = findViewById(R.id.btnThem);
    }

    private void ThemDL() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV(edtMaNV.getText().toString());
        nhanVien.setTenNV(edtTenNV.getText().toString());
        nhanVien.setNgaySinh(edtNgaySinh.getText().toString());
        if (radNu.isChecked() == true) {
            nhanVien.setGioiTinh("Nữ");
            radNam.setChecked(false);
        }
        if (radNam.isChecked() == true) {
            nhanVien.setGioiTinh("Nam");
        }
        nhanVien.setTenPhong(spPhongBan.getSelectedItem().toString());
        nhanVien.setBacLuong(edtLuong.getText().toString());
        DBNhanVien dbNhanVien = new DBNhanVien(this);
        dbNhanVien.Them(nhanVien);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}