package com.example.android_basico_semana4.dm;

public class Vehiculo {
    String matricula;
    String marca;
    String modelo;
    String color;
    double precio;
    boolean disponible;

    public Vehiculo(String matricula,
                    String marca,
                    String modelo,
                    String color,
                    double tarifa) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precio = tarifa;
        this.disponible = false;
    }

    // los métodos ‘get’ y ‘set’ de la clase Vehiculo
    public String getMatricula() {
        return this.matricula;
    }

    public String getMarca() {
        return this.marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public String getColor() {
        return this.color;
    }
    public double getTarifa() {
        return this.precio;
    }

    public boolean getDisponible() {
        return this.disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
