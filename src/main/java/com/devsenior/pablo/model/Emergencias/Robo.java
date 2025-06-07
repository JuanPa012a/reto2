package com.devsenior.pablo.model.Emergencias;

import com.devsenior.pablo.model.Emergencia;
import com.devsenior.pablo.model.Tipo;
import com.devsenior.pablo.service.Responder;

public class Robo extends Emergencia implements Responder{
    
    public Robo(Tipo tipo, String ubicacion, Integer nivelGravedad, Integer tiempoRespuesta) {
        super(tipo, ubicacion, nivelGravedad, tiempoRespuesta);
    
    }

    @Override
    public void atenderEmergencia() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void evaluarEstado(String estado) {
        System.out.println(estado);
    }
    
}
