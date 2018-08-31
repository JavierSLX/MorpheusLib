package com.morpheus.libreria;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MemoryURL url = new MemoryURL("ejemplo.xml");
        url.setDirectory("activachip");
        url.setDirectory("archivos");

        Log.i("excel", url.getPath());
    }
}
