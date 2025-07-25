package com.example.android_basico_semana4.Actavity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_basico_semana4.Adapter.CarroAdapterRecicleview;
import com.example.android_basico_semana4.R;
import com.example.android_basico_semana4.dm.Carro;

import java.util.ArrayList;
import java.util.List;

public class MainActivityRecycleView extends AppCompatActivity {
    private RecyclerView rvCarros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_recycle_view);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/



        rvCarros = findViewById(R.id.rvCarros);
        rvCarros.setLayoutManager(new LinearLayoutManager(this));

        // Datos de ejemplo
        List<Carro> lista = new ArrayList<>();
        lista.add(new Carro("Toyota", "Corolla", 2020, 50));
        lista.add(new Carro("Honda", "Civic", 2021, 45));
        lista.add(new Carro("Honda", "Accord", 2025, 45));
        lista.add(new Carro("Honda", "CRV", 2018, 45));

       /* Cantidad de Columna */
        int cantidad = getResources().getInteger(R.integer.cantidad_columna);
        GridLayoutManager layoutManager=new GridLayoutManager(this, cantidad);

        rvCarros.setLayoutManager(layoutManager);


        CarroAdapterRecicleview CarroAdapter  = new CarroAdapterRecicleview(this, lista, carro -> {
            Intent intent = new Intent(MainActivityRecycleView.this, DetailActivity.class);
            intent.putExtra("carro",  carro);
            startActivity(intent);
        });

        rvCarros.setAdapter(CarroAdapter);

    }
}