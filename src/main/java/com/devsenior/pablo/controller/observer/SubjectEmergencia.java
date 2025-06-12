package com.devsenior.pablo.controller.observer;

import com.devsenior.pablo.model.Emergencia;

public interface SubjectEmergencia {
    void agregarObserver(ObserverEmergencia emergencia);
    void eliminarObserver(ObserverEmergencia emergencia);
    void notificarObservadores(Emergencia emergencia);
}
