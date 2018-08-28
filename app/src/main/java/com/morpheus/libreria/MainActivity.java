package com.morpheus.libreria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.morpheus.morpheus.Reflection.Reflexion;
import com.morpheus.morpheus.Reflection.Tools;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Usuario usuario = new Usuario(1, "Javier Serrano", 30, "4612578406");
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario);

        List<String> lista = null;
        try
        {
            lista = Tools.getListString(usuarios, "getNombres");
            Toast.makeText(this, lista.get(0), Toast.LENGTH_SHORT).show();
        } catch (NoSuchMethodException e)
        {
            e.printStackTrace();
            Toast.makeText(this, "No se encontró el método", Toast.LENGTH_SHORT).show();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }
}
