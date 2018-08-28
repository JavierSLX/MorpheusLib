package com.morpheus.libreria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.morpheus.morpheus.Graficas.Barras;
import com.morpheus.morpheus.Herramientas.Reflexion;
import com.morpheus.morpheus.WebService.Peticion;

import java.util.List;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Reflexion reflexion = new Reflexion(new Usuario(1, "Javier Serrano", 30, "4612578406"));
        try
        {
            String nombre = (String)reflexion.methodValue("getNombre", null, null);
            Log.i("reflexion", nombre);

            reflexion.methodValue("setEdad", new Class[]{int.class}, new Object[]{31});
            int edad = (int)reflexion.methodValue("getEdad", null, null);
            Log.i("reflexion", String.valueOf(edad));

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
