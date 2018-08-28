package com.morpheus.libreria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.morpheus.morpheus.Elementos.Lista;
import com.morpheus.morpheus.Graficas.Barras;
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

        BarChart chart = (BarChart)findViewById(R.id.grafica);

        Usuario javier = new Usuario(1, "Javier Serrano", 30, "4612578406");
        Usuario meli = new Usuario(2, "Melissa", 23, "Nada");
        Usuario conocido = new Usuario(3, "Estandar", 25, "estandar@estandar.com");

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(javier);
        usuarios.add(conocido);
        usuarios.add(meli);

        Barras barras = new Barras(usuarios, usuarios);

        try
        {
            barras.createChart(chart, "getEdad", "getNombre");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
