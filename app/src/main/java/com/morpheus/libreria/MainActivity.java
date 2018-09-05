package com.morpheus.libreria;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.morpheus.morpheus.Archivos.Excel;
import com.morpheus.morpheus.Elementos.MemoryURL;
import com.morpheus.morpheus.Scanner;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity
{
    private String PERMISOS[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int escribir = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(escribir != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, PERMISOS, 1);

        MemoryURL url = new MemoryURL("ejemplo.xml");
        url.setDirectory("primera");
        url.setDirectory("segunda");

        Log.i("excel", url.getPath());

        Excel excel = new Excel(this, url);
        excel.contenidoExcel(new String[]{"Primero", "Segundo", "Tercero"}, null);
        excel.abrirExcel();
    }
}
