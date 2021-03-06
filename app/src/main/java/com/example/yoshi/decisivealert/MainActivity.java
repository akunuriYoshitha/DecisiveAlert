package com.example.yoshi.decisivealert;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.*;
import com.google.firebase.auth.FirebaseAuth;

import static android.media.AudioManager.RINGER_MODE_NORMAL;
import static android.media.AudioManager.RINGER_MODE_SILENT;
import static android.media.AudioManager.RINGER_MODE_VIBRATE;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    private AudioManager myAudioManager;
    GoogleApiClient mGoogleApiClient;
    MyDatabase mydb = new MyDatabase(MainActivity.this);
    ImageButton on_button, off_button;
    TextView user_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        on_button = (ImageButton) findViewById(R.id.on_button);
        off_button = (ImageButton) findViewById(R.id.off_button);
        user_msg = (TextView) findViewById(R.id.user_msg);
//        startService(new Intent(this, MyService.class));
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(MainActivity.this, "Connection failed", Toast.LENGTH_SHORT).show();
                    }
                }).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();
        if(mydb.getSettingsData("Settings", "manual").equals("yes"))
        {
            if (mydb.getSettingsData("Settings", "Mode").equals("Meeting"))
            {
                off_button.setVisibility(View.VISIBLE);
                on_button.setVisibility(View.GONE);
                user_msg.setText("Click the button to put your mobile in normal mode");
                silentModeOn();
            }
            else if (mydb.getSettingsData("Settings", "Mode").equals("Outdoor"))
            {
                off_button.setVisibility(View.VISIBLE);
                on_button.setVisibility(View.GONE);
                user_msg.setText("Click the button to put your mobile in normal mode");
                vibratetModeOn();
            }

        }
        else
        {
            if (mydb.getSettingsData("Settings", "Mode").equals("Meeting"))
            {
                on_button.setVisibility(View.VISIBLE);
                off_button.setVisibility(View.GONE);
                user_msg.setText("Click the button to put your mobile in silent mode");
                normalModeOn();
            }
            else
            {
                on_button.setVisibility(View.VISIBLE);
                off_button.setVisibility(View.GONE);
                user_msg.setText("Click the button to put your mobile in vibrate mode");
                normalModeOn();
            }

        }
        on_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.updateSettings("Settings", "manual", "yes");
                if (mydb.getSettingsData("Settings", "Mode").equals("Meeting"))
                {
                    off_button.setVisibility(View.VISIBLE);
                    on_button.setVisibility(View.GONE);
                    user_msg.setText("Click the button to put your mobile in normal mode");
                    silentModeOn();
                }
                else if (mydb.getSettingsData("Settings", "Mode").equals("Outdoor"))
                {
                    off_button.setVisibility(View.VISIBLE);
                    on_button.setVisibility(View.GONE);
                    user_msg.setText("Click the button to put your mobile in normal mode");
                    vibratetModeOn();
                }
            }
        });
        off_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.updateSettings("Settings", "manual", "no");
                mydb.truncateCallers();
                if (mydb.getSettingsData("Settings", "Mode").equals("Meeting"))
                {
                    on_button.setVisibility(View.VISIBLE);
                    off_button.setVisibility(View.GONE);
                    user_msg.setText("Click the button to put your mobile in silent mode");
                    normalModeOn();
                }
                else
                {
                    on_button.setVisibility(View.VISIBLE);
                    off_button.setVisibility(View.GONE);
                    user_msg.setText("Click the button to put your mobile in vibrate mode");
                    normalModeOn();
                }
            }
        });

        Intent intent = new Intent(MainActivity.this, OutgoingCallReceiver.class);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting : Intent intent = new Intent(MainActivity.this, Settings.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
            case R.id.action_logout : if (firebaseAuth.getCurrentUser() == null)
            {
                Toast.makeText(MainActivity.this, "Kindly login with your google account", Toast.LENGTH_LONG).show();
                break;
            }
                else
            {
                firebaseAuth.signOut();
                Auth.GoogleSignInApi.signOut(mGoogleApiClient);
            }
            default : return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void silentModeOn()
    {
        myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        myAudioManager.setRingerMode(RINGER_MODE_SILENT);
        if (myAudioManager.getRingerMode() == RINGER_MODE_SILENT)
            Toast.makeText(MainActivity.this, "In silent mode", Toast.LENGTH_SHORT).show();
    }



    public void vibratetModeOn()
    {
        myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        myAudioManager.setRingerMode(RINGER_MODE_VIBRATE);
        if (myAudioManager.getRingerMode() == RINGER_MODE_VIBRATE)
            Toast.makeText(MainActivity.this, "In vibrate mode", Toast.LENGTH_SHORT).show();
    }

    public void normalModeOn()
    {
        myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        myAudioManager.setRingerMode(RINGER_MODE_NORMAL);
        myAudioManager.setStreamVolume(AudioManager.STREAM_RING,myAudioManager.getStreamMaxVolume(AudioManager.STREAM_RING),0);
        if (myAudioManager.getRingerMode() == RINGER_MODE_NORMAL)
            Toast.makeText(MainActivity.this, "In ringing mode", Toast.LENGTH_SHORT).show();
    }



}


