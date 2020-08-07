package com.example.project.Interface.ChamCong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.project.Adapter.CustomAdapterCC;
import com.example.project.Database.DBChamCong;
import com.example.project.Interface.MainActivityChucNang;
import com.example.project.Library.LoadingDialog;
import com.example.project.Model.ChamCong;
import com.example.project.R;

import java.util.ArrayList;

public class MainActivityChamCong extends AppCompatActivity {
    ListView lvChamCong;
    CustomAdapterCC adapter_chamcong;
    ArrayList<ChamCong> data_chamcong = new ArrayList<>();
    LoadingDialog loadingDialog = new LoadingDialog(MainActivityChamCong.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cham_cong);
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
            }

        }, 1000);
    }


    private void setControl() {
        lvChamCong = findViewById(R.id.lvChamCong);
    }

    private void HienThiDL() {
        DBChamCong dbChamCong = new DBChamCong(getApplicationContext());
        data_chamcong = dbChamCong.layDuLieuCC();
        adapter_chamcong = new CustomAdapterCC(MainActivityChamCong.this, R.layout.custom_view_cc, data_chamcong);
        adapter_chamcong.notifyDataSetChanged();
        lvChamCong.setAdapter(adapter_chamcong);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.btnHome:
                Intent intent = new Intent(MainActivityChamCong.this, MainActivityChucNang.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}