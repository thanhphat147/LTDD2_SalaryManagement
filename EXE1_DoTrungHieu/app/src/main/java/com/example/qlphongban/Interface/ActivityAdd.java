package com.example.qlphongban.Interface;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qlphongban.Database.DBPhongBan;
import com.example.qlphongban.Model.PhongBan;
import com.example.qlphongban.R;

public class ActivityAdd extends AppCompatActivity {

    EditText txtMaPB, txtTenPB;
    Button btnThem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemDL();
                Toast.makeText(ActivityAdd.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ThemDL() {
        PhongBan phongBan = new PhongBan();
        phongBan.setMaPhong(txtMaPB.getText().toString());
        phongBan.setTenPhong(txtTenPB.getText().toString());
        DBPhongBan dbPhongBan = new DBPhongBan(this);
        dbPhongBan.Them(phongBan);
    }

    private void setControl() {
        txtMaPB = findViewById(R.id.txtMaPhong);
        txtTenPB = findViewById(R.id.txtTenPhong);
        btnThem = findViewById(R.id.btnthemPB);
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