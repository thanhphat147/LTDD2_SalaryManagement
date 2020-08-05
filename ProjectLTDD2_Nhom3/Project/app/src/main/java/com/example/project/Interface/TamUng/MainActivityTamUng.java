package com.example.project.Interface.TamUng;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.project.Adapter.CustomAdapterTU;
import com.example.project.Database.DBTamUng;
import com.example.project.Interface.MainActivityChucNang;
import com.example.project.Library.LoadingDialog;
import com.example.project.Model.TamUng;
import com.example.project.R;

import java.util.ArrayList;

public class MainActivityTamUng extends AppCompatActivity {

    ListView lvTamUng;
    ArrayList<TamUng> data_TU;
    CustomAdapterTU adapter_TU;
    LoadingDialog loadingDialog = new LoadingDialog(MainActivityTamUng.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tam_ung);
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

        }, 3000);
    }

    private void setControl() {
        lvTamUng = findViewById(R.id.lvTamUng);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void HienThiDL() {
        DBTamUng dbTamUng = new DBTamUng(getApplicationContext());
        data_TU = dbTamUng.layDuLieu();
        adapter_TU = new CustomAdapterTU(MainActivityTamUng.this, R.layout.custom_view_tu, data_TU);
        adapter_TU.notifyDataSetChanged();
        lvTamUng.setAdapter(adapter_TU);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.btnHome:
                Intent intent = new Intent(MainActivityTamUng.this, MainActivityChucNang.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}