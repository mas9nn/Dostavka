package com.example.dostavka.ui;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dostavka.ui.DB.DBHelper;

public class Util {

    public Util() {
    }

    public void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public String getToken(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("user", null, null, null, null, null, null);
        c.moveToFirst();
        int tokenColIndex = c.getColumnIndex("token");
        String token = c.getString(tokenColIndex);
        c.close();
        db.close();
        return token;
    }

    public String getUserId(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("user", null, null, null, null, null, null);
        c.moveToFirst();
        int idColIndex = c.getColumnIndex("id");
        String id = c.getString(idColIndex);
        c.close();
        db.close();
        return id;
    }

    public String getUserName(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("user", null, null, null, null, null, null);
        c.moveToFirst();
        int nameColIndex = c.getColumnIndex("name");
        String name = c.getString(nameColIndex);
        c.close();
        db.close();
        return name;
    }

    public void hideProgress(ProgressBar bar) {
        bar.setVisibility(View.INVISIBLE);
    }

    public void showProgress(ProgressBar bar) {
        bar.setVisibility(View.VISIBLE);
    }
    public void goneProgress(ProgressBar bar) {
        bar.setVisibility(View.GONE);
    }
}
