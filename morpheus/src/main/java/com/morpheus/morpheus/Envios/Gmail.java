package com.morpheus.morpheus.Envios;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Method;

public class Gmail
{
    private Context context;

    public Gmail(Context context)
    {
        this.context = context;
    }

    public void enviarArchivo(File file, String titulo, String contenido, String... correo)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setPackage("com.google.android.gm");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, titulo);
        intent.putExtra(Intent.EXTRA_TEXT, contenido);
        intent.putExtra(Intent.EXTRA_EMAIL, correo);

        if(file != null)
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));

        intent.setType("text/plain");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        //Deshabilita para las versiones superiores a API 24
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            try
            {
                Method method = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                method.invoke(null);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        //Abre la app
        try
        {
            context.startActivity(intent);
        }catch (ActivityNotFoundException e)
        {
            Toast.makeText(context, "Instale la App de Gmail en el equipo por favor", Toast.LENGTH_LONG).show();
            solicitarInstalacionApp();
        }
    }

    //Metodo que entra a Play Store
    private void solicitarInstalacionApp()
    {
        //App de hoja de claculo de Google
        String packageApp = "com.google.android.gm";

        //Abre Play Store directamente
        try
        {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageApp)));
        }catch (ActivityNotFoundException e)
        {
            e.printStackTrace();
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" +packageApp)));
        }
    }
}
