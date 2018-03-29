package com.itslp.cafeteria.cafetaAdministrador;


import android.app.PendingIntent;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.itslp.cafeteria.cafetaAdministrador.Actividades.HistoriaActivity;
import com.itslp.cafeteria.cafetaAdministrador.Actividades.MainActivity;

/**
 * Created by Trinidad on 30/11/17.
 */

public class Notificaciones extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);
        Log.d("poll","1.-"+remoteMessage.getFrom());
        Log.d("poll","2.-"+remoteMessage.getNotification().getTitle());
        Log.d("poll","3");
        Intent i =new Intent(this, HistoriaActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_ONE_SHOT);
        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_cart)
                .setContentTitle("Notificacion")
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(sonido)
                .setContentIntent(pendingIntent);
    }
}
