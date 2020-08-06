package com.example.exe4_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.Date;

public class ListSDT extends AppCompatActivity {

    ListView lvSDT;
    ArrayList<String> data = new ArrayList<>();
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_s_d_t);
        setControl();
        setEvent();
    }

    private void setEvent() {
        getCallDetails();
    }

    private void getCallDetails() {
        StringBuffer buffer = new StringBuffer();
        String sd = new String();
        Cursor managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null, null, null);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        while (managedCursor.moveToNext()) {
            String phNumber = managedCursor.getString(number);
            buffer.append(phNumber + "-");
        }

        sd = buffer.toString();
        String[] a = sd.split("-");
        //Lay tung gia tri trong mang a gan vao data
        for (int i = 0; i < a.length; i++) {
            data.add(a[i]);
        }
        //Toast.makeText(this, data.get(1), Toast.LENGTH_SHORT).show();
        adapter = new CustomAdapter(this, R.layout.custom_view, data);
        lvSDT.setAdapter(adapter);
    }

    private void setControl() {
        lvSDT = findViewById(R.id.lvSDT);
    }
}