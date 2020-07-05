package com.example.project.Interface;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Adapter.CustomAdapterNV;
import com.example.project.Database.DBNhanVien;
import com.example.project.Model.NhanVien;
import com.example.project.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAdd, btnDelete, btnEdit;
    ListView lvDanhSachNV;
    CustomAdapterNV adapterNV;
    ArrayList<NhanVien> dataNV = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();

    }

    private void setEvent() {
        HienThiDL();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNhanVien.class);
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
        adapterNV = new CustomAdapterNV(MainActivity.this, R.layout.custom_item, dataNV);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
        }
        return super.onOptionsItemSelected(item);
    }

}