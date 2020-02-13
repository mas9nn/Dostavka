package com.example.dostavka.ui.login.signIn;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dostavka.ui.https.PostClient;
import com.example.dostavka.ui.login.models.UserResponse;
import com.example.dostavka.ui.login.signUp.SignUpActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInViewModel extends ViewModel {

    public String email = "", password = "";
    SignInListener listener;

    public MutableLiveData<UserResponse> user = new MutableLiveData<>();
    public void logIn(View view) {
        listener.Start();
        if (email.length() == 0 || password.length() == 0) {
            listener.Failed("Заполните все поля");
        } else {
            login(email,password);
        }
    }
    private void login(String email,String password) {
        PostClient.getINSTANCE().login(email,password).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()) {
                    if (response.body().getSuccess()) {
                        user.setValue(response.body());
                    } else {
                        listener.Failed(response.body().getMessage());
                    }
                }else{
                    listener.Failed(response.message());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }
    public void singUp(View view) {
        view.getContext().startActivity(new Intent(view.getContext(), SignUpActivity.class));
        listener.Start();
    }

}
