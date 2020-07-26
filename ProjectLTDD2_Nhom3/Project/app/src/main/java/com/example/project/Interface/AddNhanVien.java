package com.example.project.Interface;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.Database.DBNhanVien;
import com.example.project.Model.NhanVien;
import com.example.project.R;

public class AddNhanVien extends AppCompatActivity {

    EditText edtMaNV, edtTenNV, edtNgaySinh, edtMaPB, edtLuong;
    Button btnAdd;

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
        edtMaPB = findViewById(R.id.txtMaPB);
        edtLuong = findViewById(R.id.txtLuong);
        btnAdd = findViewById(R.id.btnThem);
    }

    private void ThemDL() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV(edtMaNV.getText().toString());
        nhanVien.setTenNV(edtTenNV.getText().toString());
        nhanVien.setNgaySinh(edtNgaySinh.getText().toString());
        nhanVien.setMaPhong(edtMaPB.getText().toString());
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