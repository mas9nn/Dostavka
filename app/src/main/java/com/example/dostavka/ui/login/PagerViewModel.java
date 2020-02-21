package com.example.dostavka.ui.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dostavka.ui.https.PostClient;
import com.example.dostavka.ui.login.models.UserResponse;
import com.example.dostavka.ui.login.signIn.SignInListener;
import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagerViewModel extends ViewModel {

    public String btnText;

    SignInListener listener;
    public MutableLiveData<UserResponse> user = new MutableLiveData<>();

    public void clickBtnGoogle(View view) {


    }

    public void clickedBtnGoogle(String name,String email) {
        listener.Start();
        PostClient.getINSTANCE().createUserOther(name,email).enqueue(new Callback<UserResponse>() {
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

    public void clickBtn(View view) {

    }
}
