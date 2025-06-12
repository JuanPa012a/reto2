package com.devsenior.pablo.model;

import com.devsenior.pablo.Util.NivelGravedad;
import com.devsenior.pablo.Util.Tipo;

/**
 *
 * @author Juanpa
 */
public class Emergencia {
    private final Tipo tipo;
    private final String ubicacion;
    private final NivelGravedad nivelGravedad;
    private Long tiempoRespuesta;
    private Long tiempoInicial;
    private Long tiempoFinal;
    private Boolean atendida;

    
    public Emergencia(Tipo tipo, String ubicacion, NivelGravedad nivelGravedad, Long tiempoRespuesta) {
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.nivelGravedad = nivelGravedad;
        this.tiempoRespuesta = tiempoRespuesta;
        atendida = false;
    }

    
    public Tipo getTipo() {
        return tipo;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }
   
    public NivelGravedad getNivelGravedad() {
        return nivelGravedad;
    }
    
    public String getTiempoRespuesta() {
  
        return  transformarTiempo(tiempoRespuesta);
    }
    public void setTiempoRespuesta(Long tiempoRespuesta) {
        this.tiempoRespuesta = tiempoRespuesta;
    }


    public String getTiempoInicial() {
        return transformarTiempo(tiempoInicial-tiempoRespuesta);
    }


    public void setTiempoInicial(Long tiempoInicial) {
        this.tiempoInicial = tiempoInicial;
    }


    public Long getTiempoFinal() {
        return tiempoFinal;
    }


    
    public void iniciarAtencion(){
        tiempoInicial = System.currentTimeMillis();
        atendida = true;
    }

    public void finalizarAtencion(){
        tiempoFinal = System.currentTimeMillis();
        atendida = false;
    }

    public void calcularTiempoAtencion(){
        tiempoRespuesta = tiempoFinal - tiempoInicial;
        
        
    }


    public Boolean getAtendida() {
        return atendida;
    }
    
    private String transformarTiempo(Long tiempo){
        long tiempoTotal = tiempo;
        long horas = tiempoTotal / 3600;
        long minutos = (tiempoTotal % 3600) / 60;
        long segundos = tiempoTotal % 60;

        return  horas + ":"+minutos+":"+segundos;
    }
    
    
    @Override
    public String toString() {
        return "|Emergencia: " + this.getClass().getSimpleName()
             + " | Tiempo estimado: "+ this.getTiempoInicial()+
             " | Ubicacion: "+this.getUbicacion()+
             " | Nivel de gravedad: "+this.getNivelGravedad();
    }
}
