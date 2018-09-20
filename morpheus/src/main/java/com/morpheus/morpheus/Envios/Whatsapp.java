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

public class Whatsapp
{
    private Context context;

    public Whatsapp(Context context)
    {
        this.context = context;
    }

    public void enviarArchivo(File file, String MIME)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setPackage("com.whatsapp");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        intent.setType(MIME);
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
            Toast.makeText(context, "Instale WhatsApp en el equipo por favor", Toast.LENGTH_LONG).show();
            solicitarInstalacionApp();
        }
    }

    //Metodo que entra a Play Store
    private void solicitarInstalacionApp()
    {
        //App de hoja de claculo de Google
        String packageApp = "com.whatsapp";

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
