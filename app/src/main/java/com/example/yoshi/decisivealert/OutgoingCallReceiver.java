package com.example.yoshi.decisivealert;


import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by CH.PUSHPA SAI on 22-01-2017.
 */
public class OutgoingCallReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        MyDatabase mydb = new MyDatabase(context);
        if (mydb.getSettingsData("Settings", "manual").equals("yes")) {
            String phoneNumber;
            int p = 0;
            phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            //AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            p = 1;
            //Log.i("msg", "onReceive3");
            Log.d(OutgoingCallReceiver.class.getSimpleName(), intent.toString() + ", call to: " + phoneNumber);
//        Toast.makeText(context, "Outgoing call catched: " + phoneNumber, Toast.LENGTH_LONG).show();
            //Log.i("msg", "onReceive4");

            if(p == 1) {
                try {
                    Log.i("msg", "onReceive5");
                    Intent intent1  = new Intent(context, AlertOutCall.class);
                    intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Log.i("msg", "onReceive6");
                    context.startActivity(intent1);
                    Log.i("msg", "onReceive7");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }


        }

         //TODO: Handle outgoing call event here
    }
}
