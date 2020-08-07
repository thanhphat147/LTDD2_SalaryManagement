package com.example.exe4_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtSDT;
    Button btnCall, btnDS;
    String tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCall = new Intent(Intent.ACTION_CALL);
                tel = txtSDT.getText().toString();
                if(tel.trim().isEmpty()){
                    intentCall.setData(Uri.parse("tel:0368254544"));
                    //Toast.makeText(getApplicationContext(), "Please", Toast.LENGTH_SHORT).show();
                }
                else {
                    intentCall.setData(Uri.parse("tel:"+tel));
                }
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(getApplicationContext(), "Please", Toast.LENGTH_SHORT).show();
                    requestPermissions();
                }
                else {
                    startActivity(intentCall);
                }
            }
        });
        btnDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListSDT.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        txtSDT = findViewById(R.id.txtSDT);
        btnCall = findViewById(R.id.btnCall);
        btnDS = findViewById(R.id.btnDS);
    }

    private void requestPermissions(){
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
    }
}