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
import android.widget.ListView;
import com.example.project.Adapter.CustomAdapterNV;
import com.example.project.Database.DBNhanVien;
import com.example.project.Model.NhanVien;
import com.example.project.R;
import java.util.ArrayList;
import java.util.Locale;

public class EmployeeActivity extends AppCompatActivity {
    Button btnAdd;
    ListView lvDanhSachNV;
    CustomAdapterNV adapterNV;
    ArrayList<NhanVien> dataNV = new ArrayList<>();
    Locale myLocale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        setControl();
        setEvent();
    }

    private void setEvent() {
//        HienThiDL();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeeActivity.this, AddNhanVien.class);
                startActivity(intent);

            }
        });

    }

    private void setControl() {
        btnAdd = findViewById(R.id.btnThem);
        lvDanhSachNV = findViewById(R.id.lvNhanVien);
    }

    private void HienThiDL() {
        DBNhanVien dbNhanVien = new DBNhanVien(this);
        dataNV = dbNhanVien.LayDL();
        adapterNV = new CustomAdapterNV(EmployeeActivity.this, R.layout.custom_item, dataNV);
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
            case R.id.update:
                HienThiDL();
                break;

            case R.id.close:
                AlertDialog.Builder builder = new AlertDialog.Builder(EmployeeActivity.this);
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
        Intent refresh = new Intent(EmployeeActivity.this, EmployeeActivity.class);
        startActivity(refresh);
        finish();
    }



}