package com.example.android_basico_semana4.Actavity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_basico_semana4.R;
import com.example.android_basico_semana4.dm.Carro;

public class DetailActivity extends AppCompatActivity {

    private TextView tvDetalle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        tvDetalle = findViewById(R.id.tvDetalle);

        Carro carro = (Carro) getIntent().getSerializableExtra("carro");

        if (carro != null) {
            String texto = "Fabricante: " + carro.getFabricante() + "\n"
                    + "Modelo: "     + carro.getModelo()     + "\n"
                    + "Año: "        + carro.getAnio()       + "\n"
                    + "Gasolina: "   + carro.getLtGasolina();
            tvDetalle.setText(texto);
        }
    }
}