/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.devsenior.pablo.model.Emergencias;

import com.devsenior.pablo.model.Emergencia;
import com.devsenior.pablo.model.Tipo;
import com.devsenior.pablo.service.Responder;

/**
 *
 * @author Juanpa
 */
public class Incendio extends Emergencia implements Responder{

    public Incendio(Tipo tipo, String ubicacion, Integer nivelGravedad, Integer tiempoRespuesta) {
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
