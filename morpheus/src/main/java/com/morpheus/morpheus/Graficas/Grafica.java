package com.morpheus.morpheus.Graficas;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.morpheus.morpheus.Elementos.Lista;
import com.morpheus.morpheus.Excepciones.GraficaException;
import com.morpheus.morpheus.Reflection.Metodo;
import com.morpheus.morpheus.Reflection.Objeto;
import com.morpheus.morpheus.Reflection.Reflexion;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
            throw new GraficaException("Los valores nulos en el constructor no son válidos");
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

    //Comprueba si los nombres de los metodos existen cuando no son nulos (o en su defecto tienen objetos instanciados del tipo necesario)
    protected boolean[] comprobacionInstancias(String nameGetValues, String nameGetLabels)
    {
        boolean[] instances = new boolean[]{false, false};

        //Verifica si las listas son los valores directos y que los parametros no sean nulos o vacios
        if(nameGetValues == null || nameGetValues.equals(""))
        {
            if(!Objeto.verificarInstanciaObjeto(getValues().get(0), "Integer", "Float", "Double"))
                throw new GraficaException("Los valores de la lista de valores no son correctos");
            else
                instances[0] = true;
        }

        if(nameGetLabels == null || nameGetLabels.equals(""))
        {
            if(!Objeto.verificarInstanciaObjeto(getLabels().get(0), "String"))
                throw new GraficaException("Los valores de la lista de etiquetas no son correctos");
            else
                instances[1] = true;
        }

        return instances;
    }

    protected void comprobacionInstancias()
    {
        //Verifica que las listas puedan ser usadas para grafica
        if(!Objeto.verificarInstanciaObjeto(getValues().get(0), "Integer", "Float", "Double"))
            throw new GraficaException("Coloque valores numéricos para poder graficar");

        if(!Objeto.verificarInstanciaObjeto(getLabels().get(0), "String"))
            throw new GraficaException("Debe pasar un valor a nameMethodLabel");
    }

    //Obtiene la lista de elementos con los parametros necesarios en la grafica
    protected <Type> List<Type> getListChart(Class<Type> typeClass, boolean isTrueParameter, List<?> list, String nameGetMethod) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        List<Type> valores;

        //Verifica si los metodos que se le paso existen
        if(!isTrueParameter)
        {
            if (!Metodo.verificarExistenciaMetodoInstanciado(Reflexion.getMethods(list.get(0)), nameGetMethod))
                throw new GraficaException("El método no existe en los objetos contenidos en la lista");

            //Saca la lista
            valores = (List<Type>) Lista.getListObjects(Object.class, list, nameGetMethod);
        }
        else
            valores = (List<Type>) list;

        return valores;
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
