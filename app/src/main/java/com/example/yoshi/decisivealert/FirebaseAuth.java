package com.example.yoshi.decisivealert;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by yoshi on 2/17/2017.
 */

public class FirebaseAuth extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
