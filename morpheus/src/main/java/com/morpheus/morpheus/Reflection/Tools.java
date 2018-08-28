package com.morpheus.morpheus.Reflection;

import java.lang.reflect.InvocationTargetException;
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
        lista.add((String)Reflexion.methodValue(object, nameMethodGet, null, null));

        return lista;
    }
}
