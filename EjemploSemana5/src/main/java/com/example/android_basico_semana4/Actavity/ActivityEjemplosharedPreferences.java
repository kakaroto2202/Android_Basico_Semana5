package com.example.android_basico_semana4.Actavity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_basico_semana4.R;
import com.example.android_basico_semana4.Utils;

public class ActivityEjemplosharedPreferences extends AppCompatActivity {

    private Spinner spinnerColores;
    private LinearLayout layoutPrincipal;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "ColorPrefs";
    private static final String KEY_COLOR_INDEX = "color_index";
    private static final String KEY_COLOR_VALUE = "color_value";

    // Array de nombres de colores para el Spinner
    private String[] nombresColores = {
            "Blanco",
            "Rojo",
            "Verde",
            "Azul",
            "Amarillo",
            "Naranja",
            "Morado",
            "Rosa",
            "Gris",
            "Negro"
    };

    // Array de valores de colores correspondientes
    private int[] valoresColores = {
            Color.WHITE,
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.YELLOW,
            Color.rgb(255, 165, 0), // Naranja
            Color.rgb(128, 0, 128),  // Morado
            Color.rgb(255, 192, 203), // Rosa
            Color.GRAY,
            Color.BLACK
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejemploshared_preferences);

        // Inicializar vistas
        spinnerColores = findViewById(R.id.spinner_colores);
        layoutPrincipal = findViewById(R.id.layout_principal);

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Configurar el Spinner
        configurarSpinner();

        // Cargar el color guardado
        cargarColorGuardado();

        // Configurar listener del Spinner
        spinnerColores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Cambiar el color del layout
                cambiarColorLayout(position);

                // Guardar la selección
                guardarSeleccion(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }
        });

        Utils.GuardarConfiguracion(getApplicationContext(),"Hola Mundo","file.txt");
        Utils.GetConfiguracion(getApplicationContext(),"file.txt");
    }

    private void configurarSpinner() {
        // Crear adapter para el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                nombresColores
        );

        // Configurar el layout del dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el adapter al Spinner
        spinnerColores.setAdapter(adapter);
    }

    private void cambiarColorLayout(int position) {
        // Cambiar el color de fondo del layout principal
        layoutPrincipal.setBackgroundColor(valoresColores[position]);
    }

    private void guardarSeleccion(int position) {
        // Guardar tanto el índice como el valor del color
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_COLOR_INDEX, position);
        editor.putInt(KEY_COLOR_VALUE, valoresColores[position]);
        editor.apply();
    }

    private void cargarColorGuardado() {
        // Cargar el índice guardado (por defecto 0 = blanco)
        int indiceGuardado = sharedPreferences.getInt(KEY_COLOR_INDEX, 0);
        int colorGuardado = sharedPreferences.getInt(KEY_COLOR_VALUE, Color.WHITE);

        // Establecer la selección en el Spinner
        spinnerColores.setSelection(indiceGuardado);

        // Aplicar el color guardado al layout
        layoutPrincipal.setBackgroundColor(colorGuardado);
    }

    // Método opcional para obtener el color actual
    public int getColorActual() {
        return sharedPreferences.getInt(KEY_COLOR_VALUE, Color.WHITE);
    }

    // Método opcional para obtener el índice actual
    public int getIndiceActual() {
        return sharedPreferences.getInt(KEY_COLOR_INDEX, 0);
    }


}