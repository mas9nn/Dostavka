package com.example.dostavka.ui.login.signIn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.dostavka.R;
import com.example.dostavka.databinding.ActivitySignInBinding;
import com.example.dostavka.ui.DB.DBHelper;
import com.example.dostavka.ui.Util;
import com.example.dostavka.ui.login.models.UserResponse;
import com.example.dostavka.ui.main.MainActivity;

public class SignInActivity extends AppCompatActivity implements SignInListener {

    ActivitySignInBinding binding;
    SignInViewModel model;
    Util util = new Util();
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        model = new ViewModelProvider(this).get(SignInViewModel.class);
        binding.setViewmodel(model);
        final SignInListener listener = this;
        dbHelper = new DBHelper(this);
        model.listener = listener;
        binding.password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                binding.emailLayout.setError(null);
                binding.passwordLayout.setError(null);
            }
        });
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
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void Failed(String message) {
        util.hideProgress(binding.progressBar);
        binding.emailLayout.setError("Enter email");
        binding.passwordLayout.setError("Enter password");
        util.showToast(this, message);
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
