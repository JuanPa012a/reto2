package com.devsenior.pablo.controller.observer;

import com.devsenior.pablo.model.Emergencia;
import com.devsenior.pablo.service.IServiceEmergencia;
import static com.devsenior.pablo.view.EmergenciasView.entrada;

public class EntidadObserver implements ObserverEmergencia {
    private final IServiceEmergencia entidad;
    
    public EntidadObserver(IServiceEmergencia entidad) {
        this.entidad = entidad;
    }
    
    @Override
    public Boolean onNuevaEmergencia(Emergencia emergencia) {
            System.out.println("====================");
            System.out.println("¿Vas a atender a la emergencia " + entidad.getClass().getSimpleName()+ "?\n1)Si\n2)No");
            Integer opcion = entrada.nextInt();
            if(opcion == 2){
                return false;
            }
            entidad.atenderEmergencia(emergencia);   
            return true;
    }

    public IServiceEmergencia getEntidad() {
        return entidad;
    }
    
    
    
} 