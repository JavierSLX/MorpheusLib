package com.morpheus.libreria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.morpheus.morpheus.Elementos.Lista;
import com.morpheus.morpheus.Graficas.Barras;
import com.morpheus.morpheus.Graficas.Lineas;
import com.morpheus.morpheus.Graficas.Pastel;
import com.morpheus.morpheus.Reflection.Reflexion;
import com.morpheus.morpheus.Test.TestClass;
import com.morpheus.morpheus.WebService.Peticion;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PieChart chart = (PieChart) findViewById(R.id.grafica);

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1, "Javier", 30, "4560"));
        usuarios.add(new Usuario(2, "Zus", 26, "4561"));
        usuarios.add(new Usuario(3, "Fabi", 23, "4562"));
        usuarios.add(new Usuario(4, "Adii", 22, "4563"));
        usuarios.add(new Usuario(5, "Meli", 25, "4564"));
        usuarios.add(new Usuario(6, "Jesús", 26, "4565"));
        usuarios.add(new Usuario(7, "Angel", 29, "4566"));

        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente(1, "Javier", "JR"));
        clientes.add(new Cliente(2, "Zus", "ZS"));
        clientes.add(new Cliente(3, "Fabi", "FI"));
        clientes.add(new Cliente(4, "Adii", "AI"));
        clientes.add(new Cliente(5, "Meli", "MI"));
        clientes.add(new Cliente(6, "Jesus", "JS"));
        clientes.add(new Cliente(7, "Angel", "AL"));

        List<Integer> elementos = new ArrayList<>();
        elementos.add(12);
        elementos.add(5);
        /*elementos.add(78);
        elementos.add(26);
        elementos.add(33);
        elementos.add(9);
        elementos.add(109);*/

        List<String> etiquetas = new ArrayList<>();
        etiquetas.add("1");
        etiquetas.add("2");
        /*etiquetas.add("3");
        etiquetas.add("4");
        etiquetas.add("5");
        etiquetas.add("6");
        etiquetas.add("7");*/

        Pastel pastel = new Pastel(elementos, etiquetas, this);
        pastel.createChart(chart);
    }
}
