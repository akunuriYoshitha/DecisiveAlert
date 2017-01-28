package com.example.yoshi.decisivealert;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static android.media.AudioManager.RINGER_MODE_NORMAL;
import static android.media.AudioManager.RINGER_MODE_SILENT;
import static android.media.AudioManager.RINGER_MODE_VIBRATE;

public class MainActivity extends Activity {
    private AudioManager myAudioManager;
    MyDatabase mydb = new MyDatabase(MainActivity.this);
    Switch sw1, sw2;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startService(new Intent(this, MyService.class));
        switch1Click();
        switch2Click();
        textViewOnClick();
        if(mydb.getValue("Manual").equals("yes"))
            sw1.setChecked(true);
        if(mydb.getValue("Sleeping").equals("yes"))
            sw2.setChecked(true);
    }

    public void switch1Click(){
        sw1 = (Switch) findViewById(R.id.switch1);

        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    Intent intent = new Intent(MainActivity.this, PhoneStateReceiver.class);
                    mydb.updateData("Manual", "1", "yes");
                    myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                    myAudioManager.setRingerMode(RINGER_MODE_SILENT);
                    if (myAudioManager.getRingerMode() == RINGER_MODE_SILENT)
                        Toast.makeText(MainActivity.this, "ringer mode set to silent", Toast.LENGTH_LONG).show();
                    Intent in = new Intent("ManualModeOn");
                    in.putExtra("state", "activated");
                    sendBroadcast(in);
//                    Intent intent = new Intent(MainActivity.this, AlertReceiver.class);
//                    PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, 12000, 12000, pendingIntent);
//                    PhoneStateReceiver phoneStateReceiver = new PhoneStateReceiver();
//                    phoneStateReceiver.onReceive(MainActivity.this, intent);


                }
                else
                {
                    mydb.updateData("Manual", "1", "no");
                    myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                    myAudioManager.setRingerMode(RINGER_MODE_NORMAL);
                    mydb.truncateCallers();
//                    if (myAudioManager.getRingerMode() == RINGER_MODE_NORMAL)
//                        Toast.makeText(MainActivity.this, "ringer mode set to normal", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void switch2Click(){
        sw2 = (Switch) findViewById(R.id.switch2);
        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    mydb.updateData("Sleeping", "1", "yes");
                    myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                    myAudioManager.setRingerMode(RINGER_MODE_SILENT);
                    if (myAudioManager.getRingerMode() == RINGER_MODE_SILENT)
                        Toast.makeText(MainActivity.this, "ringer mode set to silent", Toast.LENGTH_LONG).show();                }
                else
                {
                    mydb.updateData("Sleeping", "1", "no");
                    myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                    myAudioManager.setRingerMode(RINGER_MODE_NORMAL);
                    if (myAudioManager.getRingerMode() == RINGER_MODE_NORMAL)
                        Toast.makeText(MainActivity.this, "ringer mode set to normal", Toast.LENGTH_LONG).show();                }
            }
        });
    }

    public void textViewOnClick(){
        tv1 = (TextView)findViewById(R.id.textView);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
            }
        });
    }



}


