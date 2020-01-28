package com.example.dostavka.ui.login.signIn;

public interface SignInListener {
    void Success();
    void Failed(String message);
    void Finish();
}
