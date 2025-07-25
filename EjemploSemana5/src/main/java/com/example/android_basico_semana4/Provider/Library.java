package com.example.android_basico_semana4.Provider;

import static com.example.android_basico_semana4.Provider.DatabaseHelper.COLUMN_ANIO;
import static com.example.android_basico_semana4.Provider.DatabaseHelper.COLUMN_FABRICANTE;
import static com.example.android_basico_semana4.Provider.DatabaseHelper.COLUMN_LT_GASOLINA;
import static com.example.android_basico_semana4.Provider.DatabaseHelper.COLUMN_MODELO;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android_basico_semana4.dm.Carro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Library {


    private static DatabaseHelper dbHelper;
    private static SQLiteDatabase newDB;

    // Método para inicializar la base de datos solo una vez
    public static void initializeDatabase(Context context) {
        if (dbHelper == null) {
            dbHelper = DatabaseHelper.getInstance(context);
            newDB = dbHelper.getWritableDatabase();
        }
    }

    // Método para cerrar la base de datos cuando ya no se necesite
    public static void closeDatabase() {
        if (newDB != null && newDB.isOpen()) {
            newDB.close();
            newDB = null;
        }
        dbHelper = null;
    }


    // Método para buscar carros por fabricante
    public static List<Carro> searchCarros(final Context resolver,String searchTerm) {
        List<Carro> carroList = new ArrayList<>();

        initializeDatabase(resolver);  // Asegura que la BD esté inicializada


        // Query para buscar por fabricante (case-insensitive)
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_CARROS +
                " WHERE " + COLUMN_FABRICANTE + " LIKE ? " +
                " ORDER BY " + COLUMN_FABRICANTE + ", " + COLUMN_MODELO + ", " + COLUMN_ANIO;

        String[] selectionArgs = {"%" + searchTerm + "%"};

        Cursor cursor = null;
        try {
            cursor = newDB.rawQuery(query, selectionArgs);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String fabricante = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FABRICANTE));
                    String modelo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MODELO));
                    int anio = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ANIO));
                    double ltGasolina = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_LT_GASOLINA));

                    Carro carro = new Carro(fabricante, modelo, anio, ltGasolina);
                    carroList.add(carro);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }

        }

        return carroList;
    }


    public static List<Carro> getAllCarrosFromDB(final Context resolver) {
        List<Carro> carrosList = new ArrayList<>();
        Cursor cursor = null;

        initializeDatabase(resolver);  // Asegura que la BD esté inicializada

        cursor = newDB.rawQuery (" select * from  "+DatabaseHelper.TABLE_CARROS, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String fabricante = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FABRICANTE));
                String modelo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MODELO));
                int anio = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ANIO));
                double ltGasolina = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_LT_GASOLINA));

                // Crear un objeto Carro y añadirlo a la lista
                Carro carro = new Carro(fabricante, modelo, anio, ltGasolina);
                carrosList.add(carro);

            } while (cursor.moveToNext());

            cursor.close();
        }

        return carrosList;
    }


    public static List<Carro>  GetListCar (final Context context){
        List<Carro> LisCar = new ArrayList<Carro>();

        LisCar.add(new Carro("Toyota", "Corolla", 2020, 50));
        LisCar.add(new Carro("Honda", "Civic", 2018, 45));
        LisCar.add(new Carro("Ford", "Focus", 2019, 40));

        return LisCar;

    }

    public static String LoadData(final Context context,String inFile) {
        String tContents = "";

        try {
            InputStream stream = context.getAssets().open(inFile);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            tContents = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
        }

        return tContents;

    }

    public static List<Carro> LoadFileCarCSV(Context context, String inFile) {
        List<Carro> list = new ArrayList<>();
        AssetManager assetManager = context.getAssets();
        try (InputStream is = assetManager.open(inFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;
            boolean primeraLinea = true;
            while ((line = reader.readLine()) != null) {
                // Omitir cabecera
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                String[] tokens = line.split(",");
                if (tokens.length >= 4) {
                    String fabricante = tokens[0].trim();
                    String modelo = tokens[1].trim();
                    int year = Integer.parseInt(tokens[2].trim());
                    double ltgasolina = Double.parseDouble(tokens[3].trim());

                    list.add(new Carro(fabricante, modelo, year, ltgasolina));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
