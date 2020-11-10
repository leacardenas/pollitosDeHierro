/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.apps.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author ychaves
 */
public class TimeFunctions {

    private TimeFunctions() {
        //constructor
    }

    
    /**
     * <p>
     * Convierte un número de segundos a una representación del tiempo
     * transcurrido de tipo mm:ss
     * </p>
     * <p>
     * <b>Ej:</b> el parámetro 350 lo representa como 05:50 donde quiere decir 5
     * minutos y 50 segundos
     * </p>
     *
     * @param seconds segundos a convertir
     * @return representación del tiempo transcurrido en formato mi:ss
     */
    public static String secondsToElapsedTime(long seconds) {
        return String.format("%02d:%02d",
                TimeUnit.SECONDS.toMinutes(seconds),
                seconds - TimeUnit.MINUTES.toSeconds(TimeUnit.SECONDS.toMinutes(seconds))
        );
    }

    /**
     * <p>
     * Obtiene la diferencia entre dos {@link java.util.Date}, lo retorna dentro
     * de un {@link java.util.Map}.
     * </p>
     * <p>
     * <b>Ej:</b> va a retornar un {@link java.util.Map} así:
     * <pre>{DAYS=325, HOURS=2, MINUTES=58, SECONDS=2, MILLISECONDS=0, MICROSECONDS=0, NANOSECONDS=0}</pre>
     *
     * Si desea obtener por ejemplo la cantidad de días, se debe ejecutar:
     * <pre>dateDiff(d1, d2).get(TimeUnit.DAYS)</pre>
     *
     * </p>
     *
     * @param date1 fecha inicial
     * @param date2 fecha final
     * @return {@link java.util.Map} que contiene {DAYS=0, HOURS=0, MINUTES=0,
     * SECONDS=0, MILLISECONDS=0, MICROSECONDS=0, NANOSECONDS=0}
     */
    public static Map<TimeUnit, Long> dateDiff(Date date1, Date date2) {
        long diffInMillies = date2.getTime() - date1.getTime();
        List<TimeUnit> units = new ArrayList<>(EnumSet.allOf(TimeUnit.class));
        Map<TimeUnit, Long> result = new LinkedHashMap<>();
        long milliesRest = diffInMillies;

        Collections.reverse(units);

        for (TimeUnit unit : units) {
            long diff = unit.convert(milliesRest, TimeUnit.MILLISECONDS);
            long diffInMilliesForUnit = unit.toMillis(diff);
            milliesRest = milliesRest - diffInMilliesForUnit;
            result.put(unit, diff);
        }

        return result;
    }

}
