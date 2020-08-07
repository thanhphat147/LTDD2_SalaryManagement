package com.example.project.Interface;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.project.Interface.ChamCong.MainActivityChamCong;
import com.example.project.Interface.NhanVien.MainActivityNhanVien;
import com.example.project.Interface.PhongBan.MainActivityPhongBan;
import com.example.project.Interface.TamUng.MainActivityTamUng;
import com.example.project.Interface.ThongKe.MainActivityThongKe;
import com.example.project.R;

import java.util.Locale;

public class MainActivityChucNang extends AppCompatActivity {

    Button btnDanhSachNV, btnDanhSachPB, btnDanhSachCC, btnDanhSachTU, btnAboutUs, btnThongKe;
    Locale myLocale;
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
        btnDanhSachCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityChucNang.this, MainActivityChamCong.class);
                startActivity(intent);
            }
        });
        btnDanhSachTU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityChucNang.this, MainActivityTamUng.class);
                startActivity(intent);
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityChucNang.this, MainActivityThongKe.class);
                startActivity(intent);
            }
        });
        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityChucNang.this, ActivityLienHe.class);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.close:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityChucNang.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn thoát không ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog1 = builder.create();
                alertDialog1.show();
                break;

            case R.id.lang_en:
                myLocale = new Locale("en", "US");
                onChangeLanguage(myLocale);
                break;

            case R.id.lang_vi:
                myLocale = new Locale("vi", "VN");
                onChangeLanguage(myLocale);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onChangeLanguage(Locale locale) {
        DisplayMetrics displayMetrics = getBaseContext().getResources().getDisplayMetrics();

        //cấu hình phiên bản có thể áp dụng
        Configuration config = new Configuration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);
        } else {
            config.locale = locale;
        }

        //cài đặt ngôn ngữ
        getBaseContext().getResources().updateConfiguration(config, displayMetrics);

        //refresh Activity
        Intent refresh = new Intent(MainActivityChucNang.this, MainActivityChucNang.class);
        startActivity(refresh);
        finish();
    }
}