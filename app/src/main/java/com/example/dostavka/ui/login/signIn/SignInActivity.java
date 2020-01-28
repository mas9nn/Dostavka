package com.example.dostavka.ui.login.signIn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.example.dostavka.R;
import com.example.dostavka.databinding.ActivitySignInBinding;
import com.example.dostavka.ui.Util;
import com.example.dostavka.ui.main.MainActivity;

public class SignInActivity extends AppCompatActivity implements SignInListener {

    ActivitySignInBinding binding;
    SignInViewModel model;
    Util util = new Util();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        model = new ViewModelProvider(this).get(SignInViewModel.class);
        binding.setViewmodel(model);
        SignInListener listener = this;
        model.listener = listener;
        binding.password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                binding.emailLayout.setError(null);
                binding.passwordLayout.setError(null);
                Log.wtf("sdasd","sdasd");
            }
        });
    }

    @Override
    public void Success() {
        util.showToast(this, "Success");
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void Failed(String message) {
        binding.emailLayout.setError("Enter email");
        binding.passwordLayout.setError("Enter password");
       // util.showToast(this, message);
    }

    @Override
    public void Finish() {
        finish();
    }
}
