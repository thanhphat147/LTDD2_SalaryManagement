package com.example.project.Interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project.R;

public class MainActivityChucNang extends AppCompatActivity {

    Button btnDanhSachNV, btnDanhSachPB, btnDanhSachCC, btnDanhSachTU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chuc_nang);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnDanhSachNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityChucNang.this, MainActivity.class);
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
        btnDanhSachNV = findViewById(R.id.btnDanhSachNV);
        btnDanhSachPB = findViewById(R.id.btnDanhSachPB);
        btnDanhSachCC = findViewById(R.id.btnDanhSachCC);
        btnDanhSachTU = findViewById(R.id.btnDanhSachTU);
    }
}