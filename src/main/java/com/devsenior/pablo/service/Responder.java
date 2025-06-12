package com.devsenior.pablo.service;

import com.devsenior.pablo.model.Emergencia;

public interface  Responder {
    void atenderEmergencia(Emergencia emeEnergencia);
    void evaluarEstado();
}
