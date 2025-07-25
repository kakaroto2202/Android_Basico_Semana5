package com.example.android_basico_semana4.dm;



import java.io.Serializable;

public class Carro implements Serializable {

    private String fabricante;
    private String modelo;
    private int anio;
    private double ltGasolina;




    public Carro(String fabricante, String modelo, int anio, double ltGasolina) {
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
