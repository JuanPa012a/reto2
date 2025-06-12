package com.devsenior.pablo.service;

import java.util.LinkedList;

import com.devsenior.pablo.model.Vehiculo;

public interface EmergenciaService {
    String getId();
    
    Integer getPersonal();

    Long getCombustible(String idVehiculo);

    LinkedList<Vehiculo> getAllVehiculos();

    LinkedList<Vehiculo> getVehiculosDisponbles();

    Integer getPersonalDisponible();

    void agregarPersonal(Integer cantidad);

    Boolean estadoServicio();

    void agergarVehiculo(Vehiculo vehiculo);

    void tanquearVehiculo(Vehiculo vehiculo, Long cantidad);

    Boolean combustibleConsumido(Vehiculo vehiculo, Long cantidad);

    Boolean enviarPersonal(Integer cantidad);

    Boolean enviarVehiculo();

    void devolverVehiculo();

    void devolverPersonal();


}
