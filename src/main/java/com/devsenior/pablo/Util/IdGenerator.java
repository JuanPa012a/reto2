package com.devsenior.pablo.Util;

import java.time.Year;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private static final ConcurrentHashMap<Tipo, AtomicInteger> counters = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Tipo, AtomicInteger> counters2 = new ConcurrentHashMap<>();
    private static final String YEAR_SUFFIX = String.valueOf(Year.now().getValue());
    
    static {
        // Initialize counters for each type
        for (Tipo tipo : Tipo.values()) {
            counters.put(tipo, new AtomicInteger(1));
            counters2.put(tipo, new AtomicInteger(1));   
        }
    }
    
    public static String generateId(Tipo tipo) {
        if (!counters.containsKey(tipo)) {
            throw new IllegalArgumentException("Tipo no soportado: " + tipo);
        }
        String prefix = tipo.toString().substring(0, 3).toUpperCase();
        int sequence = counters.get(tipo).getAndIncrement();
        return String.format("%s%02d%s", prefix, sequence, YEAR_SUFFIX);
    }

    public static String generateIdVehiculo(Tipo tipo) {
        if (!counters2.containsKey(tipo)) {
            throw new IllegalArgumentException("Tipo no soportado: " + tipo);
        }
        
        int sequence = counters2.get(tipo).getAndIncrement();
        String prefix = tipo.toString().substring(0, 1).toUpperCase();
        return String.format("%s%02d%s", prefix, sequence, YEAR_SUFFIX);
    }
}
