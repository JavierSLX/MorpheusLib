package com.morpheus.morpheus.Archivos;

import java.io.File;
import java.net.URL;

/**
 * Created by Morpheus on 31/08/2018.
 */

public class Excel
{
    private URL url;

    public Excel(URL url)
    {
        this.url = url;
    }

    public String contenidoExcel(String[] titulos, String[][] contenido)
    {
        return url.getPath();
    }

    public URL getUrl()
    {
        return url;
    }

    public void setUrl(URL url)
    {
        this.url = url;
    }
}
