package com.devsenior.pablo.controller.strategy;

import com.devsenior.pablo.Util.Ubicacion;
import com.devsenior.pablo.model.Emergencia;

public class StrategyGravedad implements  StrategyPrioridad {

    @Override
    public int calcularPrioridad(Emergencia emergencia, Ubicacion ubicacionActual) {
        switch (emergencia.getNivelGravedad()) {
            case ALTO -> {
                return 100;
            }
            case INTERMEDIO -> {
                return 50;
            }
            case BAJO -> {
                return 10;
            }
            default -> throw new AssertionError();
        }
    }
    
}
