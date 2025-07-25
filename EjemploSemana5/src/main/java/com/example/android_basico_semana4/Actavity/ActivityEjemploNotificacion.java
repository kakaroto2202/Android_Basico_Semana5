package com.example.android_basico_semana4.Actavity;

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_basico_semana4.R;
import com.example.android_basico_semana4.Receiver.NotificationReceiver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ActivityEjemploNotificacion extends AppCompatActivity {

    private static final String CHANNEL_ID = "NotificationChannel";
    private static final int NOTIFICATION_PERMISSION_REQUEST = 100;

    private TextView tvSelectedTime;
    private Button btnSelectTime, btnScheduleNotification, btnCancelNotification;
    private Calendar selectedCalendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejemplo_notificacion);

        /*
        *
        <receiver android:name="Autostart" android:exported="true">
                <intent-filter>
                    <action android:name="android.intent.action.BOOT_COMPLETED" />
                    <action android:name="android.intent.action.QUICKBOOT_POWERON" />
                </intent-filter>
            </receiver>

        <service StarterService" />

        * */

        initViews();
        createNotificationChannel();
        setupClickListeners();

        selectedCalendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // Solicitar permisos necesarios
        checkAndRequestPermissions();

    }

    private void initViews() {
        tvSelectedTime = findViewById(R.id.tv_selected_time);
        btnSelectTime = findViewById(R.id.btn_select_time);
        btnScheduleNotification = findViewById(R.id.btn_schedule_notification);
        btnCancelNotification = findViewById(R.id.btn_cancel_notification);
    }

    private void setupClickListeners() {
        btnSelectTime.setOnClickListener(v -> showTimePicker());
        btnScheduleNotification.setOnClickListener(v -> scheduleNotification());
        btnCancelNotification.setOnClickListener(v -> cancelNotification());
    }

    private void checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        NOTIFICATION_PERMISSION_REQUEST);
            }
        }

        // Verificar permisos de alarma exacta (Android 12+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            if (!alarmMgr.canScheduleExactAlarms()) {
                Toast.makeText(this, "Se requiere permiso para alarmas exactas", Toast.LENGTH_LONG).show();
            }
        }
    }


    private void showTimePicker() {
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (TimePicker view, int selectedHour, int selectedMinute) -> {
                    selectedCalendar = Calendar.getInstance();
                    selectedCalendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                    selectedCalendar.set(Calendar.MINUTE, selectedMinute);
                    selectedCalendar.set(Calendar.SECOND, 0);

                    // Si la hora ya pasó hoy, programar para mañana
                    if (selectedCalendar.getTimeInMillis() <= System.currentTimeMillis()) {
                        selectedCalendar.add(Calendar.DAY_OF_MONTH, 1);
                    }

                    updateSelectedTimeDisplay();
                }, hour, minute, true);

        timePickerDialog.show();
    }

    private void updateSelectedTimeDisplay() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm - dd/MM/yyyy", Locale.getDefault());
        String formattedTime = sdf.format(selectedCalendar.getTime());
        tvSelectedTime.setText("Hora seleccionada: " + formattedTime);
    }


    private void scheduleNotification() {
        if (selectedCalendar == null) {
            Toast.makeText(this, "Por favor selecciona una hora primero", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.putExtra("title", "¡Hora programada!");
        intent.putExtra("message", "La alarma que configuraste ha sonado");

        pendingIntent = PendingIntent.getBroadcast(
                this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        selectedCalendar.getTimeInMillis(),
                        pendingIntent
                );
            } else {
                alarmManager.setExact(
                        AlarmManager.RTC_WAKEUP,
                        selectedCalendar.getTimeInMillis(),
                        pendingIntent
                );
            }

            Toast.makeText(this, "Notificación programada exitosamente", Toast.LENGTH_SHORT).show();

        } catch (SecurityException e) {
            Toast.makeText(this, "Error: No se pueden programar alarmas exactas", Toast.LENGTH_LONG).show();
        }
    }


    private void cancelNotification() {
        if (pendingIntent != null && alarmManager != null) {
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
            Toast.makeText(this, "Notificación cancelada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No hay notificaciones programadas", Toast.LENGTH_SHORT).show();
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Canal de Notificaciones";
            String description = "Canal para notificaciones programadas";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == NOTIFICATION_PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso de notificaciones concedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso de notificaciones denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }



}