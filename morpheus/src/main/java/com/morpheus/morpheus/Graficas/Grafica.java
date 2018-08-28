package com.morpheus.morpheus.Graficas;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.morpheus.morpheus.Elementos.Lista;
import com.morpheus.morpheus.Excepciones.GraficaException;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Morpheus on 28/08/2018.
 */

public abstract class Grafica
{
    private List<?> values;
    private List<?> labels;

    public Grafica(List<?> values, List<?> labels)
    {
        this.values = values;
        this.labels = labels;

        if(getValues() == null || getLabels() == null)
            throw new RuntimeException("Los valores nulos en el constructor no son válidos");
    }

    public List<?> getValues()
    {
        return values;
    }

    public void setValues(List<?> values)
    {
        this.values = values;
    }

    public List<?> getLabels()
    {
        return labels;
    }

    public void setLabels(List<?> labels)
    {
        this.labels = labels;
    }

    protected void comprobacionDeDatos() throws GraficaException
    {
        //Verifica que todos los objetos sean del mismo tipo
        if(!Lista.verificacionDeObjetos(getValues()) || !Lista.verificacionDeObjetos(getLabels()))
            throw new GraficaException("Los objetos de la lista deben de ser de la misma clase instanciada");

        //Verifica que las dos listas sean del mismo tamaño
        if(getValues().size() != getLabels().size())
            throw new GraficaException("Las listas deben de ser del mismo tamaño");
    }

    //Clase que le da formato a las etiquetas
    public static class LabelFormatter implements IAxisValueFormatter
    {
        private List<String> etiquetas;

        public LabelFormatter(List<String> etiquetas)
        {
            this.etiquetas = etiquetas;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis)
        {
            return etiquetas.get((int)value);
        }
    }

    //Clase que le da formato a las cantidad numericas
    public static class NumberFormatter implements IAxisValueFormatter
    {
        private DecimalFormat format;

        public NumberFormatter()
        {
            format = new DecimalFormat("###,###");
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis)
        {
            return format.format(value);
        }
    }
}
