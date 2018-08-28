package com.morpheus.morpheus.Reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Morpheus on 27/08/2018.
 */

public abstract class Reflexion
{
    //Obtiene la clase de la que está instanciado el objeto
    public static Class getClassObject(Object object)
    {
        return object.getClass();
    }

    //Obtiene el nombre de la clase de la que está instanciado el objeto
    public static String getInstanceObject(Object object)
    {
        return getClassObject(object).getName();
    }

    //Obtiene el valor de un metodo que contiene ese objeto
    public static Object methodValue(Object object, String nameMethod, Class[] parametersMethod, Object[] parametersValues) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
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
    public static Class[] getParametersMethod(Method method)
    {
        return method.getParameterTypes();
    }

    //Obtiene el tipo de dato que regresa un elemento
    public static Class getReturnTypeMethod(Method method)
    {
        return method.getReturnType();
    }
}
