package com.example.yoshi.decisivealert;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by yoshi on 12/9/2016.
 */

public class PhoneStateReceiver extends BroadcastReceiver {
    private AudioManager audioManager;
    Switch sw1;
    int insert = 0;
    @Override
    public void onReceive(Context context, Intent intent) {
        try {

            MyDatabase mydb = new MyDatabase(context);
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Log.d("pppp", incomingNumber);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

//                    String action = intent.getAction();
//                    Log.d("jjjj", action);
                if (mydb.getSettingsData("Settings", "manual").equals("yes"))
                {
                    Cursor customContacts = mydb.getCustomContacts();
                    if (mydb.getSettingsData("Settings", "Calls").equals("custom"))
                    {
                        while (customContacts.moveToNext())
                        {
                            if (customContacts.getString(0).equals(incomingNumber))
                            {
                                audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                                    }
                                }, 30000);
                            }
                        }
                        return;
                    }

                    Cursor data = mydb.getCallersData();
                    while (data.moveToNext())
                    {
                        if (data.getString(0).equals(incomingNumber)) {
                            int count = Integer.parseInt(data.getString(1));
                            if ((Integer.parseInt(mydb.getSettingsData("Settings", "numOfCalls"))) <= count + 1)
                            {
                                audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                                    }
                                }, 30000);
                            }
                            Log.d("llll", "Count = "+ String.valueOf(count));
                            count += 1;
//                                Log.d("pppp", String.valueOf(count));
                            Boolean updateVal =  mydb.updateCallersData(incomingNumber, String.valueOf(count));
                            insert = 1;
                            if (updateVal == true)
                                Toast.makeText(context, String.valueOf(count), Toast.LENGTH_SHORT).show();
//                                    Toast.makeText(context, "Data updated", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(context, "Data updation error", Toast.LENGTH_SHORT).show();
                            Log.d("llll", "Count updated to " + String.valueOf(count));

                        }
                    }
                    if (insert == 0)
                    {
                        int res = mydb.insertCallers(incomingNumber, "1");
                        if (res == 1)
                            Toast.makeText(context, "Data inserted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    }
                    insert = 0;
                    if ((mydb.getSettingsData("Settings", "sendSMS").equals("yes")))
                    {
                        sendSMS(incomingNumber, mydb.getSettingsData("Settings", "SMSText"));
                    }
                }
//                        Toast.makeText(context, "Ringing State Number is - " + incomingNumber, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    protected void sendSMS(String contacts, String msg) {

        //for (int i = 0; i < contacts.size(); i++) {

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(contacts, null, msg, null, null);
//            Toast.makeText(context, "Message Sent",
//                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
//            Toast.makeText(getApplicationContext(), ex.getMessage(),
//                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }

    }
}
