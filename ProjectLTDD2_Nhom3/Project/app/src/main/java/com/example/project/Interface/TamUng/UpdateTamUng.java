package com.example.project.Interface.TamUng;

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

import com.example.project.Database.DBNhanVien;
import com.example.project.Database.DBTamUng;
import com.example.project.Interface.MainActivityChucNang;
import com.example.project.Library.CheckError;
import com.example.project.Model.NhanVien;
import com.example.project.Model.TamUng;
import com.example.project.R;

import java.util.ArrayList;
import java.util.Calendar;

public class UpdateTamUng extends AppCompatActivity {
    EditText txtSoTien, txtNgayUng;
    TextView tvMaNhanVien, tvTenNhanVien, tvSophieu;
    Calendar calendar;
    int year, month, day;
    Button btnTamUng;
    CheckError checkError = new CheckError(UpdateTamUng.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tam_ung);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {

        showDate(year, month + 1, day);
        String sophieu = getIntent().getExtras().getString("sophieu");
        DBTamUng dbTamUng = new DBTamUng(getApplicationContext());
        ArrayList<TamUng> tamUngs = dbTamUng.layPhieu(sophieu);
        tvSophieu.setText(tamUngs.get(0).getSoPhieu());
        txtSoTien.setText(tamUngs.get(0).getSoTienUng());
        String manv = tamUngs.get(0).getMaNV();
        DBNhanVien dbNhanVien = new DBNhanVien(getApplicationContext());
        ArrayList<NhanVien> nhanViens = dbNhanVien.LayNhanVien(manv);
        tvMaNhanVien.setText(nhanViens.get(0).getMaNV());
        tvTenNhanVien.setText(nhanViens.get(0).getTenNV());


        btnTamUng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtSoTien.getText().toString().isEmpty()) {
                    checkError.checkEmpty(txtSoTien, "Vui lòng nhập số tiền");
                } else {
                    suaTamUng();
                    Toast.makeText(getApplicationContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateTamUng.this, MainActivityTamUng.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private void suaTamUng() {
        TamUng tamUng = new TamUng();
        tamUng.setSoPhieu(tvSophieu.getText().toString());
        tamUng.setNgayTamUng(txtNgayUng.getText().toString());
        tamUng.setMaNV(tvMaNhanVien.getText().toString());
        tamUng.setSoTienUng(txtSoTien.getText().toString());
        DBTamUng dbTamUng = new DBTamUng(getApplicationContext());
        dbTamUng.suaTamUng(tamUng);
    }

    private void showDate(int year, int month, int day) {
        txtNgayUng.setText(new StringBuilder().append(day > 9 ? day : "0" + day).append("/").append(month > 9 ?
                month : "0" + month).append("/").append(year));
    }


    private void setControl() {
        tvSophieu = findViewById(R.id.tvSoPhieu);
        txtSoTien = findViewById(R.id.txtSoTienUng);

        tvMaNhanVien = findViewById(R.id.tvMaNV);
        tvTenNhanVien = findViewById(R.id.tvHoTen);
        txtNgayUng = findViewById(R.id.txtNgayUng);
        btnTamUng = findViewById(R.id.btnSuaTU);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnHome:
                Intent intent = new Intent(UpdateTamUng.this, MainActivityChucNang.class);
                startActivity(intent);
                break;

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return true;
    }
}