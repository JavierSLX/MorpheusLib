package com.morpheus.morpheus.Reflection;

import java.lang.reflect.Method;

/**
 * Created by Morpheus on 28/08/2018.
 */

public abstract class Metodo
{
    //Permite verificar si el metodo existe dentro de los metodos instanciados del objeto
    public static boolean verificarExistenciaMetodoInstanciado(Method[] methods, String nameMethod)
    {
        boolean verificado = false;
        for(Method method : methods)
        {
            if(method.getName().equals(nameMethod))
            {
                verificado = true;
                break;
            }
        }

        return verificado;
    }
}
