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
    //Metodo que regresa una lista de objetos a partir de una lista de objetos que los contiene y el nombre de su metodo
    public static List<?> getListObjects(List<?> values, String nameMethodGet) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        List<Object> lista = new ArrayList<>();

        Object object = values.get(0);
        lista.add(Reflexion.getMethodValue(object, nameMethodGet, null, null));

        return lista;
    }
}
