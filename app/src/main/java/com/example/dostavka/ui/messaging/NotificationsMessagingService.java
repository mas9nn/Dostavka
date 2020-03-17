package com.example.dostavka.ui.messaging;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

@SuppressLint("Registered")
public class NotificationsMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.wtf("sdad",remoteMessage.getData()+"");
        super.onMessageReceived(remoteMessage);
    }
}
