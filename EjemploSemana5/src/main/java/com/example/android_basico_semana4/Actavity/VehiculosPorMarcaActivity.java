package com.example.android_basico_semana4.Actavity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_basico_semana4.R;
import com.example.android_basico_semana4.dm.Carro;

import java.util.ArrayList;
import java.util.List;

public class VehiculosPorMarcaActivity extends AppCompatActivity {

    private ListView listView;
    private TextView tvTitulo;
    private List<Carro> todosVehiculos;
    private List<Carro> filtrados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vehiculos_por_marca);
      /*  ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        tvTitulo = findViewById(R.id.tvTitulo);
        listView = findViewById(R.id.listViewVehiculos);

        String marca = getIntent().getStringExtra("EXTRA_MARCA");
        tvTitulo.setText("Vehículos de marca: " + marca);

        todosVehiculos = cargarVehiculos();
        filtrados = new ArrayList<>();
        for (Carro c : todosVehiculos) {
            if (c.getFabricante().equalsIgnoreCase(marca)) {
                filtrados.add(c);
            }
        }

        List<String> nombresModelos = new ArrayList<>();
        for (Carro c : filtrados) {
            nombresModelos.add(c.getModelo() + " (" + c.getAnio() + ")");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                nombresModelos
        );
        listView.setAdapter(adapter);


    }

    private List<Carro> cargarVehiculos() {
        List<Carro> lista = new ArrayList<>();
        lista.add(new Carro("Toyota", "Corolla", 2020, 50));
        lista.add(new Carro("Honda", "Civic", 2019, 45));
        lista.add(new Carro("Ford", "Focus", 2018, 40));
        lista.add(new Carro("Toyota", "Yaris", 2021, 42));
        // Debe coincidir con la lista de MainActivity
        return lista;
    }
}