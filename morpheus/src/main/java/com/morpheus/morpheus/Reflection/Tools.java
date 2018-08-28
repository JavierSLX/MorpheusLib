package com.morpheus.morpheus.Reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morpheus on 28/08/2018.
 */

public abstract class Tools
{
    //Metodo que regresa una lista de strings a partir de una lista de objetos y el nombre de su metodo
    public static List<String> getListString(List<?> values, String nameMethodGet) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        List<String> lista = new ArrayList<>();

        Object object = values.get(0);
        lista.add((String)Reflexion.getMethodValue(object, nameMethodGet, null, null));

        return lista;
    }

    //Permite verificar si el metodo existe dentro de los metodos instanciados del objeto
    public static boolean verificarMetodoInstanciado(Method[] methods, String nameMethod)
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
