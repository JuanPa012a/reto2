package com.devsenior.pablo.model;

/**
 *
 * @author Juanpa
 */
public class Emergencia {
    private final Tipo tipo;
    private final String ubicacion;
    private Integer nivelGravedad;
    private Integer tiempoRespuesta;
    private Long tiempoInicial;
    private Long tiempoFinal;

    
    public Emergencia(Tipo tipo, String ubicacion, Integer nivelGravedad, Integer tiempoRespuesta) {
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.nivelGravedad = nivelGravedad;
        this.tiempoRespuesta = tiempoRespuesta;
    }

    
    public Tipo getTipo() {
        return tipo;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }
   
    public Integer getNivelGravedad() {
        return nivelGravedad;
    }
    public void setNivelGravedad(Integer nivelGravedad) {
        this.nivelGravedad = nivelGravedad;
    }
    public Integer getTiempoRespuesta() {
        return tiempoRespuesta;
    }
    public void setTiempoRespuesta(Integer tiempoRespuesta) {
        this.tiempoRespuesta = tiempoRespuesta;
    }


    public Long getTiempoInicial() {
        return tiempoInicial;
    }


    public void setTiempoInicial(Long tiempoInicial) {
        this.tiempoInicial = tiempoInicial;
    }


    public Long getTiempoFinal() {
        return tiempoFinal;
    }


    public void setTiempoFinal(Long tiempoFinal) {
        this.tiempoFinal = tiempoFinal;
    }

    
    
}
