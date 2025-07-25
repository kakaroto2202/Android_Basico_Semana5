package com.example.android_basico_semana4.Provider;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.android_basico_semana4.dm.Estudiante;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EstudianteCSVReader {
    /**
     * Carga la lista de estudiantes a partir del CSV en el folder assets.
     *
     * @param context Contexto de la actividad o aplicación para acceder a assets.
     * @return Lista de objetos Estudiante.
     */
    public static List<Estudiante> cargarEstudiantes(Context context) {
        List<Estudiante> estudiantes = new ArrayList<>();
        AssetManager assetManager = context.getAssets();
        try (InputStream is = assetManager.open("estudiantes.csv");
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
                    int id = Integer.parseInt(tokens[0].trim());
                    String email = tokens[1].trim();
                    String nombre = tokens[2].trim();
                    String telefono = tokens[3].trim();

                    estudiantes.add(new Estudiante(id, email, nombre, telefono));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return estudiantes;
    }
}