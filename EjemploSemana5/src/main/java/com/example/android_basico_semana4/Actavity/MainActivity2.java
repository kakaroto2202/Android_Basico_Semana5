package com.example.android_basico_semana4.Actavity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_basico_semana4.Adapter.CarroAdapter;
import com.example.android_basico_semana4.Provider.Library;
import com.example.android_basico_semana4.R;
import com.example.android_basico_semana4.dm.Carro;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private ListView listViewCarros;
    private CarroAdapter adapter;
    private List<Carro> listaCarros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

        // 1. Referenciar el ListView
        listViewCarros = findViewById(R.id.listViewCarros);

        // 2. Crear la lista de carros
        /*listaCarros = new ArrayList<>();
        listaCarros.add(new Carro("Toyota", "Corolla", 2020, 50));
        listaCarros.add(new Carro("Honda", "Civic", 2018, 45));
        listaCarros.add(new Carro("Ford", "Focus", 2019, 40));*/

        listaCarros  = Library.GetListCar(getApplicationContext());

        // 3. Crear el adapter
        adapter = new CarroAdapter(this, listaCarros);

        // 4. Asignar el adapter al ListView
        listViewCarros.setAdapter(adapter);

        // 5. Manejo de clic en ítem: enviar datos a SecondActivity
        listViewCarros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Carro seleccionado = listaCarros.get(position);
                // Construir detalle a enviar
                String detalle = "Fabricante: " + seleccionado.getFabricante()
                        + "Modelo: " + seleccionado.getModelo() +
                         "Año: " + seleccionado.getAnio() +
                        "Gasolina (lt): " + seleccionado.getLtGasolina();
                Intent intent = new Intent(MainActivity2.this, SecondActivity.class);
                intent.putExtra(SecondActivity.EXTRA_CARRO, detalle);
                startActivity(intent);
            }
        });
    }




}