package com.example.yoshi.decisivealert;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by CH.PUSHPA SAI on 11-02-2017.
 */
public class Alert extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder a_builder = new AlertDialog.Builder(Alert.this);
        a_builder.setMessage("Do you want to ON the Decisive Alert!!!")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                        //Log.i("msgA", "onCreate5");
                    }
                }) ;
        AlertDialog alert = a_builder.create();
        alert.setTitle("Alert !!!");
        alert.show();
    }
}
