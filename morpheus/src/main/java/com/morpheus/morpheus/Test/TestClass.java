package com.morpheus.morpheus.Test;

import android.content.Context;
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
import com.morpheus.morpheus.Graficas.Grafica;
import com.morpheus.morpheus.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morpheus on 30/08/2018.
 */

public class TestClass
{
    public static List<Float> testConverList(List<?> lista)
    {
        List<Float> list = new ArrayList<>();

        for(int i = 0; i < lista.size(); i++)
            list.add(((Number)lista.get(i)).floatValue());

        return list;
    }

    public static List<Entry> drawChart(Context context, @NotNull LineChart chart, List<?> values, List<String> labels)
    {
        chart.setDrawGridBackground(false);
        chart.setDescription(null);
        chart.setNoDataText("No hay elementos suficientes para graficar");
        chart.setTouchEnabled(true);

        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setPinchZoom(true);
        chart.setDescription(null);

        LimitLine llAxix = new LimitLine(10f, "Index 10");
        llAxix.setLineWidth(4f);
        llAxix.enableDashedLine(10f, 10f, 0f);
        llAxix.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llAxix.setTextSize(10f);

        XAxis xAxis = chart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setValueFormatter(new Grafica.LabelFormatter(labels));

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
        leftAxis.setValueFormatter(new Grafica.NumberFormatter());

        float max = Lista.valueMax(values);
        float limite = max + (max * 0.2f);
        leftAxis.setAxisMaximum(limite);
        leftAxis.setAxisMaximum(0);

        chart.getAxisRight().setEnabled(false);

        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setEnabled(false);

        //Pasa los datos
        List<Entry> entries = new ArrayList<>();
        for(int i = 0; i < values.size(); i++)
            entries.add(new Entry(i, ((Number)values.get(i)).floatValue()));

        return entries;
        /*LineDataSet dataSet;

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

        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.fade_red);
        dataSet.setFillDrawable(drawable);

        List<ILineDataSet> lineDataSets = new ArrayList<>();
        lineDataSets.add(dataSet);

        LineData data = new LineData(lineDataSets);
        chart.setData(data);

        chart.animateX(values.size() * 83);
        chart.invalidate();*/
    }
}
