package com.morpheus.morpheus.Graficas;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by Morpheus on 28/08/2018.
 */

public interface IChart<T>
{
    void createChart(T chart, String nameMethodValue, String nameMethodLabel) throws Exception;
    void createChart(T chart);
    void drawChart(T chart, List<?> values, List<String> labels);
}
