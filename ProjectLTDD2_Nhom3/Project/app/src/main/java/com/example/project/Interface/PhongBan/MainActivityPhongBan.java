package com.example.project.Interface.PhongBan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.project.Adapter.CustomAdapterPB;
import com.example.project.Database.DBPhongBan;
import com.example.project.Interface.MainActivityChucNang;
import com.example.project.Model.PhongBan;
import com.example.project.R;

import java.util.ArrayList;

public class MainActivityPhongBan extends AppCompatActivity {

    Button btnThem;
    ListView lvPhongBan;
    ArrayList<PhongBan> data_PB = new ArrayList<>();
    CustomAdapterPB adapter_PB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_phong_ban);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }
    private void setEvent() {
        HienThiDL();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityPhongBan.this, AddPhongBan.class);
                startActivity(intent);
            }
        });
    }

    private void HienThiDL() {
        DBPhongBan dbPhongBan = new DBPhongBan(this);
        data_PB = dbPhongBan.LayDL();
        adapter_PB = new CustomAdapterPB(MainActivityPhongBan.this, R.layout.custom_view_pb, data_PB);
        lvPhongBan.setAdapter(adapter_PB);
    }

    private void setControl() {
        btnThem = findViewById(R.id.btnThem);
        lvPhongBan = findViewById(R.id.lvPhongBan);
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
                Intent intent = new Intent(MainActivityPhongBan.this, MainActivityChucNang.class);
                startActivity(intent);
                break;
            case R.id.close:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityPhongBan.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn thoát không?");
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
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return true;
    }
}