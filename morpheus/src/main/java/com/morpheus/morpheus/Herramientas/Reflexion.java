package com.morpheus.morpheus.Herramientas;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Morpheus on 27/08/2018.
 */

public class Reflexion
{
    private Object object;

    public Reflexion(Object object)
    {
        this.object = object;
    }

    public Object getObject()
    {
        return object;
    }

    public void setObject(Object object)
    {
        this.object = object;
    }

    //Obtiene la clase de la que está instanciado el objeto
    public Class getClassObject()
    {
        return object.getClass();
    }

    //Obtiene el nombre de la clase de la que está instanciado el objeto
    public String getInstanceObject()
    {
        return getClassObject().getName();
    }

    //Obtiene el valor de un metodo que contiene ese objeto
    public Object methodValue(String nameMethod, Class[] parametersMethod, Object[] parametersValues) throws Exception
    {
        Method method;

        if(parametersMethod == null)
        {
            method = object.getClass().getDeclaredMethod(nameMethod);
            return method.invoke(object);
        }
        else
        {
            method = object.getClass().getDeclaredMethod(nameMethod, parametersMethod);
            return method.invoke(object, parametersValues);
        }
        // Castea a un elemento indefinido return objectReturn.cast(method.invoke(object));
    }

    //Obtiene los parametros de un metodo para funcionar
    public Class[] getParametersMethod(Method method)
    {
        return method.getParameterTypes();
    }

    //Obtiene el tipo de dato que regresa un elemento
    public Class getReturnTypeMethod(Method method)
    {
        return method.getReturnType();
    }
}
