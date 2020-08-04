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
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Database.DBNhanVien;
import com.example.project.Database.DBPhongBan;
import com.example.project.Model.NhanVien;
import com.example.project.R;

import java.util.ArrayList;

public class UpdateNhanVien extends AppCompatActivity {
    EditText edtTenNV, edtNgaySinh, edtLuong;
    TextView tvMa;
    Button btnUpdate;
    RadioButton radNam, radNu;
    Spinner spPhongBan;
    ImageButton btnDatePicker;
    ArrayList<NhanVien> dataNV = new ArrayList<>();
    DBPhongBan dbPhongBan;
    ArrayList<String> dsPhong;
    ArrayAdapter adapterPB;
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
        String gioiTinh, phongBan;

        dbPhongBan = new DBPhongBan(getApplicationContext());
        dsPhong= dbPhongBan.LayDSPhong();
        adapterPB = new ArrayAdapter<>(UpdateNhanVien.this, R.layout.support_simple_spinner_dropdown_item, dsPhong);
        spPhongBan.setAdapter(adapterPB);
        String masv = getIntent().getExtras().getString("manv");
        DBNhanVien dbNhanVien  =new DBNhanVien(this);
        dataNV = dbNhanVien.LayNhanVien(masv);
        tvMa.setText(dataNV.get(0).getMaNV());
        edtTenNV.setText(dataNV.get(0).getTenNV());
        edtNgaySinh.setText(dataNV.get(0).getNgaySinh());
        gioiTinh = dataNV.get(0).getGioiTinh();
        if (gioiTinh.equals("Nam")) {
            radNam.setChecked(true);
        }
        if (gioiTinh.equals("Nữ")){
            radNu.setChecked(true);
        }

        phongBan = dataNV.get(0).getTenPhong();
        if(phongBan != null) {
            spPhongBan.setSelection(getIndex(spPhongBan, phongBan));
        }

        edtLuong.setText(dataNV.get(0).getBacLuong());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDL();
                Toast.makeText(UpdateNhanVien.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateNhanVien.this, MainActivityNhanVien.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        tvMa = findViewById(R.id.tvMa);
        edtTenNV = findViewById(R.id.txtTenNV);
        edtNgaySinh = findViewById(R.id.txtNgaySinh);
        radNam = findViewById(R.id.radNam);
        radNu = findViewById(R.id.radNu);
        spPhongBan = findViewById(R.id.spTenPB);
        edtLuong = findViewById(R.id.txtLuong);
        btnDatePicker = findViewById(R.id.btnDatePicker);
        btnUpdate = findViewById(R.id.btnUpdate);
    }

    private void UpdateDL() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV(tvMa.getText().toString());
        nhanVien.setTenNV(edtTenNV.getText().toString());
        nhanVien.setNgaySinh(edtNgaySinh.getText().toString());
        if(radNam.isChecked()) {
            nhanVien.setGioiTinh(radNam.getText().toString());
        }
        else{
            nhanVien.setGioiTinh(radNu.getText().toString());
        }
        nhanVien.setTenPhong(spPhongBan.getSelectedItem().toString());
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

    private int getIndex(Spinner spinner, String key){
        for (int i = 0; i < spinner.getCount(); i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(key)){
                return i;
            }
        }

        return 0;
    }
}