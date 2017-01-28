package com.example.yoshi.decisivealert;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.phoneNumber;

/**
 * Created by yoshi on 12/1/2016.
 */

public class Settings extends Activity implements NumberPicker.OnValueChangeListener{
    MyDatabase mydb = new MyDatabase(Settings.this);
    TextView numCalls1, numCalls2;
    TextView calls1, calls2, calls3;
    TextView msg1, msg2, msg3;
    Switch sw1;
    EditText smsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_home);
        calls1 = (TextView) findViewById(R.id.calls1);
        calls2 = (TextView) findViewById(R.id.calls2);
        calls3 = (TextView) findViewById(R.id.calls3);
        msg1 = (TextView) findViewById(R.id.msg1);
        msg2 = (TextView) findViewById(R.id.msg2);
        msg3 = (TextView) findViewById(R.id.msg3);
        numCalls1 = (TextView) findViewById(R.id.numCalls1);
        numCalls2 = (TextView) findViewById(R.id.numCalls2);
        sw1 = (Switch) findViewById(R.id.sms_switch);
        smsText = (EditText) findViewById(R.id.smsText);
        smsText.setText(mydb.getValue("SMSText"));
        calls1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, SetContacts.class);
                startActivity(intent);
            }
        });
        calls2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, SetContacts.class);
                startActivity(intent);
            }
        });
        calls3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, SetContacts.class);
                startActivity(intent);
            }
        });
        msg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, SetMessages.class);
                startActivity(intent);
            }
        });
        msg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, SetMessages.class);
                startActivity(intent);
            }
        });
        numCalls2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        numCalls1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                      mydb.updateData("SendSMS", "1", "yes");
//                      sendSMS("9989415870", smsText.getText().toString());
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms: 9573762714"));
//                    intent.putExtra("sms_body", "Busy...");
//                    startActivity(intent);
                }
                else
                {
                    mydb.updateData("SendSMS", "1", "no");
                }
            }
        });


        TextWatcher textWatcher = new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                mydb.updateData("SMSText", "1", smsText.getText().toString());
//                Toast.makeText(Settings.this, mydb.getValue("SMSText"), Toast.LENGTH_LONG).show();
            }
        };
        smsText.addTextChangedListener(textWatcher);


        String callValue = mydb.getValue("Calls");
        String msgValue = mydb.getValue("Messages");
//        String sendSMS = mydb.getValue("SendSMS");

        calls3.setText(callValue);
        msg3.setText(msgValue);
        numCalls2.setText(mydb.getValue("NumCalls") + " > ");
        Log.d("hhhh", mydb.getValue("SendSMS"));
        if (mydb.getValue("SendSMS").equals("yes")) {
            sw1.setChecked(true);
        }

    }
    protected void sendSMS(String contacts, String msg) {

        //for (int i = 0; i < contacts.size(); i++) {

            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(contacts, null, msg, null, null);
                Toast.makeText(getApplicationContext(), "Message Sent",
                        Toast.LENGTH_LONG).show();
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), ex.getMessage(),
                        Toast.LENGTH_LONG).show();
                ex.printStackTrace();
            }

        }

    public void show()
    {

        final Dialog d = new Dialog(Settings.this);
        d.setTitle("NumberPicker");

        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(15); // max value 100
        np.setMinValue(2);   // min value 0
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int value = np.getValue(); //set the value to textview
                mydb.updateData("NumCalls", "1",String.valueOf(np.getValue()));
               // Log.d("hhhhh", String.valueOf(np.getValue()));
                numCalls2.setText(String.valueOf(np.getValue()) + " > ");
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();


    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }
}
