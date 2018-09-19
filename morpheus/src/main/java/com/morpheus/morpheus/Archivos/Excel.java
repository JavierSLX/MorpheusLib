package com.morpheus.morpheus.Archivos;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;

/**
 * Created by Morpheus on 31/08/2018.
 */

public class Excel extends Archivo
{
    private URI url;
    private File file;
    private Context context;

    public Excel(@NotNull Context context, @NotNull URI url)
    {
        super();
        this.url = url;
        this.context = context;
    }

    public void contenidoExcel(@NotNull String[] titulos, @NonNull Object[][] contenido)
    {
        file = new File(url.getPath());

        StringBuilder cadena = new StringBuilder();
        String[] carpetas = url.getPath().split("/");
        for(int i = 0; i < carpetas.length - 1; i++)
        {
            cadena.append(carpetas[i]);
            if(i < carpetas.length - 2)
                cadena.append("/");
        }

        File carpeta = new File(cadena.toString());
        if(!carpeta.exists())
            carpeta.mkdirs();

        //Crea un nuevo libro
        HSSFWorkbook workbook = new HSSFWorkbook();

        //Crea una nueva hoja
        HSSFSheet sheet = workbook.createSheet();

        HSSFCell cell;
        HSSFRow row;

        int n = 1;
        row = sheet.createRow(n);
        for(int i = 0; i < titulos.length; i++)
        {
            cell = row.createCell(i);
            cell.setCellValue(titulos[i]);
            sheet.setColumnWidth(i, (titulos.length * 600));
        }

        //Coloca el contenido
        for (Object[] elemento : contenido)
        {
            row = sheet.createRow(++n);
            for (int j = 0; j < elemento.length; j++)
            {
                cell = row.createCell(j);
                cell.setCellValue((String) elemento[j]);
            }
        }

        //Pasa la configuracion y el contenido a un archivo Excel
        FileOutputStream fileOutputStream = null;
        try
        {
            fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            fileOutputStream.close();

        } catch (java.io.IOException e)
        {
            e.printStackTrace();
        }
    }

    public void abrirExcel()
    {
        Uri uri = Uri.fromFile(file);
        Log.i("excel", file.getPath());
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try
        {
            context.startActivity(intent);
        }catch (ActivityNotFoundException e)
        {
            Toast.makeText(context, "Instale una app de lectura de Excel, por favor", Toast.LENGTH_SHORT).show();
            solicitarInstalacionApp();
        }
    }

    //Busca instalar una app
    private void solicitarInstalacionApp()
    {
        //App de hoja de calculo de Google
        String packageApp = "com.google.android.apps.docs.editors.sheets";

        //Abre Play Store directamente
        try
        {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageApp)));
        }catch (ActivityNotFoundException e)
        {
            e.printStackTrace();
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id" + packageApp)));
        }
    }

    public URI getUrl()
    {
        return url;
    }

    public void setUrl(URI url)
    {
        this.url = url;
    }
}
