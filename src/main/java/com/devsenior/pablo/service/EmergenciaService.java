package com.devsenior.pablo.service;

import java.util.LinkedList;

import com.devsenior.pablo.model.Vehiculo;

public interface EmergenciaService {
    String getId();
    
    Integer getPersonal();

    Long getCombustible(Long idVehiculo);

    LinkedList<Vehiculo> getAllVehiculos();

    LinkedList<Vehiculo> getVehiculosDisponbles();

    Integer getPersonalDisponible();

    void agregarPersonal(Integer cantidad);

    Boolean estadoServicio();

    void agergarVehiculo(Vehiculo vehiculo);

    void enviarPersonal(Integer cantidad);

    void enviarVehiculo();


}
