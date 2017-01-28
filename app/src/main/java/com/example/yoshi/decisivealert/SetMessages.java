package com.example.yoshi.decisivealert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yoshi on 12/1/2016.
 */

public class SetMessages extends Activity {
    MyDatabase mydb;
    TextView nobody, customContacts, editList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_contacts);
        nobody = (TextView) findViewById(R.id.nobody);
        customContacts = (TextView) findViewById(R.id.customContacts);
        editList = (TextView) findViewById(R.id.editList);
        mydb = new MyDatabase(SetMessages.this);
        nobody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.updateData("Messages", "1", "nobody");
                //String callValue = mydb.getValue("Calls");
                Intent intent = new Intent(SetMessages.this, Settings.class);
                //intent.putExtra("callNobody", callValue);
                startActivity(intent);
            }
        });
        customContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.updateData("Messages", "1", "custom");

//                String callValue = mydb.getValue("Calls");

                editList.setVisibility(View.VISIBLE);


            }
        });
        editList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetMessages.this, CustomContactsSample.class);
                startActivity(intent);
            }
        });
    }
}
