package com.morpheus.morpheus.Graficas;

import java.util.List;

/**
 * Created by Morpheus on 28/08/2018.
 */

public interface IChart<T>
{
    T createChart(String nameMethodValue, String nameMethodLabel);
    T createChart();
}
