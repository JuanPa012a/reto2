package com.devsenior.pablo.view;

import java.util.Scanner;

import com.devsenior.pablo.Util.NivelGravedad;
import com.devsenior.pablo.Util.Tipo;
import com.devsenior.pablo.Util.Ubicacion;
import com.devsenior.pablo.controller.EmergengiaController;
import com.devsenior.pablo.service.IServiceEmergencia;

public class EmergenciasView {

    private final EmergengiaController controlador = new EmergengiaController();
    private final Formularios formularios = new Formularios();
    public  final static Scanner entrada = new Scanner(System.in);

    public void menuprincipal() {
        while (true) {
            System.out.println("====================");
            System.out.println("!--- MENU PRINCIPAL ---!");
            Menus.enlistar();
            System.out.println("Selecciona la opcion que deseas");
            Integer menuInteger = entrada.nextInt() - 1;
            if(menuInteger <0){
                System.out.println("Dato invalido");
                continue;
            }
            switch (Menus.menuprincipal.values()[menuInteger]) {
                case AGREGAR_EMERGENCIA -> {
                    formularios.formularioAgregarEmergencia();
                }
                case AGREGAR_ENTIDADES -> {
                    formularios.formularioAgregarEntidades();
                }

                case ELIMINAR_ENTIDAD -> {
                    formularios.formularioEliminarEntidad();
                }
                case REVISAR_EMERGENCIA -> {
                    formularios.formularioRevisarEmergencia();
                }
                case REVISAR_ENTIDAD -> {
                    formularios.formularioRevisarEntidad();
                }
                case EMERGENCIAS_PENDIENTES -> {
                    formularios.formularioEmergenciasPendientes();
                }

                case SALIDA -> {
                    entrada.close();
                    System.out.println("=========================================");
                    System.out.println("MUCHAS GRACIAS POR USAR NUESTRO SERVICIO");
                    System.exit(0);
                }
                default ->
                    System.out.println("Opcion invalida");
            }

        }
    }

    private class Menus {

        public enum menuprincipal {
            AGREGAR_ENTIDADES,
            AGREGAR_EMERGENCIA,
            ELIMINAR_ENTIDAD,
            REVISAR_EMERGENCIA,
            REVISAR_ENTIDAD,
            EMERGENCIAS_PENDIENTES,
            SALIDA
        }

        public enum subMenuEmergencia {
            CONSULTAR_CERCANIA,
            CONSULTAR_GRAVEDAD_ACCIDENTE,
            FINALIZAR_EMERGENCIA,
            VOLVER
        }
        public enum menuEntidad{
            DISPONIBILIDAD,
            PERSONAL_DISPONIBLE,
            VEHICULOS,
            VEHICULOS_DISPONIBLES,
            VOLVER
        }
        public enum menuVehiculo{
            TANQUEAR,
            VOLVER
        }

        public static void enlistar() {
            System.out.println("====================");
            for (int i = 0; i < menuprincipal.values().length; i++) {
                System.out.println(i + 1 + " - " + menuprincipal.values()[i]);
            }
            System.out.println("====================");
        }

        public static void enlistarsubMenuEmergencia() {
            System.out.println("====================");
            for (int i = 0; i < subMenuEmergencia.values().length; i++) {
                System.out.println(i + 1 + " - " + subMenuEmergencia.values()[i]);
            }
            System.out.println("====================");
        }
        public static void enlistarsubMenuEntidades() {
            System.out.println("====================");
            for (int i = 0; i < menuEntidad.values().length; i++) {
                System.out.println(i + 1 + " - " + menuEntidad.values()[i]);
            }
            System.out.println("====================");
        }
        public static void enlistarsubMenuVehiculos() {
            System.out.println("====================");
            for (int i = 0; i < menuVehiculo.values().length; i++) {
                System.out.println(i + 1 + " - " + menuVehiculo.values()[i]);
            }
            System.out.println("====================");
        }
    }

