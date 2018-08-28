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

        List<Integer> elementos = new ArrayList<>();
        elementos.add(12);
        elementos.add(17);
        elementos.add(22);
        elementos.add(27);

        List<String> etiquetas = new ArrayList<>();
        etiquetas.add("1");
        etiquetas.add("2");
        etiquetas.add("3");
        etiquetas.add("4");

        Barras barras = new Barras(elementos, etiquetas);
        try
        {
            barras.createChart(chart, null, null);
        } catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }
}
