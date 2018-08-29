package com.morpheus.morpheus.Reflection;

import com.morpheus.morpheus.Excepciones.GraficaException;

/**
 * Created by Morpheus on 28/08/2018.
 */

public abstract class Objeto
{
    //Metodo que permite verificar si esta instanciado con una clase que se le especifica
    public static boolean verificarInstanciaObjeto(Object object, String nombreClase)
    {
        String clase = Reflexion.getInstanceClassSimpleName(object);
        return clase.equals(nombreClase);
    }

    //Comprueba si un objeto pertenece a alguna(s) instancia(s) que se le marque
    public static boolean verificarInstanciaObjeto(Object object, String... types)
    {
        for(String type : types)
            if (Reflexion.getInstanceClassSimpleName(object).equals(type))
                return true;

        return false;
    }
}
