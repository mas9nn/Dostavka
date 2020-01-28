package com.example.dostavka.ui.login.signUp;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.dostavka.ui.login.signIn.SignInActivity;

public class SignUpViewModel extends ViewModel {
    public String name = "", email = "", password = "";
    SignUpListener listener;


    public void signUp(View view) {
        if (name.length() == 0 || email.length() == 0 || password.length() == 0) {
            listener.Failed("Заполните все поля");
        } else {
            listener.Success();
        }
    }

    public void signIn(View view) {
        view.getContext().startActivity(new Intent(view.getContext(), SignInActivity.class));
        listener.Finish();
    }
}
