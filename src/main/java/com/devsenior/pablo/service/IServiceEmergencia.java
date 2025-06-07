package com.devsenior.pablo.service;

import java.util.LinkedList;
import java.util.Scanner;

import com.devsenior.pablo.model.Vehiculo;
import com.devsenior.pablo.repository.EmergenciaRepository;

public class IServiceEmergencia implements EmergenciaService{
    private final String id;
    private Integer personal;
    private final EmergenciaRepository repository;
    private static LinkedList<Vehiculo> vehiculosEnviados;
    
    

    public IServiceEmergencia(String id, Integer personal, EmergenciaRepository repository) {
        this.id = id;
        this.personal = personal;
        this.repository = repository;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Integer getPersonal() {
        return personal;
    }

    @Override
    public Long getCombustible(Long idVehiculo) {
        var vehiculo = repository.getVehiculoById(idVehiculo);
        return vehiculo.getCombustible();
    }

    @Override
    public LinkedList<Vehiculo> getAllVehiculos() {
        return repository.getAll();
    }

    @Override
    public LinkedList<Vehiculo> getVehiculosDisponbles() {
        var vehiculos = repository.getAll();
        var vehiculosDisponibles = new LinkedList<Vehiculo>();
        for (Vehiculo vehiculo : vehiculos) {
            if(vehiculo.getCombustible()>0){
                vehiculosDisponibles.add(vehiculo);
            }
        }
        return vehiculosDisponibles;
    }

    @Override
    public Integer getPersonalDisponible() {
        return personal;
    }

    @Override
    public void agregarPersonal(Integer cantidad) {
        personal += cantidad;
    }

    @Override
    public Boolean estadoServicio() {
        return !(personal>1 || repository.getAll().isEmpty());
    }

    @Override
    public void agergarVehiculo(Vehiculo vehiculo) {
        repository.agregarVehiculo(vehiculo);
    }

    @Override
    public void enviarPersonal(Integer cantidad) {
        personal -= cantidad;
    }

    @Override
    public void enviarVehiculo() {
        var vehiculos = repository.getAll();
        vehiculosParaEnviar(vehiculos);
        for (Vehiculo vehiculo : vehiculosEnviados) {
                vehiculos.remove(vehiculo);
        }
    }
    

    private void vehiculosParaEnviar(LinkedList<Vehiculo> vehiculos){
        
        try (Scanner entrada = new Scanner(System.in)) {
            while(true){
            System.out.println("Ingresa la placa del vehiculo que vas a enviar: ");
            String placa = entrada.next();
            for (Vehiculo vehiculo : vehiculos) {
                if(vehiculo.getPlaca().equals(placa)){
                    vehiculosEnviados.add(vehiculo);
                }
            }
            System.out.println("Envias otro vehiculo?\nResponde 1 = Si, 2 = No:\n");
            String op = entrada.next();
            if(op.contains("2")){
                break;
            }
            }
        }
    }

    private void vehiculosParaDevolver(){
        for (Vehiculo vehiculo : vehiculosEnviados) {
                repository.agregarVehiculo(vehiculo);
        }
        vehiculosEnviados.clear();
    }

}
