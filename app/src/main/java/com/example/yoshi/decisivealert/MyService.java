package com.example.yoshi.decisivealert;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

/**
 * Created by yoshi on 11/24/2016.
 */

public class MyService extends Service {
    int count = 0;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    View v;/*
    @Override
   public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
        Log.d("hhhh", "Service started");
        //this.stopSelf();
        AudioManager myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        //int mode = myAudioManager.getRingerMode();
        while (count == 0)
        {
            if(myAudioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT)
            {
                count = 1;
                Log.d("hhhh", "In silent Mode");
                //createAlert();
               // showNotification(v);
                while (myAudioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT)
                {
                }
                if(myAudioManager.getRingerMode() != AudioManager.RINGER_MODE_SILENT)
                    count = 0;
            }
        }

    }
*/
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("hhhh", "Service started");
        getStatus();




//        AudioManager myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
//        //int mode = myAudioManager.getRingerMode();
//        while (count == 0)
//        {
//            if(myAudioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT)
//            {
//                count = 1;
//                Log.d("hhhh", "In silent Mode");
//                //Toast.makeText(this, "In silent mode", Toast.LENGTH_SHORT).show();
//                //createAlert();
//                // showNotification(v);
//                while (myAudioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT)
//                {
//                }
//                if(myAudioManager.getRingerMode() != AudioManager.RINGER_MODE_SILENT)
//                    count = 0;
//            }
//        }
        return START_STICKY;
    }

    void getStatus()
    {
        AudioManager myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        while(count == 0)
        {
            if (myAudioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT) {
                Log.d("hhhh", "In silent mode");
                count = 1;
            }
        }
    }
/*   public void createAlert()
    {
        Log.d("hhhh", "createAlert called");
        AlertDialog.Builder alert = new AlertDialog.Builder(MyService.this);
        alert.setTitle("Decisive Alert");
        alert.setMessage("Doyou want to turn on the app???");
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MyService.this, Home1.class);
                startActivity(intent);

            }
        });
        Log.d("hhhh", "positive button created");
        alert.setNegativeButton("no", null);
        Log.d("hhhh", "negative button created");
        AlertDialog alertDialog = alert.create();
        Log.d("hhhh", "alert dialogue created");
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
        Log.d("hhhh", "show created");
        /*alert.setMessage("Do you want to turn on the app???").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MyService.this, Home1.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alert.create();
        alertDialog.setTitle("Decisive Alert");
        alertDialog.show();
    }*/


    /*public void showNotification(View v)
    {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.notification_icon);
        builder.setContentTitle("Decisive Alert");
        builder.setContentText("Want to activate Decisive Alert???");
        Intent intent = new Intent(this, NotificationClass.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(NotificationClass.class);
        taskStackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager NM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NM.notify(0, builder.build());

    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("hhhh", "Service stopped");
    }
}
