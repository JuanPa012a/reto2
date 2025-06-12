package com.devsenior.pablo.Util;

public enum NivelGravedad{
    ALTO, INTERMEDIO, BAJO;

    public static void enlistar(){
        System.out.println("====================");
        for (int i = 0; i < NivelGravedad.values().length; i++) {
            System.out.println(i+1 + " - " + NivelGravedad.values()[i]);
        }
        System.out.println("====================");
    }

}