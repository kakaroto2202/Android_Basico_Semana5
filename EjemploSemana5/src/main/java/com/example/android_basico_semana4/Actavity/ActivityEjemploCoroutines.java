package com.example.android_basico_semana4.Actavity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_basico_semana4.R;

import kotlin.Unit;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

public class ActivityEjemploCoroutines extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejemplo_coroutines);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);

        // Llamar a la coroutine cuando se presiona el botón
        button.setOnClickListener(v -> {
            // Ejecutar la coroutine
            ejecutarCoroutine();
        });

    }

    private void ejecutarCoroutine() {
        // Crear el scope de la coroutine
        CoroutineScope scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getMain());

        // Lanzar la coroutine
        BuildersKt.launch(scope, null, null, (coroutineScope, continuation) -> {

            // Cambiar texto inicial
            runOnUiThread(() -> textView.setText("Iniciando tarea..."));

            // Simular trabajo en background (cambiar a IO dispatcher)
            Object result = BuildersKt.withContext(Dispatchers.getIO(), (coroutineScope2, continuation2) -> {

                try {
                    // Simular una tarea que toma tiempo (ej: llamada a API)
                    Thread.sleep(3000);
                    return "¡Tarea completada!";
                } catch (InterruptedException e) {
                    return "Error en la tarea";
                }

            }, continuation);

            // Actualizar UI con el resultado
            String mensaje = (String) result;
            runOnUiThread(() -> textView.setText(mensaje));

            return Unit.INSTANCE;
        });
    }
}