package com.morpheus.morpheus.Elementos;

import com.morpheus.morpheus.Reflection.Reflexion;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morpheus on 28/08/2018.
 */

public abstract class Lista
{
    //Permite checar que todos los elementos de una lista estén instanciados de la misma clase
    public static boolean verificacionDeObjetos(List<?> elements)
    {
        boolean verificacion = true;
        Class clase = Reflexion.getInstanceClass(elements.get(0));

        for(int i = 1; i < elements.size(); i++)
        {
            if(!Reflexion.getInstanceClass(elements.get(i)).equals(clase))
            {
                verificacion = false;
                break;
            }
        }

        return verificacion;
    }

    //Metodo que regresa una lista de objetos a partir de una lista de objetos que los contiene y el nombre de su metodo
    public static List<Object> getListObjects(List<?> values, String nameMethodGet) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        List<Object> lista = new ArrayList<>();

        for(Object object : values)
            lista.add(Reflexion.getMethodValue(object, nameMethodGet, null, null));

        return lista;
    }
}
