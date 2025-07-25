package com.example.android_basico_semana4.Actavity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_basico_semana4.R;

public class SecondActivity extends AppCompatActivity {

    public static final String EXTRA_CARRO = "com.example.ejemplocreaciongruposexposicion.CARRO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        // 1. Referenciar el TextView de detalle
        TextView tvDetalle = findViewById(R.id.tvDetalleCarro);

        // 2. Obtener el string enviado desde MainActivity
        String detalle = getIntent().getStringExtra(EXTRA_CARRO);

        // 3. Mostrar el detalle
        tvDetalle.setText(detalle);
    }
}