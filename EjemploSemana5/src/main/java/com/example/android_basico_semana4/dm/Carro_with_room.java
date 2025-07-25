package com.example.android_basico_semana4.dm;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "carros")
public class Carro_with_room implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String fabricante;
    private String modelo;
    private int anio;
    private double ltGasolina;

    // Constructor vacío requerido por Room
    public Carro_with_room() {}

    public Carro_with_room(String fabricante, String modelo, int anio, double ltGasolina) {
        this.setFabricante(fabricante);
        this.setModelo(modelo);
        this.setAnio(anio);
        this.setLtGasolina(ltGasolina);
    }

    public void recorrerDistancia(int km) {
        if(km <= getLtGasolina()) {
            System.out.println("carro ==> Se recorrieron " + km + " km.");
            setLtGasolina(getLtGasolina() - km);
        } else {
            System.out.println("carro ==> Hace falta gasolina para recorrer esa distancia.");
        }
    }

    public void echarGasolina(int lt) {
        setLtGasolina(getLtGasolina() + lt);
        System.out.println("carro ==> Se echaron " + lt + " Galones de gasolina al carro.");
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getLtGasolina() {
        return ltGasolina;
    }

    public void setLtGasolina(double ltGasolina) {
        this.ltGasolina = ltGasolina;
    }
}
