package com.morpheus.morpheus.Graficas;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.morpheus.morpheus.Elementos.Lista;
import com.morpheus.morpheus.Excepciones.GraficaException;
import com.morpheus.morpheus.Reflection.Metodo;
import com.morpheus.morpheus.Reflection.Objeto;
import com.morpheus.morpheus.Reflection.Reflexion;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
        boolean isValuePrimitive = false;
        boolean isLabelString = false;

        comprobacionDeDatos();

        //Verifica si las listas son los valores directos y que los parametros no sean nulos o vacios
        if(nameMethodValue == null || nameMethodValue.equals(""))
        {
            if(!Objeto.verificarInstanciaObjeto(getValues().get(0), "Integer") && !Objeto.verificarInstanciaObjeto(getValues().get(0), "Double") &&
                    !Objeto.verificarInstanciaObjeto(getValues().get(0), "Float"))
                throw new GraficaException("Debe pasar un valor a nameMethodValue");
            else
                isValuePrimitive = true;
        }

        if(nameMethodLabel == null || nameMethodLabel.equals(""))
        {
            if(!Objeto.verificarInstanciaObjeto(getLabels().get(0), "String"))
                throw new GraficaException("Debe pasar un valor a nameMethodLabel");
            else
                isLabelString = true;
        }

        if(isValuePrimitive && isLabelString)
        {
            createChart(chart);
            return;
        }

        //Saca las listas necesarias
        List<Object> valores;
        //Verifica si los metodos que se le paso existen
        if(!isValuePrimitive)
        {
            if (!Metodo.verificarExistenciaMetodoInstanciado(Reflexion.getMethods(getValues().get(0)), nameMethodValue))
                throw new GraficaException("El método no existe en los objetos contenidos en la lista");

            //Saca la lista
            valores = Lista.getListObjects(getValues(), nameMethodValue);
        }
        else
            valores = (List<Object>)getValues();

        List<String> etiquetas;
        if(!isLabelString)
        {
            if (!Metodo.verificarExistenciaMetodoInstanciado(Reflexion.getMethods(getLabels().get(0)), nameMethodLabel))
                throw new GraficaException("El método no existe en los objetos contenidos en la lista");

            //Saca la lista
            etiquetas = ((List<String>)((Object)Lista.getListObjects(getLabels(), nameMethodLabel)));
        }
        else
            etiquetas = (List<String>)(getLabels());

        drawChart(chart, valores, etiquetas);
    }

    @Override
    public void createChart(@NotNull BarChart chart)
    {
        comprobacionDeDatos();


    }

    @Override
    public void drawChart(@NotNull BarChart chart, @NotNull List<?> values, @NotNull List<String> labels)
    {
        List<BarEntry> entries = new ArrayList<>();

        for(int i = 0; i < values.size(); i++)
            entries.add(new BarEntry(i, ((Number) values.get(i)).floatValue()));

        BarDataSet set = new BarDataSet(entries, "");

        //Los colores a usar
        set.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData data = new BarData(set);
        data.setBarWidth(0.5f);
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
