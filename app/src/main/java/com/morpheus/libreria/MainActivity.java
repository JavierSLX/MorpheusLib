package com.morpheus.libreria;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.morpheus.morpheus.Scanner;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int camara = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if(camara != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);

        Intent intent = new Intent(this, Scanner.class);
        startActivityForResult(intent, 1);
    }
}
