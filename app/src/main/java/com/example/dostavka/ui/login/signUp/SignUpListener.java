package com.example.dostavka.ui.login.signUp;

public interface SignUpListener {
    void Success();
    void Failed(String message);
    void Finish();
    void Start();
}
