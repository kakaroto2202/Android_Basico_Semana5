package com.example.android_basico_semana4.Actavity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_basico_semana4.R;

import java.util.Calendar;

public class ActivityEjemplosDialogos extends AppCompatActivity {

    private TextView textView;
    private Button btnAlert, btnProgress, btnDate, btnTime;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejemplos_dialogos);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/


        // Referencias a la vista
        textView = findViewById(R.id.textView);
        btnAlert = findViewById(R.id.btnAlert);
        btnProgress = findViewById(R.id.btnProgress);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);

        // Configurar botones para mostrar los diferentes diálogos
        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();
            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });


    }

    // 1. AlertDialog: un diálogo simple con opciones
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación")
                .setMessage("¿Deseas continuar?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ActivityEjemplosDialogos.this, "Elegiste Sí", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ActivityEjemplosDialogos.this, "Elegiste No", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ActivityEjemplosDialogos.this, "Cancelado", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();
    }

    // 2. ProgressDialog: muestra una barra de progreso (desaprobado en versiones recientes)
    private void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(true);
        progressDialog.show();

        // Simula que se cierra después de 3 segundos
        textView.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        }, 3000);
    }

    // 3. DatePickerDialog: selecciona una fecha
    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    textView.setText("Fecha seleccionada: " + selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
                }, year, month, day);
        datePickerDialog.show();
    }

    // 4. TimePickerDialog: selecciona una hora
    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, selectedHour, selectedMinute) -> {
                    textView.setText("Hora seleccionada: " + selectedHour + ":" + selectedMinute);
                }, hour, minute, true);
        timePickerDialog.show();
    }



}