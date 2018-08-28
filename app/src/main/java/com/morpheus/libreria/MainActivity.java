package com.morpheus.libreria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.morpheus.morpheus.Elementos.Lista;
import com.morpheus.morpheus.Reflection.Reflexion;
import com.morpheus.morpheus.Reflection.Tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Usuario javier = new Usuario(1, "Javier Serrano", 30, "4612578406");
        Usuario meli = new Usuario(2, "Melissa", 23, "Nada");
        Cliente conocido = new Cliente(1, "Estandar", "estandar@estandar.com");
        int valor = 10;
        Integer i = 9;
        Double d = 2d;
        float f = 5f;

        List<Object> usuarios = new ArrayList<>();
        usuarios.add(javier);
        usuarios.add(conocido);
        usuarios.add(meli);
        usuarios.add("Hola");
        usuarios.add(valor);
        usuarios.add(d);
        usuarios.add(f);

        Toast.makeText(this, Reflexion.getInstanceClassSimpleName(i), Toast.LENGTH_SHORT).show();
    }
}
