package com.example.android_basico_semana4.Provider;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.android_basico_semana4.dm.CarroDao;
import com.example.android_basico_semana4.dm.Carro_with_room;


@Database(entities = {Carro_with_room.class}, version = 1, exportSchema = false)
public abstract class CarroDatabase extends RoomDatabase {

    private static CarroDatabase INSTANCE;

    public abstract CarroDao carroDao();

    public static CarroDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CarroDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    CarroDatabase.class,
                                    "carro_database"
                            ).allowMainThreadQueries() // Solo para pruebas, no recomendado en producción
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
