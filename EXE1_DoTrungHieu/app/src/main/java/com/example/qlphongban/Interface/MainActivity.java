package com.example.qlphongban.Interface;

import androidx.annotation.NonNull;
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

import com.example.qlphongban.Adapter.CustomAdapter;
import com.example.qlphongban.Database.DBPhongBan;
import com.example.qlphongban.Model.PhongBan;
import com.example.qlphongban.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnThem;
    ListView lvPhongBan;
    ArrayList<PhongBan> data_PB = new ArrayList<>();
    CustomAdapter adapter_PB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityAdd.class);
                startActivity(intent);
            }
        });
    }

    private void HienThiDL() {
        DBPhongBan dbPhongBan = new DBPhongBan(this);
        data_PB = dbPhongBan.LayDL();
        adapter_PB = new CustomAdapter(MainActivity.this, R.layout.custom_listview, data_PB);
        lvPhongBan.setAdapter(adapter_PB);
    }

    private void setControl() {
        btnThem = findViewById(R.id.btnThem);
        lvPhongBan = findViewById(R.id.lvPhongBan);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnUpdate:
                HienThiDL();
                break;
            case R.id.mnThoat:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
        }
        return true;
    }
}