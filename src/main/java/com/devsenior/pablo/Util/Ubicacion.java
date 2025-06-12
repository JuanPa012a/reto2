package com.devsenior.pablo.Util;

public enum Ubicacion {
        NORTE(0, 90),      // Polo Norte
        SUR(0, -90),       // Polo Sur
        ESTE(90, 0),       // Este en el ecuador
        OESTE(-90, 0),     // Oeste en el ecuador
        NORESTE(45, 45),   // Entre Norte y Este
        SURESTE(45, -45),  // Entre Sur y Este  
        NOROESTE(-45, 45), // Entre Norte y Oeste
        SUROESTE(-45, -45);// Entre Sur y Oeste

        private final Integer x;
        private final Integer y;

     
      Ubicacion(int x, int y) {
                 this.x = x;
                 this.y = y;
              }
      
           public Integer getX(){
            return x;
           }
           public Integer getY(){
            return y;
           }
        public static void enlistar(){
            System.out.println("====================");
            for (int i = 0; i < Ubicacion.values().length; i++) {
                System.out.println(i+1 + " - " + Ubicacion.values()[i]);
            }
            System.out.println("====================");
        }
    }


        