    private class Formularios {
        private IServiceEmergencia formularioObtenerEntidad(String message) {
          
                controlador.enListarEntidades();
                System.out.println(message);
                String id = entrada.next().toUpperCase();
                return controlador.obtenerEntidadPorId(id);
            
        }

        private Tipo formularioObtenerTipoEmergencia(String message) {

                Tipo.enlistar();
                System.out.println(message);
                Integer opcion = entrada.nextInt() - 1;
                if(opcion<0){
                    return null;
                }
                return Tipo.values()[opcion];
            
        }
        
        private void formularioTanquearVehiculo(IServiceEmergencia entidad){
            System.out.println("Ingresa la placa del vehiculo: ");
            String placa = entrada.next();
            var vehiculo = entidad.obtenerVehiculo(placa);
            System.out.println("¿Cuanto vas a tanquear?:");
            Long gasolina = entrada.nextLong();
            entidad.tanquearVehiculo(vehiculo, gasolina);
        }

        private Ubicacion formularioObtenerUbicacion(String message) {
            
                Ubicacion.enlistar();
                System.out.println(message);
                Integer getUbicacion = entrada.nextInt() - 1;
                if(getUbicacion<0){
                    return null;
                }
                return Ubicacion.values()[getUbicacion];
            
        }

        private NivelGravedad formularioObtenerNivelGravedad(String message) {
            
                NivelGravedad.enlistar();
                System.out.println(message);
                Integer getGravedad = entrada.nextInt() - 1;
                if(getGravedad<0){
                    return null;
                }
                return NivelGravedad.values()[getGravedad];
            
        }
        

        private void formularioAgregarEntidades() {
                System.out.println("====================");
                System.out.println("!--- FORMULARIO DE ENTIDAD ---! ");
                var tipo = formularioObtenerTipoEmergencia("Selecciona el tipo accidente\n(0 para salir):");
                if(tipo == null){return;}
                System.out.println("¿Cuanto personal obtendra la entidad?");
                Integer personal = entrada.nextInt();
                controlador.agregarEntidad(personal, tipo);
                System.out.println("CREADO CON ÉXITO!");
        }

        private void formularioAgregarEmergencia() {
            System.out.println("====================");
            System.out.println("UNA EMERGENCIA!!!!");
            // Iniciar tiempo de respuesta
            var tiempoRespuesta = System.currentTimeMillis();

            // Obtener tipo de emergencia
            var tipo = formularioObtenerTipoEmergencia("Selecciona el tipo accidente\n(0 para salir):");
            if(tipo == null){
                return;
            }

            // Obtener ubicacion
            var ubicacion = formularioObtenerUbicacion("¿Donde esta la ubicación de la emergencia?\n(0 para salir)");
            if(ubicacion == null){
                return;
            }
            // Obtener Nivel de gravedad
            var gravedad = formularios.formularioObtenerNivelGravedad("¿Que nivel de gravedad?\n(0 para salir)");
            if(gravedad == null){
                return;
            }
            // Enviar al controlador el objeto
            controlador.crearEmergencia(tipo,
                    ubicacion.name(),
                    gravedad, tiempoRespuesta);

        }

        private void formularioEliminarEntidad() {
            System.out.println("====================");
            System.out.println("ELIMINAR ENTIDAD");
            var entidad = formularioObtenerEntidad("Ingresa el ID de la entidad\n(0 para salir): ");
            if(entidad == null){
                return;
            }
            entidad.getAllEntidades().remove(entidad);
            controlador.eliminarObserver(entidad.getId());
            System.out.println("ELIMINADO CON ÉXITO!");

        }

