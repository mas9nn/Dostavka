package com.example.dostavka.ui;

import android.content.Context;
import android.widget.Toast;

public class Util {
    public Util() {
    }

    public void showToast(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
