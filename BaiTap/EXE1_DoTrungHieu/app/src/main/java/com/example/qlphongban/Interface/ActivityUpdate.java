package com.example.qlphongban.Interface;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlphongban.Database.DBPhongBan;
import com.example.qlphongban.Model.PhongBan;
import com.example.qlphongban.R;

import java.util.ArrayList;

public class ActivityUpdate extends AppCompatActivity {

    EditText txtTenPB;
    TextView tvMaPB;
    Button btnSua;
    ArrayList<PhongBan> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }
    private void setEvent() {
        String maPB = getIntent().getExtras().getString("ma");
        DBPhongBan dbPhongBan = new DBPhongBan(this);
        data = dbPhongBan.LayDL(maPB);
        tvMaPB.setText(data.get(0).getMaPhong());
        txtTenPB.setText(data.get(0).getTenPhong());

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuaDL();
                Toast.makeText(ActivityUpdate.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SuaDL() {
        PhongBan phongBan = new PhongBan();
        phongBan.setMaPhong(tvMaPB.getText().toString());
        phongBan.setTenPhong(txtTenPB.getText().toString());
        DBPhongBan dbPhongBan = new DBPhongBan(this);
        dbPhongBan.Sua(phongBan);
    }

    private void setControl() {
        tvMaPB = findViewById(R.id.tvMaPhongS);
        txtTenPB = findViewById(R.id.txtTenPhongS);
        btnSua = findViewById(R.id.btnSuaPB);
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