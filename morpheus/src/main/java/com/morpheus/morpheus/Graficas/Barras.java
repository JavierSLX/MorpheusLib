package com.morpheus.morpheus.Graficas;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morpheus on 27/08/2018.
 */

public class Barras
{
    private Object object;

    public Barras(Object object)
    {
        this.object = object;
    }

    public String tipoObjeto()
    {
        return object.getClass().getName();
    }

    public List<String> nombresMetodos()
    {
        Method[] methods = object.getClass().getDeclaredMethods();
        List<String> lista = new ArrayList<>();
        for(Method method : methods)
            lista.add(method.getName());

        return lista;
    }

    public String obtenerCadenaNombre() throws Exception
    {
        //Si no recibe elementos no se le definen
        Method getNombre = object.getClass().getDeclaredMethod("getNombre");

        //Se le pasan los elementos
        return (String)getNombre.invoke(object);
    }
}
