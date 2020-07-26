package com.example.project.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project.R;

public class MainActivityChucNang extends AppCompatActivity {

    Button btnDanhSachNV, btnDanhSachPB, btnDanhSachCC, btnDanhSachTU, btnAboutUs, btnThongKe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnDanhSachNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityChucNang.this, MainActivityNhanVien.class);
                startActivity(intent);
            }
        });
        btnDanhSachPB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityChucNang.this, MainActivityPhongBan.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        btnDanhSachNV = findViewById(R.id.btnDSNhanVien);
        btnDanhSachPB = findViewById(R.id.btnDepartment);
        btnDanhSachCC = findViewById(R.id.btnDSChamCong);
        btnDanhSachTU = findViewById(R.id.btnDSTamUng);
        btnThongKe = findViewById(R.id.btnThongKe);
        btnAboutUs = findViewById(R.id.btnAboutUs);
    }
}