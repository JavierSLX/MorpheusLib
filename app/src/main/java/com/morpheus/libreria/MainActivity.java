package com.morpheus.libreria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.morpheus.morpheus.Elementos.Lista;
import com.morpheus.morpheus.Graficas.Barras;
import com.morpheus.morpheus.Graficas.Lineas;
import com.morpheus.morpheus.Reflection.Reflexion;
import com.morpheus.morpheus.Test.TestClass;
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

        BarChart chart = (BarChart) findViewById(R.id.grafica);

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

        Barras barras = new Barras(elementos, etiquetas);
        barras.createChart(chart);
    }
}
