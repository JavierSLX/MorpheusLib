package com.morpheus.morpheus.Herramientas;

import java.util.List;

/**
 * Created by Morpheus on 30/08/2018.
 */

public class Math
{
    //Metodo que saca el promedio de los valores de una lista
    public static double calculoPorcentaje(List<?> values, double value)
    {
        double suma = 0;
        for(int i = 0; i < values.size(); i++)
            suma += ((Number)values.get(i)).doubleValue();

        return (value / suma) * 100;
    }
}
