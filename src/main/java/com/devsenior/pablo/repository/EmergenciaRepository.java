package com.devsenior.pablo.repository;

import java.util.LinkedList;

import com.devsenior.pablo.model.Vehiculo;

public class EmergenciaRepository {
    private final LinkedList<Vehiculo> vehiculos;

    public EmergenciaRepository(LinkedList<Vehiculo> vehiculo) {
        this.vehiculos = vehiculo;
    }

    public void agregarVehiculo(Vehiculo vehiculo){
        vehiculos.add(vehiculo);
    }

    public void eliminarVehiculoByPlaca(String placa){
        var vehiculo = getVehiculoByPlaca(placa);
        vehiculos.remove(vehiculo);
    }

    public Vehiculo getVehiculoByPlaca(String placa) {
        for (Vehiculo vehiculo : vehiculos) {
            if(vehiculo.getPlaca().equalsIgnoreCase(placa)){
                return vehiculo;
            }
        }
        return null;
        
    }
    public Vehiculo getVehiculoById(Long id) {
        for (Vehiculo vehiculo : vehiculos) {
            if(vehiculo.getId().equals(id)){
                return vehiculo;
            }
        }
        return null;
        
    }

    public LinkedList<Vehiculo> getAll(){
        return vehiculos;
    }
    
}
