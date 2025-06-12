package com.devsenior.pablo.Util;

public enum Tipo {
    MEDICA,
    INCENDIO,
    SEGURIDAD;

    public static void enlistar(){
        System.out.println("====================");
        for (int i = 0; i < Tipo.values().length; i++) {
            System.out.println((i + 1) + " - " + Tipo.values()[i]);
        }
        System.out.println("====================");
    }

    
}
