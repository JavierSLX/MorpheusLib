package com.morpheus.morpheus.Reflection;

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
}
