package com.morpheus.libreria;

import java.util.Locale;

/**
 * Created by Morpheus on 27/08/2018.
 */

public class Usuario
{
    private int id;
    private String nombre;
    private int edad;
    private String telefono;

    public Usuario(int id, String nombre, int edad, String telefono)
    {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public int getEdad()
    {
        return edad;
    }

    public void setEdad(int edad)
    {
        this.edad = edad;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    @Override
    public String toString()
    {
        return String.format(Locale.getDefault(), "ID: %d, Nombre: %s, Edad: %d, Telefono: %s", getId(), getNombre(), getEdad(), getTelefono());
    }
}
