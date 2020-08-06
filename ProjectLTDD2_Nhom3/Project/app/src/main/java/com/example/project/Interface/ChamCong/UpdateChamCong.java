package com.example.project.Interface.ChamCong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Database.DBChamCong;
import com.example.project.Database.DBNhanVien;
import com.example.project.Library.CheckError;
import com.example.project.Model.ChamCong;
import com.example.project.Model.NhanVien;
import com.example.project.R;
import java.util.ArrayList;
import java.util.Calendar;

public class UpdateChamCong extends AppCompatActivity {
    TextView tvMaNhanVien, tvTenNhanVien, tvNgayChamCong;
    EditText txtSoNgayCong;
    Button btnLuu, btnThoat;
    Calendar calendar;
    int year, month;
    ArrayList<NhanVien> dataNV = new ArrayList<>();
    ArrayList<ChamCong> chamCong = new ArrayList<>();
    CheckError checkError = new CheckError(UpdateChamCong.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_cham_cong);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        showDate(year, month + 1);
        String manv = getIntent().getExtras().getString("manv");
        DBChamCong dbChamCong = new DBChamCong(getApplicationContext());
        chamCong = dbChamCong.layChamCong(manv);
        DBNhanVien dbNhanVien = new DBNhanVien(getApplicationContext());
        dataNV = dbNhanVien.LayNhanVien(chamCong.get(0).getMaNV());
        tvMaNhanVien.setText(dataNV.get(0).getMaNV());
        tvTenNhanVien.setText(dataNV.get(0).getTenNV());
        txtSoNgayCong.setText(chamCong.get(0).getSoNgayCong());

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtSoNgayCong.getText().toString().isEmpty()) {
                    checkError.checkEmpty(txtSoNgayCong, "Vui lòng nhập số ngày công");
                } else {
                    suaChamCong();
                    Toast.makeText(getApplicationContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateChamCong.this, MainActivityChamCong.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void suaChamCong() {
        ChamCong chamCong = new ChamCong();
        chamCong.setMaNV(tvMaNhanVien.getText().toString());
        chamCong.setThangChamCong(tvNgayChamCong.getText().toString());
        chamCong.setSoNgayCong(txtSoNgayCong.getText().toString());
        DBChamCong dbChamCong = new DBChamCong(getApplicationContext());
        dbChamCong.suaChamCong(chamCong);
    }

    private void showDate(int year, int month) {
        tvNgayChamCong.setText(new StringBuilder().append(month > 9 ?
                month : "0" + month).append("/").append(year));
    }

    private void setControl() {
        tvMaNhanVien = findViewById(R.id.tvMaNV);
        tvTenNhanVien = findViewById(R.id.tvHoTen);
        tvNgayChamCong = findViewById(R.id.tvNgayChamCong);
        txtSoNgayCong = findViewById(R.id.txtSoNgayCong);
        btnLuu = findViewById(R.id.btnSuaCC);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
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
        }
        return true;
    }
}