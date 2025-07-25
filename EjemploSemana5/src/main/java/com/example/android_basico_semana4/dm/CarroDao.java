package com.example.android_basico_semana4.dm;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CarroDao {

    @Insert
    void insertarCarro(Carro_with_room carro);

    @Insert
    void insertarCarros(List<Carro_with_room> carros);

    @Update
    void actualizarCarro(Carro_with_room carro);

    @Delete
    void eliminarCarro(Carro_with_room carro);

    @Query("SELECT * FROM carros")
    List<Carro_with_room> obtenerTodosLosCarros();

    @Query("SELECT * FROM carros WHERE id = :id")
    Carro_with_room obtenerCarroPorId(int id);

    @Query("SELECT * FROM carros WHERE fabricante = :fabricante")
    List<Carro_with_room> obtenerCarrosPorFabricante(String fabricante);

    @Query("SELECT * FROM carros WHERE anio = :anio")
    List<Carro_with_room> obtenerCarrosPorAnio(int anio);

    @Query("SELECT * FROM carros WHERE anio BETWEEN :anioInicio AND :anioFin")
    List<Carro_with_room> obtenerCarrosPorRangoAnios(int anioInicio, int anioFin);

    @Query("DELETE FROM carros")
    void eliminarTodosLosCarros();

    @Query("SELECT COUNT(*) FROM carros")
    int contarCarros();
}

