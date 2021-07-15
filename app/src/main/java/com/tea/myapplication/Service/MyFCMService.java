package com.tea.myapplication.Service;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.tea.myapplication.Common.Common;

import java.util.Map;
import java.util.Random;

public class MyFCMService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Map<String, String> dataReceived = remoteMessage.getData();
        if (dataReceived != null) {
            Common.showNotification(this, new Random().nextInt(),
                    dataReceived.get(Common.NOTIFICATION_TITLE),
                    dataReceived.get(Common.NOTIFICATION_CONTENT),
                    dataReceived.get(Common.NOTIFICATION_SENDER),
                    dataReceived.get(Common.NOTIFICATION_ROOM_ID), null);
        }
    }
}