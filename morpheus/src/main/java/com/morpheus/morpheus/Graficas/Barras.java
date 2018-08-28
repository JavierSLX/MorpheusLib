package com.morpheus.morpheus.Graficas;

import com.github.mikephil.charting.charts.BarChart;
import com.morpheus.morpheus.Elementos.Lista;
import com.morpheus.morpheus.Reflection.Objeto;
import com.morpheus.morpheus.Reflection.Reflexion;

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
        boolean isValueString = false;
        boolean isLabelString = false;

        //Verifica que todos los objetos sean del mismo tipo
        if(!Lista.verificacionDeObjetos(getValues()) || !Lista.verificacionDeObjetos(getLabels()))
            throw new RuntimeException("Los objetos de la lista deben de ser de la misma clase instanciada");

        //Verifica que las dos listas sean del mismo tamaño
        if(getValues().size() != getLabels().size())
            throw new RuntimeException("Las listas deben de ser del mismo tamaño");

        //Verifica si las listas son o no String y que los parametros no sean nulos o vacios
        if(nameMethodValue == null || nameMethodValue.equals(""))
        {
            if(!Objeto.verificarInstanciaObjeto(getValues().get(0), "String"))
                throw new RuntimeException("Debe pasar un valor a nameMethodValue");
            else
                isValueString = true;
        }

        if(nameMethodLabel == null || nameMethodLabel.equals(""))
        {
            if(!Objeto.verificarInstanciaObjeto(getLabels().get(0), "Double"))
                throw new RuntimeException("Debe pasar un valor a nameMethodLabel");
            else
                isLabelString = true;
        }

        return null;
    }

    @Override
    public BarChart createChart()
    {
        return null;
    }
}
