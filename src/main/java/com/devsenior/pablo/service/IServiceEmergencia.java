package com.devsenior.pablo.service;

import java.util.LinkedList;

import com.devsenior.pablo.Util.FactoryEntidades;
import static com.devsenior.pablo.Util.IdGenerator.generateIdVehiculo;
import com.devsenior.pablo.Util.Tipo;
import com.devsenior.pablo.model.Emergencia;
import com.devsenior.pablo.model.Vehiculo;
import static com.devsenior.pablo.view.EmergenciasView.entrada;

public abstract class IServiceEmergencia implements EmergenciaService, Responder {
    // Recursos
    private String id;
    private Integer personal;
    private final LinkedList<Vehiculo> vehiculos;
    private final  LinkedList<Vehiculo> vehiculosEnviados;
    private Integer personalEnviados;
    private final FactoryEntidades factoryEntidades;

    public IServiceEmergencia() {
        this.vehiculos = new LinkedList<>();
        this.vehiculosEnviados = new LinkedList<>();
        entidades = new LinkedList<>();
        this.factoryEntidades = new FactoryEntidades();
    }

    // Enlistar Entidades
    private final LinkedList<IServiceEmergencia> entidades;
    // Obtener emergencia
    protected Emergencia emergencia;

    public IServiceEmergencia(String id, Integer personal) {
        this.vehiculos = new LinkedList<>();
        this.id = id;
        this.personal = personal;
        entidades = new LinkedList<>();
        this.factoryEntidades = new FactoryEntidades();
        this.vehiculosEnviados = new LinkedList<>();
    }

    // Logica para los recursos
    @Override
    public String getId() {
        return id;
    }

    @Override
    public Integer getPersonal() {
        return personal;
    }

    @Override
    public Long getCombustible(String idVehiculo) {
        var vehiculo = getVehiculoById(idVehiculo);
        return vehiculo.getCombustible();
    }

    @Override
    public LinkedList<Vehiculo> getAllVehiculos() {
        return vehiculos;
    }

    @Override
    public LinkedList<Vehiculo> getVehiculosDisponbles() {
        var vehiculosDisponibles = new LinkedList<Vehiculo>();
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getCombustible() > 0) {
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
        return !(personal > 1 || vehiculos.isEmpty());
    }

    @Override
    public void agergarVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
    }

    @Override
    public Boolean enviarPersonal(Integer cantidad) {
        if (personal < 1) {
            System.out.println("No hay personal disponible para enviar");
            return false;
        }
        personal -= cantidad;
        personalEnviados = cantidad;
        return true;

    }

    @Override
    public Boolean enviarVehiculo() {
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos disponibles para enviar...");
            System.out.println("Que decision vas a tomar?\n1)Agregar Vehiculo\n2)Enviar sin vehiculo");
            var opcion = entrada.nextInt();
            if (opcion == 1) {
                System.out.println("Ingresa el nombre del vehiculo: ");
                String nombre = entrada.next();
                System.out.println("Ingresa la placa del vehiculo: ");
                String placa = entrada.next();
                System.out.println("¿Con cuanta gasolina viene el vehiculo?");
                Long combustible = entrada.nextLong();
                agergarVehiculo(new Vehiculo(generateIdVehiculo(emergencia.getTipo()),
                        nombre, placa.toUpperCase(), combustible));
            }
            return opcion == 2;
        }
        vehiculosParaEnviar();
        for (Vehiculo vehiculo : vehiculosEnviados) {
            vehiculos.remove(vehiculo);
        }
        return true;

    }

    public Vehiculo obtenerVehiculo(String placa) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getPlaca().equals(placa.toUpperCase())) {
                return vehiculo;
            }
        }
        return null;
    }

    private void vehiculosParaEnviar() {

        while (true) {
            vehiculos.stream()
            .forEach(System.out::println);
            System.out.println("Ingresa la placa del vehiculo que vas a enviar: ");
            String placa = entrada.next();
            var vehiculo = obtenerVehiculo(placa);
            if(vehiculo == null){
                System.err.println("El vehiculo ingresado es incorrecto");
            }else{
                while (true) {
                    if (vehiculo.getCombustible() <= 0) {
                        System.out.println("Este vehiculo necesita gasolina: " + vehiculo.getCombustible());
                        System.out.println("Cuanta gasolina va a tanquear?");
                        Long gasolina = entrada.nextLong();
                        tanquearVehiculo(vehiculo, gasolina);
                    }
                    System.out.println("Cuanta gasolina va a consumir para esta emergencia?");
                    Long gasolina = entrada.nextLong();
                    if (combustibleConsumido(vehiculo, gasolina)) {
                        vehiculosEnviados.add(vehiculo);
                        vehiculos.remove(vehiculo);
                        break;
                    }
                    System.out.println();
                }
            }

            System.out.println("Envias otro vehiculo?\n1) = Si\n2) = No:");
            String op = entrada.next();
            if (op.contains("2")) {
                break;
            }
        }

    }

    private void vehiculosParaDevolver() {
        for (Vehiculo vehiculo : vehiculosEnviados) {
            vehiculos.add(vehiculo);
        }
        vehiculosEnviados.clear();
    }

    @Override
    public void devolverVehiculo() {
        vehiculosParaDevolver();
    }

    @Override
    public void devolverPersonal() {
        personal += personalEnviados;
        personalEnviados = 0;
    }

    @Override
    public void tanquearVehiculo(Vehiculo vehiculo, Long cantidad) {
        vehiculo.setCombustible(
                vehiculo.getCombustible() + cantidad);
    }

    @Override
    public Boolean combustibleConsumido(Vehiculo vehiculo, Long cantidad) {
        if (cantidad > vehiculo.getCombustible()) {
            System.out.println("Esa cantidad supera la cantidad actual!");
            return false;
        }
        vehiculo.setCombustible(
                vehiculo.getCombustible() - cantidad);
        return true;
    }

    private Vehiculo getVehiculoById(String id) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getId().equals(id)) {
                return vehiculo;
            }
        }
        return null;

    }

    // Logica para las entidades
    public IServiceEmergencia agregarEntidad(Integer personal, Tipo tipo) {
        if (personal < 1) {
            System.out.println("El personal ingresado no cumple con la cantidad minima");
            return null;
        } else {
            var nuevaEntidad = factoryEntidades.crearEntidad(tipo, personal);
            entidades.add(nuevaEntidad);
            return nuevaEntidad;
        }
    }

    public LinkedList<IServiceEmergencia> getAllEntidades() {
        return entidades;
    }

    public IServiceEmergencia getEntidadById(String id) {
        for (IServiceEmergencia iServiceEmergencia : entidades) {
            if (iServiceEmergencia.getId().equals(id)) {
                return iServiceEmergencia;
            }
        }
        return null;
    }

    public FactoryEntidades getFactoryEntidades() {
        return factoryEntidades;
    }

    public Emergencia getEmergencia() {
        return emergencia;
    }

}
