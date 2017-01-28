package com.example.yoshi.decisivealert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by yoshi on 12/1/2016.
 */

public class SetContacts extends Activity {
    MyDatabase mydb;
    TextView nobody;
    TextView customContacts;
    TextView editList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_contacts);
        nobody = (TextView) findViewById(R.id.nobody);
        customContacts = (TextView) findViewById(R.id.customContacts);
        editList = (TextView) findViewById(R.id.editList);
        mydb = new MyDatabase(SetContacts.this);
        //Toast.makeText(this, "Database created", Toast.LENGTH_SHORT).show();

//        Toast.makeText(this, "nobody inserted", Toast.LENGTH_SHORT).show();
        nobody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.updateData("Calls", "1", "nobody");
                //String callValue = mydb.getValue("Calls");
                Intent intent = new Intent(SetContacts.this, Settings.class);
                //intent.putExtra("callNobody", callValue);
                startActivity(intent);
            }
        });
        customContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.updateData("Calls", "1", "custom");

//                String callValue = mydb.getValue("Calls");

                editList.setVisibility(View.VISIBLE);


            }
        });
        editList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetContacts.this, CustomContactsSample.class);
                startActivity(intent);
            }
        });

    }
}
