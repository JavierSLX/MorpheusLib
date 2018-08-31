package com.morpheus.morpheus.Elementos;

import android.os.Environment;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * Created by Morpheus on 31/08/2018.
 */

public class MemoryURL
{
    private String file;
    private String directories;

    public MemoryURL(@NotNull String file)
    {
        this.file = file;
        directories = "";
    }

    public void setDirectory(String nameDirectory)
    {
        String cadena = nameDirectory.replace("/", "");
        directories += String.format(Locale.getDefault(), "%s/", nameDirectory);
    }

    public void resetDirectories()
    {
        directories = "";
    }

    public String getPath()
    {
        String path = Environment.getExternalStorageDirectory() + "/";
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
