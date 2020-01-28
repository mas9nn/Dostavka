package com.example.dostavka.ui.login.signIn;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.dostavka.ui.login.signUp.SignUpActivity;

public class SignInViewModel extends ViewModel {

    public String email = "", password = "";
    SignInListener listener;


    public void logIn(View view) {
        if (email.length() == 0 || password.length() == 0) {
            listener.Failed("Заполните все поля");
        } else {
            listener.Success();
        }
    }

    public void singUp(View view) {
        view.getContext().startActivity(new Intent(view.getContext(), SignUpActivity.class));
        listener.Finish();
    }

}
