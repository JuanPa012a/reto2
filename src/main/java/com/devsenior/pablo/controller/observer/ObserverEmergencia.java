package com.devsenior.pablo.controller.observer;

import com.devsenior.pablo.model.Emergencia;

public interface ObserverEmergencia {
    Boolean onNuevaEmergencia(Emergencia emergencia);
}