        private void formularioRevisarEmergencia() {
           System.out.println("====================");
                while (true) {
                    System.out.println("REVISAR EMERGENCIA");
                    var entidad = formularios
                            .formularioObtenerEntidad("Ingresa el ID de la entidad que tiene la emergencia\n(0 para salir): ");
                    if(entidad == null){
                       break;
                    }
                        boolean estadox = true;
                        entidad.evaluarEstado();
                        while(estadox){
                    Menus.enlistarsubMenuEntidades();
                    System.out.println("Ingresa la opcion que deseas:");
                    Integer opcion = entrada.nextInt() - 1;
                    switch (Menus.menuEntidad.values()[opcion]) {
                        case DISPONIBILIDAD -> {
                            String message = !entidad.estadoServicio()?"Estamos listos para otra emergencia"
                            :"Nos encontramos en otras misiones, pronto los contactamos!";
                         System.out.println(message);
                        }
                        case PERSONAL_DISPONIBLE -> {
                            System.out.println("Hay: "+entidad.getPersonal() +
                            (entidad.getPersonal()==1 ? " personal disponible" :
                            " personales disponibles"));
                        }
                        case VEHICULOS -> {
                            boolean estado = true;
                            while (estado) { 
                                if(entidad.getAllVehiculos().isEmpty()){
                                    System.out.println("No hay vehiculos en el momento");
                                    break;
                                }
                            entidad.getAllVehiculos().stream()
                            .forEach(System.out::println);
                            System.out.println("¿Cual es tu siguiente acción?");
                            Menus.enlistarsubMenuVehiculos();
                            opcion = entrada.nextInt() -1;
                            switch (Menus.menuVehiculo.values()[opcion]) {
                                case TANQUEAR -> {
                                    formularioTanquearVehiculo(entidad);
                                    }
                                case VOLVER -> {
                                    estado = false;
                                    break;
                                    }
                            }
                            }
                        
                        }
                        case VEHICULOS_DISPONIBLES -> {
                            if(entidad.getVehiculosDisponbles().isEmpty()){
                                System.out.println("No hay vehiculos disponibles");
                            }
                            entidad.getVehiculosDisponbles().stream()
                            .forEach(System.out::println);
                        } 
                        case VOLVER -> {
                            estadox = false;
                            break;
                        }
                        default -> System.err.println("Opcion invaldia");
                    }
                }
                
                break;
            }

            
        }

        private void formularioRevisarEntidad() {
            System.out.println("====================");
                while (true) {
                    System.out.println("REVISAR ENTIDAD");
                    var entidad = formularioObtenerEntidad("Ingresa el ID de la entidad que tiene la emergencia\n(0 para salir): ");
                    if(entidad == null){
                        break;
                    }
                    entidad.evaluarEstado();
                    Boolean estado = true;
                    while (estado) { 
                    Menus.enlistarsubMenuEmergencia();
                    System.out.println("Ingresa la opcion que deseas:");
                    Integer opcion = entrada.nextInt() - 1;
                    switch (Menus.subMenuEmergencia.values()[opcion]) {
                        case CONSULTAR_CERCANIA -> {
                            var ubicacion = formularios.formularioObtenerUbicacion("En que ubicacion se encuentra?");
                            var cercania = controlador.calcularCercania(entidad.getEmergencia(),
                                    ubicacion);
                            System.out.println("Estas a " + cercania + " km de distancia");
                        }
                        case CONSULTAR_GRAVEDAD_ACCIDENTE -> {
                            var gravedad = controlador.calcularGravedad(entidad.getEmergencia());
                            System.out.println("El nivel de gravedad es del " + gravedad + "%");
                        }
                        case FINALIZAR_EMERGENCIA -> {
                            controlador.finalizarAtencion(entidad);
                            estado = false;
                            break;
                        }
                        case VOLVER -> {
                            estado = false;
                            break;
                        }
                        default -> System.err.println("Opcion invaldia");
                    }
                }
                break;
                }
        }
        private void formularioEmergenciasPendientes(){
            controlador.enlistarEmergenciasPendientes();
        }
    }
}
