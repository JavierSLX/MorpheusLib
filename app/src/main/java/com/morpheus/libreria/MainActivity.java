package com.morpheus.libreria;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.morpheus.morpheus.Archivos.Excel;
import com.morpheus.morpheus.Elementos.MemoryURL;
import com.morpheus.morpheus.Graficas.Pastel;
import com.morpheus.morpheus.Scanner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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

        MemoryURL url = new MemoryURL("ejemplo.xml");
        url.setDirectory("primera");
        url.setDirectory("segunda");

        Log.i("excel", url.getPath());

        Excel excel = new Excel(this, url);
        String contenido[][] = new String[][]{{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};
        excel.contenidoExcel(new String[]{"Primero", "Segundo", "Tercero"}, contenido);
        excel.abrirExcel();

        /*List<Integer> valores = new ArrayList<Integer>();
        valores.add(23);
        valores.add(55);
        valores.add(10);
        valores.add(67);
        valores.add(31);
        valores.add(44);

        List<String> nombres = new ArrayList<>();
        nombres.add("Juan");
        nombres.add("David");
        nombres.add("Rubén");
        nombres.add("Andrea");
        nombres.add("Melisa");
        nombres.add("Esteban");

        Pastel pastel = new Pastel(valores, nombres, this);
        pastel.createChart(chart);*/
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
