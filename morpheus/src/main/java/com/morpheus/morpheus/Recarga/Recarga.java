package com.morpheus.morpheus.Recarga;

import android.content.Context;

import com.morpheus.morpheus.Constants;
import com.morpheus.morpheus.Excepciones.RecargaException;
import com.morpheus.morpheus.WebService.OnResultElementListener;
import com.morpheus.morpheus.WebService.Peticion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Morpheus on 27/08/2018.
 */

public class Recarga
{
    private Context context;

    public Recarga(Context context)
    {
        this.context = context;
    }

    //Realiza la recarga
    public void realizarRecarga(String elemento, int tipo, int id, final OnResultElementListener<JSONObject> listener)
    {
        comprobarDatosRecarga(elemento, tipo, id, listener);

        String url = Constants.SERVER + Constants.DIR_RECARGA + "recargaNumero.php";
        Map<String, String> params = new HashMap<>();
        params.put("valor", elemento);
        params.put("tipo", String.valueOf(tipo));
        params.put("id", String.valueOf(id));

        Peticion.POST post = new Peticion.POST(context, url, params);
        post.getResponse(new Peticion.OnPeticionListener<String>()
        {
            @Override
            public void onSuccess(String respuesta)
            {
                JSONObject result;
                try
                {
                    result = new JSONObject(respuesta);
                    listener.onSuccess(result);
                } catch (JSONException e)
                {
                    e.printStackTrace();
                    listener.onSuccess(null);
                }
            }

            @Override
            public void onFailed(String error, int respuestaHTTP)
            {
                listener.onFailed(error, respuestaHTTP);
            }
        });
    }

    //Comprueba que los elementos no sean nulos o en 0
    private void comprobarDatosRecarga(String elemento, int tipo, int id, OnResultElementListener listener)
    {
        if(elemento == null)
            throw new RecargaException("Debe colocar un número o un ICCID");

        if(tipo < 1 || tipo > 3)
            throw new RecargaException("El tipo es incorrecto");

        if(id < 1)
            throw new RecargaException("El id debe de ser mayor a 0");

        if(listener == null)
            throw new RecargaException("El hilo no tiene interfaz de respuesta");
    }
}
