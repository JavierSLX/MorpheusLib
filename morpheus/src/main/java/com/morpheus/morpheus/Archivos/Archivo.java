package com.morpheus.morpheus.Archivos;

import android.os.Build;
import android.os.StrictMode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Morpheus on 31/08/2018.
 */

public class Archivo
{
    public Archivo()
    {
        quitarSeguridadVersion();
    }

    private void quitarSeguridadVersion()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            try
            {
                Method method = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                method.invoke(null);
            } catch (NoSuchMethodException | InvocationTargetException| IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }
}
