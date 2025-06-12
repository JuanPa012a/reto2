package com.devsenior.pablo.controller.strategy;

import com.devsenior.pablo.Util.Ubicacion;
import com.devsenior.pablo.model.Emergencia;

public interface StrategyPrioridad {
    int calcularPrioridad(Emergencia emergencia, Ubicacion ubicacionActual);
}
