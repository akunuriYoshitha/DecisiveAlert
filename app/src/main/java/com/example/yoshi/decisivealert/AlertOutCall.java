package com.example.yoshi.decisivealert;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by CH.PUSHPA SAI on 26-01-2017.
 */
public class AlertOutCall extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Log.i("msgA", "onCreate1");
        super.onCreate(savedInstanceState);
        Log.i("msgA", "onCreate2");
        Toast.makeText(AlertOutCall.this,"toast msg", Toast.LENGTH_LONG).show();
        AlertDialog.Builder a_builder = new AlertDialog.Builder(AlertOutCall.this);
        a_builder.setMessage("Do you want to stop the Decisive Alert!!!")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Log.i("msgA", "onCreate4");
                        finish();
                        Log.i("msgA", "onCreate5");
                    }
                }) ;
        AlertDialog alert = a_builder.create();
        alert.setTitle("Alert !!!");
        alert.show();
        Log.i("msgA", "onCreate3");
    }
}
