package com.devsenior.pablo.model;

public class Vehiculo {
    private final Long id;
    private final String nombre;
    private final String placa;
    private Long combustible;
    
    
    
    public Vehiculo(Long id, String nombre, String placa, Long combustible) {
        this.id = id;
        this.nombre = nombre;
        this.combustible = combustible;
        this.placa = placa;
    }

    
    
    public String getNombre() {
        return nombre;
    }
    
    public Long getCombustible() {
        return combustible;
    }

    public void setCombustible(Long combustible) {
        this.combustible = combustible;
    }


    public String getPlaca() {
        return placa;
    }



    public Long getId() {
        return id;
    }
    
    
}
