package com.example.dostavka.ui.home;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dostavka.ui.Util;
import com.example.dostavka.ui.home.models.RestaurantResponse;
import com.example.dostavka.ui.https.PostClient;
import com.example.dostavka.ui.login.signIn.SignInListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    public MutableLiveData<RestaurantResponse> shops = new MutableLiveData<>();
    public SignInListener listener;


    Util util = new Util();
    public void getAllShops(Context context){
        Log.wtf("ASdasd","asdasd");
        PostClient.getINSTANCE().getAllShops(util.getToken(context),util.getUserId(context)).enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                Log.wtf("asdas",response.body()+"");
                if(response.isSuccessful()) {
                    if (response.body().getSuccess()) {
                        shops.setValue(response.body());
                    } else {
                        listener.Failed(response.body().getMessage());
                    }
                }else{
                    listener.Failed(response.message());
                }
            }

            @Override
            public void onFailure(Call<RestaurantResponse> call, Throwable t) {
                Log.wtf("asdas",t.getMessage());
            }
        });
    }
}
