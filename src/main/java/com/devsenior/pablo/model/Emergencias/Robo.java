package com.devsenior.pablo.model.Emergencias;

import com.devsenior.pablo.Util.NivelGravedad;
import com.devsenior.pablo.Util.Tipo;
import com.devsenior.pablo.model.Emergencia;

public class Robo extends Emergencia {
    
    public Robo(Tipo tipo, String ubicacion, NivelGravedad nivelGravedad, Long tiempoRespuesta) {
        super(tipo, ubicacion, nivelGravedad, tiempoRespuesta);
    
    }

}
