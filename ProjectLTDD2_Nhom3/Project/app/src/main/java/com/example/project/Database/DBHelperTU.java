package com.example.project.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperTU extends SQLiteOpenHelper {

    public DBHelperTU(Context context) {
        super(context, "QLNhanVien", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table TamUng (manv text, tennv text , sophieu integer primary key unique, ngayghicong date, tienung int)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
