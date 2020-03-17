package com.example.dostavka.ui.chating;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dostavka.R;
import com.example.dostavka.ui.Util;
import com.example.dostavka.ui.chating.adapters.LVadapter;
import com.example.dostavka.ui.chating.models.ChatModel;
import com.example.dostavka.ui.chating.models.data;
import com.example.dostavka.ui.https.PostClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("Registered")
public class ChatActivity extends AppCompatActivity {

    Util util = new Util();
    List<data> datas = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

        PostClient.getINSTANCE().getMessages(util.getToken(this),util.getUserId(this),"1").enqueue(new Callback<ChatModel>() {
            @Override
            public void onResponse(Call<ChatModel> call, Response<ChatModel> response) {
                datas = response.body().getData();

                LVadapter boxAdapter = new LVadapter(ChatActivity.this, datas);

                // настраиваем список
                ListView lvMain = (ListView) findViewById(R.id.list_message);
                lvMain.setAdapter(boxAdapter);
            }
            @Override
            public void onFailure(Call<ChatModel> call, Throwable t) {
            }
        });
    }
}
