package com.example.dostavka.ui.login.signUp;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dostavka.ui.https.PostClient;
import com.example.dostavka.ui.login.models.UserResponse;
import com.example.dostavka.ui.login.models.User_param;
import com.example.dostavka.ui.login.signIn.SignInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {
    public String name = "", email = "", password = "";
    SignUpListener listener;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public MutableLiveData<UserResponse> user = new MutableLiveData<>();

    public void signUp(final View view) {
        listener.Start();
        if (name.length() == 0 || email.length() == 0 || password.length() == 0) {
            listener.Failed("Заполните все поля");
        } else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        createUser(email,name,password);
                    }else {
                        Toast.makeText(view.getContext(), task.getException()+"", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void createUser(String name,String email,String password) {
        PostClient.getINSTANCE().createUser(email,name,password).enqueue(new Callback<UserResponse>() {
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
                Log.wtf("asdasd",t.getMessage());
            }
        });
    }

    public void signIn(View view) {
        view.getContext().startActivity(new Intent(view.getContext(), SignInActivity.class));
        listener.Finish();
    }
}
