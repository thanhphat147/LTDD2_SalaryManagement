package com.example.project.Interface;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Database.DBNhanVien;
import com.example.project.Model.NhanVien;
import com.example.project.R;

import java.util.ArrayList;

public class UpdateNhanVien extends AppCompatActivity {
    EditText edtTenNV, edtNgaySinh, edtMaPB, edtLuong;
    TextView tvMa;
    Button btnUpdate;
    ArrayList<NhanVien> dataNV = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nhan_vien);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        String masv = getIntent().getExtras().getString("ma");
        DBNhanVien dbNhanVien  =new DBNhanVien(this);
        dataNV =dbNhanVien.LayDL(masv);
        tvMa.setText(dataNV.get(0).getMaNV());
        edtTenNV.setText(dataNV.get(0).getTenNV());
        edtNgaySinh.setText(dataNV.get(0).getNgaySinh());
        edtMaPB.setText(dataNV.get(0).getMaPhong());
        edtLuong.setText(dataNV.get(0).getBacLuong());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDL();
                Toast.makeText(UpdateNhanVien.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setControl() {
        tvMa = findViewById(R.id.txtMaNV);
        edtTenNV = findViewById(R.id.txtTenNV);
        edtNgaySinh = findViewById(R.id.txtNgaySinh);
        edtMaPB = findViewById(R.id.txtMaPB);
        edtLuong = findViewById(R.id.txtLuong);
        tvMa = findViewById(R.id.tvMa);
        btnUpdate = findViewById(R.id.btnUpdate);
    }

    private void UpdateDL() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV(tvMa.getText().toString());
        nhanVien.setTenNV(edtTenNV.getText().toString());
        nhanVien.setNgaySinh(edtNgaySinh.getText().toString());
        nhanVien.setMaPhong(edtMaPB.getText().toString());
        nhanVien.setBacLuong(edtLuong.getText().toString());
        DBNhanVien dbNhanVien = new DBNhanVien(this);
        dbNhanVien.Sua(nhanVien);
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