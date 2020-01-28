package com.example.dostavka.ui.login.signUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.dostavka.R;
import com.example.dostavka.databinding.ActivitySignUpBinding;
import com.example.dostavka.ui.Util;
import com.example.dostavka.ui.main.MainActivity;

public class SignUpActivity extends AppCompatActivity implements SignUpListener {
    ActivitySignUpBinding binding;
    SignUpViewModel model;
    Util util = new Util();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        model = new ViewModelProvider(this).get(SignUpViewModel.class);
        binding.setViewmodel(model);
        SignUpListener listener = this;
        model.listener = listener;
    }

    @Override
    public void Success() {
        util.showToast(this,"Success");
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void Failed(String message) {
        util.showToast(this,message);
    }

    @Override
    public void Finish() {
        finish();
    }
}
