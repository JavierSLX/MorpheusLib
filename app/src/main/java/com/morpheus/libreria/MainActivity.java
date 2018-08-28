package com.morpheus.libreria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.morpheus.morpheus.Graficas.Barras;

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

        BarChart chart = (BarChart)findViewById(R.id.grafica);

        Usuario javier = new Usuario(1, "Javier Serrano", 30, "4612578406");
        Usuario meli = new Usuario(2, "Melissa", 23, "Nada");
        Usuario conocido = new Usuario(3, "Estandar", 25, "estandar@estandar.com");

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(javier);
        usuarios.add(conocido);
        usuarios.add(meli);

        List<Double> valores = new ArrayList<>();
        valores.add(5.2);
        valores.add(10.1);
        valores.add(7.5);
        valores.add(7.2);

        Barras barras = new Barras(valores, usuarios);

        try
        {
            barras.createChart(chart, null, "getNombre");
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException  e)
        {
            e.printStackTrace();
        } 
    }
}
