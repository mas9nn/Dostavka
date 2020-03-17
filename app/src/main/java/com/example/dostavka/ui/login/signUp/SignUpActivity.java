package com.example.dostavka.ui.login.signUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.dostavka.R;
import com.example.dostavka.databinding.ActivitySignUpBinding;
import com.example.dostavka.ui.DB.DBHelper;
import com.example.dostavka.ui.Util;
import com.example.dostavka.ui.https.PostClient;
import com.example.dostavka.ui.login.models.UserResponse;
import com.example.dostavka.ui.login.models.User_param;
import com.example.dostavka.ui.main.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class SignUpActivity extends AppCompatActivity implements SignUpListener {
    ActivitySignUpBinding binding;
    SignUpViewModel model;
    Util util = new Util();
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        model = new ViewModelProvider(this).get(SignUpViewModel.class);
        binding.setViewmodel(model);
        final SignUpListener listener = this;
        model.listener = listener;
        dbHelper = new DBHelper(this);
        model.user.observe(this, new Observer<UserResponse>() {
            @Override
            public void onChanged(UserResponse user_params) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("id", user_params.getData().getId());
                cv.put("token", user_params.getData().getToken());
                cv.put("name", user_params.getData().getName());
                db.insert("user", null, cv);
                db.close();
                dbHelper.close();
                listener.Success();
            }
        });

    }

    @Override
    public void Success() {
        util.hideProgress(binding.progressBar);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void Failed(String message) {
        util.hideProgress(binding.progressBar);
        util.showToast(this,message);
    }

    @Override
    public void Finish() {
        util.hideProgress(binding.progressBar);
        finish();
    }

    @Override
    public void Start() {
        util.showProgress(binding.progressBar);
    }
}
