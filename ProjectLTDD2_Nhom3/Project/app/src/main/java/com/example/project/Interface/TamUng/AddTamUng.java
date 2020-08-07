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

public class AddTamUng extends AppCompatActivity {
    EditText txtSophieu, txtSoTien, txtNgayUng;
    TextView tvMaNhanVien, tvTenNhanVien;
    Calendar calendar;
    int year, month, day;
    Button btnTamUng;
    CheckError checkError = new CheckError(AddTamUng.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tam_ung);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        showDate(year, month + 1, day);
        String manv = getIntent().getExtras().getString("ma");
        DBNhanVien dbNhanVien = new DBNhanVien(getApplicationContext());
        ArrayList<NhanVien> nhanViens = dbNhanVien.LayNhanVien(manv);
        tvMaNhanVien.setText(nhanViens.get(0).getMaNV());
        tvTenNhanVien.setText(nhanViens.get(0).getTenNV());

        btnTamUng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBTamUng dbTamUng = new DBTamUng(getApplicationContext());
                boolean check = dbTamUng.checkSoPhieu(txtSophieu.getText().toString());
                boolean check1 = dbTamUng.checkTamUng(txtNgayUng.getText().toString(), tvMaNhanVien.getText().toString());
                if (txtSophieu.getText().toString().isEmpty() || txtSoTien.getText().toString().isEmpty()) {
                    checkError.checkEmpty(txtSophieu, "Vui lòng nhập số phiếu");
                    checkError.checkEmpty(txtSoTien, "Vui lòng nhập số tiền");
                } else if (check == true) {
                    txtSophieu.setError("Số phiếu đã tồn tại");
                    txtSophieu.isFocused();
                } else if (check1 == true) {
                    Toast.makeText(getApplicationContext(), "Nhân viên đã tạm ứng trong tháng này rồi", Toast.LENGTH_SHORT).show();
                } else {
                    themTamUng();
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddTamUng.this, MainActivityTamUng.class);
                    startActivity(intent);
                    finish();
                }

            }
        });


    }

    private void themTamUng() {
        TamUng tamUng = new TamUng();
        tamUng.setSoPhieu(txtSophieu.getText().toString());
        tamUng.setMaNV(tvMaNhanVien.getText().toString());
        tamUng.setNgayTamUng(txtNgayUng.getText().toString());
        tamUng.setSoTienUng(txtSoTien.getText().toString());
        DBTamUng dbTamUng = new DBTamUng(getApplicationContext());
        dbTamUng.themTamUng(tamUng);

    }

    private void showDate(int year, int month, int day) {
        txtNgayUng.setText(new StringBuilder().append(day > 9 ? day : "0" + day).append("/").append(month > 9 ?
                month : "0" + month).append("/").append(year));
    }

    private void setControl() {
        txtSophieu = findViewById(R.id.txtSoPhieu);
        txtSoTien = findViewById(R.id.txtSoTienUng);
        txtNgayUng = findViewById(R.id.txtNgayUng);
        tvMaNhanVien = findViewById(R.id.tvMaNV);
        tvTenNhanVien = findViewById(R.id.tvHoTen);
        btnTamUng = findViewById(R.id.btnthemTU);
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
        switch (item.getItemId()) {
            case R.id.btnHome:
                Intent intent = new Intent(AddTamUng.this, MainActivityChucNang.class);
                startActivity(intent);
                break;

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return true;
    }
}