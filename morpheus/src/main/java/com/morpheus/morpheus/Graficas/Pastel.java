package com.morpheus.morpheus.Graficas;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.morpheus.morpheus.Herramientas.Math;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Morpheus on 30/08/2018.
 */

public class Pastel extends Grafica implements IChart<PieChart>
{
    private Context context;

    public Pastel(List<?> values, List<?> labels, Context context)
    {
        super(values, labels);
        this.context = context;
    }

    @Override
    public void createChart(PieChart chart, String nameMethodValue, String nameMethodLabel) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
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
    public void createChart(PieChart chart)
    {
        comprobacionDeDatos();
        comprobacionInstancias();

        drawChart(chart, getValues(), (List<String>)getLabels());
    }

    @Override
    public void drawChart(PieChart chart, List<?> values, List<String> labels)
    {
        //Crea la lista de los valores que tendrá la grafica
        List<PieEntry> entries = new ArrayList<>();
        for(int i = 0; i < values.size(); i++)
            entries.add(new PieEntry(((Number)values.get(i)).floatValue(), labels.get(i)));

        //Crea el objeto de los datos del Pie y los convierte en datos
        PieDataSet dataSet = new PieDataSet(entries, "Pastel");
        dataSet.setValueTextColor(ContextCompat.getColor(context, android.R.color.white));
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData(dataSet);

        //Agrega los valores a la grafica
        chart.setData(data);
        chart.setRotationAngle(0);
        chart.setRotationEnabled(false);
        chart.animateXY(1000, 1000);

        //Tamaño del texto interno
        chart.getData().setValueTextSize(14f);
        chart.setEntryLabelTextSize(12f);

        //Los valores de la sombra del centro y del espacio vacio
        chart.setHoleRadius(0);
        chart.setTransparentCircleRadius(0);
        chart.highlightValues(null);

        //Oculta la leyenda
        chart.getLegend().setEnabled(false);
        chart.setDescription(null);
        chart.setDrawCenterText(true);
        chart.setDrawEntryLabels(true);

        chart.invalidate();

        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener()
        {
            @Override
            public void onValueSelected(Entry e, Highlight h)
            {
                //Obtiene el porcentaje al presionar sobre un elemento
                double valor = Math.calculoPorcentaje(getValues(), e.getY());
                Toast.makeText(context, String.format(Locale.getDefault(), "%02.2f %%", valor), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected()
            {

            }
        });
    }
}
