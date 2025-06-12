package com.devsenior.pablo.controller;

import java.util.LinkedList;

import static com.devsenior.pablo.Util.IdGenerator.generateIdVehiculo;
import com.devsenior.pablo.Util.NivelGravedad;
import com.devsenior.pablo.Util.Tipo;
import com.devsenior.pablo.Util.Ubicacion;
import com.devsenior.pablo.controller.observer.EmergenciaSubject;
import com.devsenior.pablo.controller.observer.EntidadObserver;
import com.devsenior.pablo.controller.observer.ObserverEmergencia;
import com.devsenior.pablo.controller.strategy.StrategyCercania;
import com.devsenior.pablo.controller.strategy.StrategyGravedad;
import com.devsenior.pablo.controller.strategy.StrategyPrioridad;
import com.devsenior.pablo.model.Emergencia;
import com.devsenior.pablo.model.ServiciosEmergencia.Policia;
import com.devsenior.pablo.model.Vehiculo;
import com.devsenior.pablo.service.IServiceEmergencia;
import static com.devsenior.pablo.view.EmergenciasView.entrada;

public class EmergengiaController {

    private final IServiceEmergencia servicio;

    private final EmergenciaSubject observer;

    private final StrategyPrioridad prioridadCercania;
    private final StrategyPrioridad prioridadGravedad;
    

    public EmergengiaController() {
        this.servicio = new Policia();
        this.observer = new EmergenciaSubject();
        this.prioridadCercania = new StrategyCercania();
        this.prioridadGravedad= new StrategyGravedad();
    }

    public void agregarEntidad(Integer personal, Tipo tipo) {
        var entity = servicio.agregarEntidad(personal, tipo);
        if (entity != null) {
            observer.agregarObserver(new EntidadObserver(entity));
            while (true) {
                agergarVehiculo(tipo, entity);
                System.out.println("¿Vas agregar otro vehiculo?\n1)Si\n2)No");
                Integer op = entrada.nextInt();
                if(op==2){
                    break;
                }
            }
        }
    }

    public void agergarVehiculo(Tipo tipo, IServiceEmergencia entidad ) {
        System.out.println("Ingresa el nombre del vehiculo: ");
        String nombre = entrada.next();
        System.out.println("Ingresa la placa del vehiculo: ");
        String placa = entrada.next();
        System.out.println("¿Con cuanta gasolina viene el vehiculo?");
        Long combustible = entrada.nextLong();
        entidad.agergarVehiculo(new Vehiculo(generateIdVehiculo(tipo),
                nombre, placa.toUpperCase(), combustible));
    }

    public void agregarObserver(ObserverEmergencia observador) {
        observer.agregarObserver(observador);
    }

    public void eliminarObserver(String id) {
        
            var entidad = obtenerObserver(id);
            if (entidad.getEntidad().getId().equals(id)) {
                observer.eliminarObserver(entidad);
            }
        

    }
    public EntidadObserver obtenerObserver(String id){
        for (ObserverEmergencia subject : observer.getObservadores()) {
            var entidad = (EntidadObserver) subject;
            if (entidad.getEntidad().getId().equals(id)) {
                return entidad;
            }
        }
        return null;
    }

    public void crearEmergencia(Tipo tipo, String ubicacion, NivelGravedad nivelGravedad, Long tiempoRespuesta) {
        var emergencia = servicio.getFactoryEntidades().crearEmergencia(tipo, ubicacion, nivelGravedad,
                tiempoRespuesta);
        observer.notificarObservadores(emergencia);
    }

    public void finalizarAtencion(IServiceEmergencia entidad) {
        entidad.getEmergencia().finalizarAtencion();
        entidad.devolverPersonal();
        entidad.devolverVehiculo();
        entidad.getEmergencia().calcularTiempoAtencion();
        observer.eliminarEmergenciaPendiente(entidad.getEmergencia());
        System.out.println("El tiempo estimado de la emergencia fue por: "+entidad.getEmergencia().getTiempoRespuesta());
    }

    public void enListarEntidades() {
        System.out.println("====================");
        for (IServiceEmergencia entity : servicio.getAllEntidades()) {
            System.out.println("ID: " + entity.getId() + " Entidad: " + entity.getClass().getSimpleName());
        }
        System.out.println("====================");
    }

    public void enlistarEmergenciasPendientes(){
        System.out.println("====================");
        if(observer.getEmergenciasPendientes().isEmpty()){
            System.out.println("No hay emergencias pendientes por mostrar");
        }
        for (Emergencia emergencia : observer.getEmergenciasPendientes()) {
            System.out.println(emergencia);
        }
        System.out.println("====================");
    }

    public LinkedList<IServiceEmergencia> obtenerEntidad() {
        return servicio.getAllEntidades();
    }

    public IServiceEmergencia obtenerEntidadPorId(String id) {
        return servicio.getEntidadById(id);
    }

    public Integer calcularCercania(Emergencia emergencia, Ubicacion ubicacionActual) {
        return ((StrategyCercania) prioridadCercania).calcularPrioridad(emergencia, ubicacionActual);
    }

    public Integer calcularGravedad(Emergencia emergencia) {
        return ((StrategyGravedad) prioridadGravedad).calcularPrioridad(emergencia, null);
    }


    
}
