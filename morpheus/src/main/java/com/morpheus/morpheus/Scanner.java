package com.morpheus.morpheus;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class Scanner extends Activity implements ZBarScannerView.ResultHandler
{
    private ZBarScannerView scannerView;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        scannerView = new ZBarScannerView(this);
        mp = MediaPlayer.create(this, R.raw.pitido);
        setContentView(scannerView);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result)
    {
        //Reproduce el sonido
        mp.start();

        String codigo = result.getContents();
        scannerView.resumeCameraPreview(this);

        //Agrega el codigo al intent para seguir con el proceso
        Intent intent = getIntent();
        intent.putExtra("CODIGO", codigo);
        setResult(RESULT_OK, intent);
        finish();
    }
}
