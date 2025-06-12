/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.devsenior.pablo.model.Emergencias;

import com.devsenior.pablo.Util.NivelGravedad;
import com.devsenior.pablo.Util.Tipo;
import com.devsenior.pablo.model.Emergencia;

/**
 *
 * @author Juanpa
 */
public class Accidente extends Emergencia  {

    public Accidente(Tipo tipo, String ubicacion, NivelGravedad nivelGravedad, Long tiempoRespuesta) {
        super(tipo, ubicacion, nivelGravedad, tiempoRespuesta);
    }

   
    
}
