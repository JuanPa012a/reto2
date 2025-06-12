package com.devsenior.pablo.Util;

import com.devsenior.pablo.model.Emergencia;
import com.devsenior.pablo.model.Emergencias.Accidente;
import com.devsenior.pablo.model.Emergencias.Incendio;
import com.devsenior.pablo.model.Emergencias.Robo;
import com.devsenior.pablo.model.ServiciosEmergencia.Ambulancia;
import com.devsenior.pablo.model.ServiciosEmergencia.Bombero;
import com.devsenior.pablo.model.ServiciosEmergencia.Policia;
import com.devsenior.pablo.service.IServiceEmergencia;

public class FactoryEntidades {
    public Emergencia crearEmergencia(Tipo tipo, String ubicacion, NivelGravedad nivelGravedad,
    Long tiempoRespuesta){
        switch (tipo){
            case INCENDIO -> {
                return new Incendio(tipo, ubicacion, nivelGravedad, tiempoRespuesta);
            }
            case MEDICA ->  {
                return new Accidente(tipo, ubicacion, nivelGravedad, tiempoRespuesta);
            }
            case SEGURIDAD -> {
                return new Robo(tipo, ubicacion, nivelGravedad, tiempoRespuesta);
            }

            default -> throw new AssertionError();
        }    
    }

    public IServiceEmergencia crearEntidad(Tipo tipo, Integer personal){
        String generatedId = IdGenerator.generateId(tipo);
        switch (tipo){
            case INCENDIO -> {
                return new Bombero(generatedId, personal);
            }
            case MEDICA ->  {
                return new Ambulancia(generatedId, personal);
            }
            case SEGURIDAD -> {
                return new Policia(generatedId, personal);
            }

            default -> throw new AssertionError();
        }    
    }

    public  IServiceEmergencia obtenerEntidad(IServiceEmergencia entidad){
        switch (entidad) {
            case Ambulancia ambulancia -> {
                return ambulancia;
            }
            case Bombero bombero -> {
                return bombero;
            }
            case Policia policia -> {
                return policia;
            }
            default -> throw new AssertionError();
        }
    }
    
}
