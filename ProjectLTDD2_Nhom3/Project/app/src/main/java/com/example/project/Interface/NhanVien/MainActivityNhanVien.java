package com.example.project.Interface.NhanVien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.project.Adapter.CustomAdapterNV;
import com.example.project.Database.DBNhanVien;
import com.example.project.Interface.MainActivityChucNang;
import com.example.project.Interface.TamUng.MainActivityTamUng;
import com.example.project.Library.LoadingDialog;
import com.example.project.Model.NhanVien;
import com.example.project.R;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivityNhanVien extends AppCompatActivity {
    Button btnAdd;
    ListView lvDanhSachNV;
    CustomAdapterNV adapterNV;
    ArrayList<NhanVien> dataNV = new ArrayList<>();
    Locale myLocale;
    LoadingDialog loadingDialog = new LoadingDialog(MainActivityNhanVien.this);

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        loadingDialog.startLoadingDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.dismissDialog();
                HienThiDL();
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivityNhanVien.this, AddNhanVien.class);
                        startActivity(intent);
                    }
                });
            }

        }, 3000);


    }

    private void setControl() {
        btnAdd = findViewById(R.id.btnThem);
        lvDanhSachNV = findViewById(R.id.lvNhanVien);
    }

    private void HienThiDL() {
        DBNhanVien dbNhanVien = new DBNhanVien(this);
        dataNV = dbNhanVien.LayDSNhanVien();
        adapterNV = new CustomAdapterNV(MainActivityNhanVien.this, R.layout.custom_item, dataNV);
        adapterNV.notifyDataSetChanged();
        lvDanhSachNV.setAdapter(adapterNV);
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
                Intent intent = new Intent(MainActivityNhanVien.this, MainActivityChucNang.class);
                startActivity(intent);
                break;

            case R.id.close:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityNhanVien.this);
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
            case android.R.id.home:
                onBackPressed();
                return true;
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
        Intent refresh = new Intent(MainActivityNhanVien.this, MainActivityNhanVien.class);
        startActivity(refresh);
        finish();
    }



}