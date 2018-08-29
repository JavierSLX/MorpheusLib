package com.morpheus.morpheus.Graficas;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.morpheus.morpheus.Elementos.Lista;
import com.morpheus.morpheus.Excepciones.GraficaException;
import com.morpheus.morpheus.Reflection.Metodo;
import com.morpheus.morpheus.Reflection.Objeto;
import com.morpheus.morpheus.Reflection.Reflexion;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morpheus on 28/08/2018.
 */

public class Lineas extends Grafica implements IChart<LineChart>
{

    public Lineas(@NotNull List<?> values, @NotNull List<?> labels)
    {
        super(values, labels);
    }

    @Override
    public void createChart(@NotNull LineChart chart, String nameMethodValue, String nameMethodLabel) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        boolean isValuePrimitive = false;
        boolean isLabelString = false;

        comprobacionDeDatos();

        //Verifica si las listas son los valores directos y que los parametros no sean nulos o vacios
        if(nameMethodValue == null || nameMethodValue.equals(""))
        {
            if(!Objeto.verificarInstanciaObjeto(getValues().get(0), "Integer") && !Objeto.verificarInstanciaObjeto(getValues().get(0), "Double") &&
                    !Objeto.verificarInstanciaObjeto(getValues().get(0), "Float"))
                throw new GraficaException("Los valores de la lista de valores no son correctos");
            else
                isValuePrimitive = true;
        }

        if(nameMethodLabel == null || nameMethodLabel.equals(""))
        {
            if(!Objeto.verificarInstanciaObjeto(getLabels().get(0), "String"))
                throw new GraficaException("Los valores de la lista de etiquetas no son correctos");
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
    public void createChart(@NotNull LineChart chart)
    {
        comprobacionDeDatos();

        //Verifica que las listas puedan ser usadas para grafica
        if(!Objeto.verificarInstanciaObjeto(getValues().get(0), "Integer") && !Objeto.verificarInstanciaObjeto(getValues().get(0), "Double") &&
                !Objeto.verificarInstanciaObjeto(getValues().get(0), "Float"))
            throw new GraficaException("Coloque valores numéricos para poder graficar");

        if(!Objeto.verificarInstanciaObjeto(getLabels().get(0), "String"))
            throw new GraficaException("Debe pasar un valor a nameMethodLabel");

        drawChart(chart, getValues(), (List<String>)getLabels());
    }

    @Override
    public void drawChart(@NotNull LineChart chart, List<?> values, List<String> labels)
    {
        chart.setDrawGridBackground(false);
        chart.setDescription(null);
        chart.setNoDataText("No hay elementos suficientes para graficar");
        chart.setTouchEnabled(true);

        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setPinchZoom(true);
        chart.setDescription(null);

        LimitLine llAxix = new LimitLine(10, "Index 10");
        llAxix.setLineWidth(4f);
        llAxix.enableDashedLine(10f, 10f, 0f);
        llAxix.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llAxix.setTextSize(10f);

        XAxis xAxis = chart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setValueFormatter(new LabelFormatter(labels));

        LimitLine ll1 = new LimitLine(100f, "Maximo hacia arriba");
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(10f);

        LimitLine ll2 = new LimitLine(0f, "Minimo hacia abajo");
        ll2.setLineWidth(4f);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll2.setTextSize(10f);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(ll2);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawLimitLinesBehindData(true);
        leftAxis.setValueFormatter(new NumberFormatter());

        double max = Lista.valueMax(values);
        float limite = (float)(max + (max * 0.2f));
        leftAxis.setAxisMaximum(limite);
        leftAxis.setAxisMaximum(0);

        chart.getAxisRight().setEnabled(false);

        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setEnabled(false);

        //Pasa los datos
        List<Entry> entries = new ArrayList<>();
        for(int i = 0; i < values.size(); i++)
        {
            Number number = (Number)values.get(i);
            entries.add(new Entry(i, number.floatValue()));
        }

        LineDataSet dataSet;

        //Define detalles finales
        dataSet = new LineDataSet(entries, "Datos");
        dataSet.enableDashedLine(10f, 5f, 0f);
        dataSet.getColor(Color.BLACK);
        dataSet.setCircleColor(Color.BLACK);
        dataSet.setCircleRadius(3f);
        dataSet.setLineWidth(1f);
        dataSet.setDrawCircleHole(false);
        dataSet.setValueTextSize(9f);
        dataSet.setDrawFilled(true);

        //dataSet.setFillColor(Color.BLACK);

        List<ILineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(dataSet);

        LineData data = new LineData(lineDataSets);
        chart.setData(data);

        chart.animateX(values.size() * 83);
        chart.invalidate();
    }
}
