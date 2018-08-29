package com.morpheus.libreria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.morpheus.morpheus.Graficas.Barras;
import com.morpheus.morpheus.Graficas.Lineas;
import com.morpheus.morpheus.WebService.Peticion;

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

        LineChart chart = (LineChart) findViewById(R.id.grafica);

        Usuario javier = new Usuario(1, "Javier Serrano", 30, "4612578406");
        Usuario meli = new Usuario(2, "Melissa", 23, "Nada");
        Usuario conocido = new Usuario(3, "Estandar", 25, "estandar@estandar.com");

        List<Integer> elementos = new ArrayList<>();
        elementos.add(12);
        elementos.add(5);
        elementos.add(78);
        elementos.add(26);
        elementos.add(33);
        elementos.add(9);
        elementos.add(109);
        elementos.add(11);

        List<String> etiquetas = new ArrayList<>();
        etiquetas.add("1");
        etiquetas.add("2");
        etiquetas.add("3");
        etiquetas.add("4");
        etiquetas.add("5");
        etiquetas.add("6");
        etiquetas.add("7");
        etiquetas.add("8");

        Lineas lineas = new Lineas(elementos, etiquetas);
        lineas.createChart(chart);
    }
}
