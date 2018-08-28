package com.morpheus.morpheus.Elementos;

import com.morpheus.morpheus.Reflection.Reflexion;

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
        Class clase = Reflexion.getClassObject(elements.get(0));

        for(int i = 1; i < elements.size(); i++)
        {
            if(!Reflexion.getClassObject(elements.get(i)).equals(clase))
            {
                verificacion = false;
                break;
            }
        }

        return verificacion;
    }
}
