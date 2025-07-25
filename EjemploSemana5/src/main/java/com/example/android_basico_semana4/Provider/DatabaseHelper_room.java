package com.example.android_basico_semana4.Provider;


import android.content.Context;

import com.example.android_basico_semana4.dm.Carro_with_room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseHelper_room {

    private CarroDatabase database;
    private ExecutorService executor;

    public DatabaseHelper_room(Context context) {
        database = CarroDatabase.getInstance(context);
        executor = Executors.newFixedThreadPool(4);
    }

    public void inicializarDatosIniciales() {
        executor.execute(() -> {
            // Verificar si ya existen datos
            if (database.carroDao().contarCarros() == 0) {
                insertarDatosIniciales();
            }
        });
    }

    private void insertarDatosIniciales() {
        List<Carro_with_room> carros = crearCarrosIniciales();
        database.carroDao().insertarCarros(carros);
    }

    private List<Carro_with_room> crearCarrosIniciales() {
        List<Carro_with_room> carros = new ArrayList<>();

        // 30 registros de carros variados
        carros.add(new Carro_with_room("Toyota", "Corolla", 2020, 45.5));
        carros.add(new Carro_with_room("Honda", "Civic", 2019, 42.3));
        carros.add(new Carro_with_room("Nissan", "Sentra", 2021, 40.8));
        carros.add(new Carro_with_room("Ford", "Focus", 2018, 38.2));
        carros.add(new Carro_with_room("Chevrolet", "Cruze", 2020, 44.1));

        carros.add(new Carro_with_room("Hyundai", "Elantra", 2022, 41.7));
        carros.add(new Carro_with_room("Kia", "Forte", 2021, 39.9));
        carros.add(new Carro_with_room("Mazda", "3", 2020, 43.2));
        carros.add(new Carro_with_room("Subaru", "Impreza", 2019, 40.5));
        carros.add(new Carro_with_room("Volkswagen", "Jetta", 2021, 42.8));

        carros.add(new Carro_with_room("Toyota", "Camry", 2022, 48.6));
        carros.add(new Carro_with_room("Honda", "Accord", 2020, 46.3));
        carros.add(new Carro_with_room("Nissan", "Altima", 2021, 45.1));
        carros.add(new Carro_with_room("Ford", "Fusion", 2019, 44.7));
        carros.add(new Carro_with_room("Chevrolet", "Malibu", 2020, 43.9));

        carros.add(new Carro_with_room("BMW", "320i", 2021, 50.2));
        carros.add(new Carro_with_room("Mercedes-Benz", "C200", 2022, 52.8));
        carros.add(new Carro_with_room("Audi", "A3", 2020, 49.5));
        carros.add(new Carro_with_room("Lexus", "IS250", 2021, 51.3));
        carros.add(new Carro_with_room("Infiniti", "Q50", 2019, 48.9));

        carros.add(new Carro_with_room("Toyota", "RAV4", 2022, 55.2));
        carros.add(new Carro_with_room("Honda", "CR-V", 2021, 53.7));
        carros.add(new Carro_with_room("Nissan", "Rogue", 2020, 52.4));
        carros.add(new Carro_with_room("Ford", "Escape", 2021, 51.8));
        carros.add(new Carro_with_room("Chevrolet", "Equinox", 2019, 50.6));

        carros.add(new Carro_with_room("Jeep", "Cherokee", 2020, 58.3));
        carros.add(new Carro_with_room("Dodge", "Journey", 2018, 56.7));
        carros.add(new Carro_with_room("Mitsubishi", "Outlander", 2021, 54.2));
        carros.add(new Carro_with_room("Acura", "RDX", 2022, 55.9));
        carros.add(new Carro_with_room("Volvo", "XC40", 2021, 57.1));

        return carros;
    }

    public CarroDatabase getDatabase() {
        return database;
    }

    public void shutdown() {
        if (executor != null) {
            executor.shutdown();
        }
    }
}