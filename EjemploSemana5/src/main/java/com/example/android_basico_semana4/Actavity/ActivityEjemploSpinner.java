package com.example.android_basico_semana4.Actavity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_basico_semana4.R;
import com.example.android_basico_semana4.dm.Carro;

import java.util.ArrayList;
import java.util.List;

public class ActivityEjemploSpinner extends AppCompatActivity {

    private Spinner spinnerCarros;
    private List<Carro> listaCarros;

    private TextView textViewCarro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejemplo_spinner);


        spinnerCarros = findViewById(R.id.spinnerCarros);
        textViewCarro = findViewById(R.id.textViewCarro);

        // Inicializar lista de carros
        listaCarros = new ArrayList<>();
        listaCarros.add(new Carro("Toyota", "Corolla", 2020, 50));
        listaCarros.add(new Carro("Honda", "Civic", 2019, 45));
        listaCarros.add(new Carro("Ford", "Focus", 2018, 55));
        listaCarros.add(new Carro("Chevrolet", "Cruze", 2021, 60));
        listaCarros.add(new Carro("Nissan", "Sentra", 2017, 40));

        // Crear una lista de marcas a partir de los objetos Carro
        List<String> marcas = new ArrayList<>();
        for (Carro carro : listaCarros) {
            marcas.add(carro.getFabricante() + " - " + carro.getModelo());
        }

        // Crear un adaptador y asignarlo al spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                marcas
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCarros.setAdapter(adapter);

        // Manejar selección del spinner
        spinnerCarros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Carro carroSeleccionado = listaCarros.get(position);
                String detalleCarro = "Marca: " + carroSeleccionado.getFabricante() +
                        "\nModelo: " + carroSeleccionado.getModelo() +
                        "\nAño: " + carroSeleccionado.getAnio() +
                        "\nLitros de Gasolina: " + carroSeleccionado.getLtGasolina();
                textViewCarro.setText(detalleCarro);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textViewCarro.setText("");
            }
        });



    }
}