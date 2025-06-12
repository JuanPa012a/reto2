package com.devsenior.pablo.controller.observer;

import java.util.ArrayList;
import java.util.List;

import com.devsenior.pablo.model.Emergencia;

public class EmergenciaSubject implements SubjectEmergencia {
    private final List<ObserverEmergencia> observadores;
    private final List<Emergencia> emergenciasPendientes;

    public EmergenciaSubject() {
        this.observadores = new ArrayList<>();
        this.emergenciasPendientes = new ArrayList<>();
    }

    @Override
    public void agregarObserver(ObserverEmergencia observador) {
        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    @Override
    public void eliminarObserver(ObserverEmergencia observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores(Emergencia emergencia) {
        for (ObserverEmergencia observador : observadores) {
            Boolean validar = observador.onNuevaEmergencia(emergencia);
            if(validar){
                emergenciasPendientes.add(emergencia);
            }
            
        }
    }

    public List<ObserverEmergencia> getObservadores() {
        return observadores;
    }

    public List<Emergencia> getEmergenciasPendientes() {
        return emergenciasPendientes;
    }

    public void eliminarEmergenciaPendiente(Emergencia emergencia){
        emergenciasPendientes.remove(emergencia);
    }

    
    
} 