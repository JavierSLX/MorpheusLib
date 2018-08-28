package com.morpheus.morpheus.Graficas;

import com.github.mikephil.charting.charts.BarChart;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morpheus on 27/08/2018.
 */

public class Barras extends Grafica implements IChart<BarChart>
{

    public Barras(List<?> values, List<?> labels)
    {
        super(values, labels);
    }

    @Override
    public BarChart createChart(String nameMethodValue, String nameMethodLabel)
    {
        //Verifica que todos los objetos sean del mismo tipo
        return null;
    }

    @Override
    public BarChart createChart()
    {
        return null;
    }
}
