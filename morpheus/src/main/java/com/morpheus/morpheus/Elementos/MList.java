package com.morpheus.morpheus.Elementos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morpheus on 29/08/2018.
 */

public class MList<T>
{
    private List<T> lista;

    public MList()
    {
        lista = new ArrayList<>();
    }

    public List<T> getLista()
    {
        return lista;
    }

    public void setLista(List<T> lista)
    {
        this.lista = lista;
    }
}
