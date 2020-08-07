package com.example.project.Interface.ChamCong;

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
import com.example.project.Interface.MainActivityChucNang;
import com.example.project.Interface.NhanVien.MainActivityNhanVien;
import com.example.project.Library.CheckError;
import com.example.project.Model.ChamCong;
import com.example.project.Model.NhanVien;
import com.example.project.R;

import java.util.ArrayList;
import java.util.Calendar;

public class AddChamCong extends AppCompatActivity {
    TextView tvMaNhanVien, tvTenNhanVien;
    EditText txtSoNgayCong, txtNgayChamCong;
    Button btnAdd;
    Calendar calendar;
    int year, month;
    ArrayList<NhanVien> dataNV = new ArrayList<>();
    CheckError checkError = new CheckError(AddChamCong.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cham_cong);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        showDate(year, month + 1);
        String manv = getIntent().getExtras().getString("ma");
        DBNhanVien dbNhanVien = new DBNhanVien(this);
        dataNV = dbNhanVien.LayNhanVien(manv);
        tvMaNhanVien.setText(dataNV.get(0).getMaNV());
        tvTenNhanVien.setText(dataNV.get(0).getTenNV());
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBChamCong dbChamCong = new DBChamCong(getApplicationContext());
                boolean check = dbChamCong.checkChamCong(txtNgayChamCong.getText().toString(), tvMaNhanVien.getText().toString());
                if (txtNgayChamCong.getText().toString().isEmpty() || txtSoNgayCong.getText().toString().isEmpty()) {
                    checkError.checkEmpty(txtNgayChamCong, "Ngày chấm không để trống");
                    checkError.checkEmpty(txtSoNgayCong, "Vui lòng nhập số ngày công");
                } else if (check == true) {
                    Toast.makeText(getApplicationContext(), "Nhân viên đã chấm công rồi", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddChamCong.this, MainActivityNhanVien.class);
                    startActivity(intent);
                    finish();
                } else {
                    themChamCong();
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddChamCong.this, MainActivityChamCong.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void setControl() {
        tvMaNhanVien = findViewById(R.id.tvMaNV);
        tvTenNhanVien = findViewById(R.id.tvHoTen);
        txtNgayChamCong = findViewById(R.id.txtNgayChamCong);
        txtSoNgayCong = findViewById(R.id.txtSoNgayCong);
        btnAdd = findViewById(R.id.btnthemCC);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
    }

    private void themChamCong() {
        ChamCong chamCong = new ChamCong();
        chamCong.setMaNV(tvMaNhanVien.getText().toString());
        chamCong.setThangChamCong(txtNgayChamCong.getText().toString());
        chamCong.setSoNgayCong(txtSoNgayCong.getText().toString());
        DBChamCong dbChamCong = new DBChamCong(getApplicationContext());
        dbChamCong.themChamCong(chamCong);
    }


    private void showDate(int year, int month) {
        txtNgayChamCong.setText(new StringBuilder().append(month > 9 ?
                month : "0" + month).append("/").append(year));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnHome:
                Intent intent = new Intent(AddChamCong.this, MainActivityChucNang.class);
                startActivity(intent);
                break;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return true;
    }
}