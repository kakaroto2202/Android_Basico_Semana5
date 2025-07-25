package com.example.android_basico_semana4.Receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.core.app.NotificationCompat;

import com.example.android_basico_semana4.Actavity.ActivityEjemploNotificacion;

public class NotificationReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "NotificationChannel";

    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra("title");
        String message = intent.getStringExtra("message");

        showNotification(context, title, message);
    }

    private void showNotification(Context context, String title, String message) {
        // Intent para abrir la app cuando se toque la notificación
        Intent notificationIntent = new Intent(context, ActivityEjemploNotificacion.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        // Sonido de notificación
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // Crear la notificación
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title != null ? title : "Notificación programada")
                .setContentText(message != null ? message : "Tu alarma ha sonado")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setVibrate(new long[]{1000, 1000, 1000, 1000});

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1, builder.build());
    }
}