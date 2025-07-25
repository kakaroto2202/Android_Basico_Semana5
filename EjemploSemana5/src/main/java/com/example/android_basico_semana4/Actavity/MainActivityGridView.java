package com.example.android_basico_semana4.Actavity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_basico_semana4.Adapter.BrandAdapter_imagen;
import com.example.android_basico_semana4.Provider.DatabaseHelper_room;
import com.example.android_basico_semana4.Provider.Library;
import com.example.android_basico_semana4.R;
import com.example.android_basico_semana4.dm.Carro;
import com.example.android_basico_semana4.dm.CarroEntity;
import com.example.android_basico_semana4.dm.Carro_with_room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivityGridView extends AppCompatActivity {

    private GridView gridMarcas;
    private List<Carro> listaVehiculos;

    /// Room Example
    private DatabaseHelper_room databaseHelper_room;
    private ExecutorService executor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_grid_view);


        gridMarcas = findViewById(R.id.gridMarcas);
        //listaVehiculos = cargarVehiculos();
        listaVehiculos = Library.getAllCarrosFromDB(getApplicationContext());


        /// EJEMPLO CON ROOMS

        // Inicializar helper de base de datos
        databaseHelper_room = new DatabaseHelper_room(this);
         executor = Executors.newFixedThreadPool(2);

        // Inicializar datos
        databaseHelper_room.inicializarDatosIniciales();

        // Ejemplo de uso de la base de datos
         ejemploDeUso();


        //BrandAdapter adapter = new BrandAdapter(this, listaVehiculos);
        BrandAdapter_imagen adapter = new BrandAdapter_imagen(this, listaVehiculos);
        int cantidad = getResources().getInteger(R.integer.cantidad_columna);
        gridMarcas.setNumColumns(cantidad);
        gridMarcas.setAdapter(adapter);

        gridMarcas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String marca = listaVehiculos.get(position).getFabricante();
                Intent intent = new Intent(MainActivityGridView.this, VehiculosPorMarcaActivity.class);
                intent.putExtra("EXTRA_MARCA", marca);
                startActivity(intent);
            }
        });

    }

    private List<Carro> cargarVehiculos() {
        List<Carro> lista = new ArrayList<>();
        lista.add(new Carro("Toyota", "Corolla", 2020, 50));
        lista.add(new Carro("Honda", "Civic", 2019, 45));
        lista.add(new Carro("Ford", "Focus", 2018, 40));
        lista.add(new Carro("Toyota", "Yaris", 2021, 42));
        lista.add(new Carro("Chevrolet", "Camaro", 2021, 42));
        // Agrega más vehículos según necesidad
        return lista;
    }

    private void ejemploDeUso() {
        executor.execute(() -> {
            try {
                // Esperar un momento para que se inicialicen los datos
                Thread.sleep(1000);

                // Obtener todos los carros
                List<Carro_with_room> todosLosCarros = databaseHelper_room.getDatabase().carroDao().obtenerTodosLosCarros();

                runOnUiThread(() -> {
                    Toast.makeText(this, "Total de carros: " + todosLosCarros.size(), Toast.LENGTH_SHORT).show();
                });

                // Mostrar algunos carros en el log
                Log.d("CarroApp", "=== LISTA DE CARROS ===");
                for (int i = 0; i < Math.min(5, todosLosCarros.size()); i++) {
                    Carro_with_room carro = todosLosCarros.get(i);
                    Log.d("CarroApp", String.format("ID: %d, %s %s (%d) - %.1f L",
                            carro.getId(),
                            carro.getFabricante(),
                            carro.getModelo(),
                            carro.getAnio(),
                            carro.getLtGasolina()));
                }

                // Buscar carros por fabricante
                List<Carro_with_room> toyotas = databaseHelper_room.getDatabase().carroDao().obtenerCarrosPorFabricante("Toyota");
                Log.d("CarroApp", "Carros Toyota encontrados: " + toyotas.size());

                // Buscar carros por año
                List<Carro_with_room> carros2021 = databaseHelper_room.getDatabase().carroDao().obtenerCarrosPorAnio(2021);
                Log.d("CarroApp", "Carros del 2021: " + carros2021.size());

                // Ejemplo de actualización
                if (!todosLosCarros.isEmpty()) {
                    Carro_with_room primerCarro = todosLosCarros.get(0);
                    primerCarro.echarGasolina(10);
                    databaseHelper_room.getDatabase().carroDao().actualizarCarro(primerCarro);
                    Log.d("CarroApp", "Carro actualizado: " + primerCarro.getFabricante() + " " + primerCarro.getModelo());
                }

                // Ejemplo de inserción de nuevo carro
                Carro_with_room nuevoCarro = new Carro_with_room("Tesla", "Model 3", 2023, 0); // Carro eléctrico
                databaseHelper_room.getDatabase().carroDao().insertarCarro(nuevoCarro);
                Log.d("CarroApp", "Nuevo carro insertado: Tesla Model 3");

                // Verificar el total después de la inserción
                int totalFinal = databaseHelper_room.getDatabase().carroDao().contarCarros();
                Log.d("CarroApp", "Total final de carros: " + totalFinal);

                runOnUiThread(() -> {
                    Toast.makeText(this, "Operaciones completadas. Ver logs para detalles.", Toast.LENGTH_LONG).show();
                });

            } catch (InterruptedException e) {
                Log.e("CarroApp", "Error en operaciones de base de datos", e);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (databaseHelper_room != null) {
            databaseHelper_room.shutdown();
        }
        if (executor != null) {
            executor.shutdown();
        }
    }
}