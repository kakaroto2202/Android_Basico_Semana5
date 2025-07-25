package com.example.android_basico_semana4.dm;

// 1. Entidad: CarroEntity.java

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "carro_table")
public class CarroEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String fabricante;
    private String modelo;
    private int anio;
    private double ltGasolina;

    public CarroEntity(String fabricante, String modelo, int anio, double ltGasolina) {
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.anio = anio;
        this.ltGasolina = ltGasolina;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFabricante() { return fabricante; }
    public void setFabricante(String fabricante) { this.fabricante = fabricante; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public double getLtGasolina() { return ltGasolina; }
    public void setLtGasolina(double ltGasolina) { this.ltGasolina = ltGasolina; }
}
