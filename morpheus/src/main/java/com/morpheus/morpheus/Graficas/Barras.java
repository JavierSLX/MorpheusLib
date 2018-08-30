package com.morpheus.morpheus.Graficas;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morpheus on 27/08/2018.
 */

public class Barras extends Grafica implements IChart<BarChart>
{
    public Barras(@NotNull List<?> values, @NotNull List<?> labels)
    {
        super(values, labels);
    }

    @Override
    public void createChart(@NotNull BarChart chart, String nameMethodValue, String nameMethodLabel) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        comprobacionDeDatos();
        boolean[] instances = comprobacionInstancias(nameMethodValue, nameMethodLabel);

        if(instances[0] && instances[1])
        {
            createChart(chart);
            return;
        }

        //Saca las listas necesarias
        List<Object> valores = getListChart(Object.class, instances[0], getValues(), nameMethodValue);
        List<String> etiquetas = getListChart(String.class, instances[1], getLabels(), nameMethodLabel);

        drawChart(chart, valores, etiquetas);
    }

    @Override
    public void createChart(@NotNull BarChart chart)
    {
        comprobacionDeDatos();
        comprobacionInstancias();

        drawChart(chart, getValues(), (List<String>)getLabels());
    }

    @Override
    public void drawChart(@NotNull BarChart chart, @NotNull List<?> values, @NotNull List<String> labels)
    {
        List<BarEntry> entries = new ArrayList<>();

        for(int i = 0; i < values.size(); i++)
            entries.add(new BarEntry(i, ((Number) values.get(i)).floatValue()));

        BarDataSet set = new BarDataSet(entries, "");

        //Los colores a usar
        set.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(set);
        data.setBarWidth(0.7f);
        data.setValueTextSize(14);

        chart.setData(data);
        chart.animateY(2500);
        chart.setFitBars(true);
        chart.getLegend().setEnabled(false);

        //Cambia la posicion de las etiquetas X
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(labels.size());
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(12);
        xAxis.setValueFormatter(new LabelFormatter(labels));

        //Quita los valores de la derecha de la grafica
        YAxis right = chart.getAxisRight();
        right.setDrawGridLines(false);
        right.setEnabled(false);

        YAxis left = chart.getAxisLeft();
        left.setDrawGridLines(false);

        left.setValueFormatter(new NumberFormatter());

        //Quita la leyenda de descripcion
        chart.setDescription(null);

        chart.invalidate();
    }
}
