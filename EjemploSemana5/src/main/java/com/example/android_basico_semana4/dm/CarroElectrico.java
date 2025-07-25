package com.example.android_basico_semana4.dm;


public class CarroElectrico extends Carro implements TecnologiaElectrica {

    private double Potencia;


    public CarroElectrico(String fabricante, String modelo, int anio, double ltGasolina, double Potencia) {
        super(fabricante, modelo, anio, ltGasolina);
        this.Potencia=Potencia;
    }

    public CarroElectrico(String fabricante, String modelo, int anio, double Potencia) {
        super(fabricante, modelo, anio, 0);
        this.Potencia=Potencia;
    }



    @Override
    public void Set_PotenciaBateria() {

    }

    @Override
    public double Get_PotenciaBateria() {
        return Potencia;
    }
}
