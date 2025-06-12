package com.devsenior.pablo.model.ServiciosEmergencia;

import com.devsenior.pablo.model.Emergencia;
import com.devsenior.pablo.service.IServiceEmergencia;
import static com.devsenior.pablo.view.EmergenciasView.entrada;


/**
 *
 * @author Juanpa
 */
public class Policia extends IServiceEmergencia {

    public Policia(String id, Integer personal) {
        super(id, personal);
    }
    public Policia(){
        super();
    }
   

    @Override
    public void atenderEmergencia(Emergencia emeEnergencia) {
        System.out.println("Ya enviamos los policias!");
        System.out.println("Cuanto personal envia para esta emergencia?");
        Integer cantidad = entrada.nextInt();
        Boolean estado = false;
        while (!estado) {
            estado = enviarPersonal(cantidad);
        }
        estado = false;
        while (!estado) {
            estado = enviarVehiculo();
        }

        this.emergencia = emeEnergencia;
        emeEnergencia.iniciarAtencion();
    }

    @Override
    public void evaluarEstado() {
        if(emergencia == null){
            System.out.println("No hay emergencias registradas hasta el momento");
            return;
        }
        if(emergencia.getAtendida()){
            System.out.println("Todavía estamos tratando: " + emergencia.getClass().getSimpleName());
        }else{
            System.out.println("Ya la finalizamos con tiempo: " +  emergencia.getTiempoRespuesta());
        }
    }

}
