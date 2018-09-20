package com.morpheus.libreria;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.morpheus.morpheus.Archivos.Excel;
import com.morpheus.morpheus.Envios.Whatsapp;

import java.io.File;
import java.net.URI;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private TextView txtRespuesta;
    private String PERMISOS[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS,
    Manifest.permission.WRITE_CONTACTS, Manifest.permission.CALL_PHONE};
    private static final int REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int escribirMemoria = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int escribirContacto = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS);
        int leerContacto = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        int llamada = ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if(escribirMemoria != PackageManager.PERMISSION_GRANTED || escribirContacto != PackageManager.PERMISSION_GRANTED || leerContacto != PackageManager.PERMISSION_GRANTED ||
                llamada != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, PERMISOS, 1);

        /*txtRespuesta = (TextView)findViewById(R.id.txtResultado);
        findViewById(R.id.btHablar).setOnClickListener(this);

        PieChart chart = (PieChart)findViewById(R.id.grafica);*/

        URI uri = URI.create(Environment.getExternalStorageDirectory() + "/excel/helado.xml");
        Toast.makeText(this, uri.getPath(), Toast.LENGTH_SHORT).show();

        Excel excel = new Excel(this, uri);
        String contenido[][] = new String[][]{{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};
        File file = excel.contenidoExcel(new String[]{"Primero", "Segundo", "Tercero"}, contenido);
        //excel.abrirExcel();
        Whatsapp whatsapp = new Whatsapp(this);
        whatsapp.enviarArchivo(file, "application/vnd.ms-excel");
    }

    @Override
    public void onClick(View view)
    {
        Intent intentVoz = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        //Configura el lenguaje en español (Mexico)
        intentVoz.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX");
        try
        {
            startActivityForResult(intentVoz, REQUEST);
        }catch (ActivityNotFoundException e)
        {
            Toast.makeText(this, "Tu dispositivo no soporta el reconocimiento por voz", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case REQUEST:
                if(resultCode == RESULT_OK && data != null)
                {
                    List<String> voz = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String texto = voz.get(0);
                    txtRespuesta.setText(texto);
                }
                break;
        }
    }
}
