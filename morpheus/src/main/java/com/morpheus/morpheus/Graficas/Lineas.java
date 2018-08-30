package com.morpheus.morpheus.Graficas;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.morpheus.morpheus.Elementos.Lista;
import com.morpheus.morpheus.R;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morpheus on 28/08/2018.
 */

public class Lineas extends Grafica implements IChart<LineChart>
{
    private Context context;

    public Lineas(List<?> values, List<?> labels, Context context)
    {
        super(values, labels);
        this.context = context;
    }

    @Override
    public void createChart(@NotNull LineChart chart, String nameMethodValue, String nameMethodLabel) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
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
    public void createChart(@NotNull LineChart chart)
    {
        comprobacionDeDatos();
        comprobacionInstancias();

        drawChart(chart, getValues(), (List<String>)getLabels());
    }

    @Override
    public void drawChart(@NotNull LineChart chart, List<?> values, List<String> labels)
    {
        chart.setDrawGridBackground(false);
        chart.setDescription(null);
        chart.setNoDataText("No hay elementos suficientes a graficar");
        chart.setTouchEnabled(true);

        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setPinchZoom(true);
        chart.setDescription(null);

        LimitLine llAxis = new LimitLine(10f, "Index 10");
        llAxis.setLineWidth(4f);
        llAxis.enableDashedLine(10f, 10f, 0f);
        llAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llAxis.setTextSize(10f);

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

        //Saca el valor maximo mas 20%
        float max = Lista.valueMax(values);
        float limite = max + (max * 0.2f);
        leftAxis.setAxisMaximum(limite);
        leftAxis.setAxisMinimum(0);

        chart.getAxisRight().setEnabled(false);

        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setEnabled(false);

        //Pasa los datos
        List<Entry> entries = new ArrayList<>();
        for(int i = 0; i < values.size(); i++)
            entries.add(new Entry(i, ((Number)values.get(i)).floatValue()));

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

        //Dependiendo de la version de SDK se le pasa un dato o no
        if(Utils.getSDKInt() > 18)
        {
            Drawable drawable = ContextCompat.getDrawable(context, R.drawable.fade_red);
            dataSet.setFillDrawable(drawable);
        }
        else
            dataSet.setFillColor(Color.BLACK);

        List<ILineDataSet> lineDataSet = new ArrayList<>();
        lineDataSet.add(dataSet);

        LineData data = new LineData(lineDataSet);
        chart.setData(data);

        chart.animateX(values.size() * 83);
        chart.invalidate();
    }
}
