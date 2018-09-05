package com.morpheus.morpheus.Elementos;

import android.os.Environment;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Locale;

/**
 * Created by Morpheus on 31/08/2018.
 */

public class MemoryURL
{
    private String file;
    private String directories;
    private String path;

    public MemoryURL(@NotNull String file)
    {
        this.file = file;
        directories = "";
    }

    public void setDirectory(String nameDirectory)
    {
        String cadena = nameDirectory.replace("/", "");
        directories += String.format(Locale.getDefault(), "%s/", cadena);
    }

    public void resetDirectories()
    {
        directories = "";
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getPath()
    {
        path = Environment.getExternalStorageDirectory().getPath() + "/";
        File carpeta = new File(path + directories);
        if(!carpeta.exists())
            carpeta.mkdirs();

        path += directories + file;
        return path;
    }

    public String getFile()
    {
        return file;
    }

    public void setFile(String file)
    {
        this.file = file;
    }
}
