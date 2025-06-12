package com.devsenior.pablo.model;

public class Vehiculo {
    private final String id;
    private final String nombre;
    private final String placa;
    private Long combustible;
    
    
    
    public Vehiculo(String id, String nombre, String placa, Long combustible) {
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



    public String getId() {
        return id;
    }



    @Override
    public String toString() {
        return "Vehiculo [id=" + id + ", nombre=" + nombre + ", placa=" + placa + ", combustible=" + combustible + "]";
    }
    
    

}
