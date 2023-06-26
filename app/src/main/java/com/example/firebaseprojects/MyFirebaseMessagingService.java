package com.example.firebaseprojects;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
//    private static final String TAG = "MyFirebaseMsgService";
//
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
//
//        // Handle incoming FCM messages
//        Log.d(TAG, "Message data payload: " + remoteMessage.getData());
//
//        // Handle notification if required
//        if (remoteMessage.getNotification() != null) {
//            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
//        }
//    }
//
//    @Override
//    public void onNewToken(String token) {
//        super.onNewToken(token);
//
//        // Handle token refresh or registration
//        Log.d(TAG, "Refreshed token: " + token);
//
//        // You can send the token to your server or store it locally
//        // for sending FCM messages to this device later
//    }
//}

    private final int REQUEST_PERMISSION_PHONE_STATE = 1;
    Activity activity;

    public MyFirebaseMessagingService(MainActivity mainActivity) {
        this.activity = mainActivity;
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getfirebasemessage(message.getNotification().getTitle(), message.getNotification().getBody());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public void getfirebasemessage(String title, String body) {


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "myFirebaseChannel")
                .setSmallIcon(R.drawable.noti)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        managerCompat.notify(101, builder.build());
    }
}
