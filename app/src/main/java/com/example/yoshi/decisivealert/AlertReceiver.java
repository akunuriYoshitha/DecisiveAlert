package com.example.yoshi.decisivealert;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

/**
 * Created by yoshi on 12/10/2016.
 */
public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        show(context);
    }
    public void show(Context context)
    {

        final Dialog d = new Dialog(context);
        d.setTitle("NumberPicker");

        d.setContentView(R.layout.dialog);
        Button set = (Button) d.findViewById(R.id.set_number_picker);
        Button cancel = (Button) d.findViewById(R.id.cancel_number_picker);
//        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
//        np.setMaxValue(15); // max value 100
//        np.setMinValue(2);   // min value 0
//        np.setWrapSelectorWheel(false);
//        np.setOnValueChangedListener(context);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                int value = np.getValue(); //set the value to textview
//                mydb.updateData("NumCalls", "1",String.valueOf(np.getValue()));
//                // Log.d("hhhhh", String.valueOf(np.getValue()));
//                numCalls2.setText(String.valueOf(np.getValue()) + " > ");
//                d.dismiss();
//            }
//        });
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                d.dismiss();
//            }
//        });
//        d.show();


    }
}
