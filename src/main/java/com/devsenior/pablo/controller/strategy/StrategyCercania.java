package com.devsenior.pablo.controller.strategy;

import com.devsenior.pablo.Util.Ubicacion;
import com.devsenior.pablo.model.Emergencia;


public class StrategyCercania implements StrategyPrioridad{

    
    @Override
    public int calcularPrioridad(Emergencia emergencia, Ubicacion ubicacionActual) {
        
        Ubicacion ubicacionEmergencia = Ubicacion.valueOf(emergencia.getUbicacion());
        

        Double distancia = Math.sqrt(
            Math.pow((ubicacionActual.getX() - ubicacionEmergencia.getX()), 2)
            +   Math.pow((ubicacionActual.getY() - ubicacionEmergencia.getY()), 2)
            ); 

        
        return  distancia.intValue();

    }

}