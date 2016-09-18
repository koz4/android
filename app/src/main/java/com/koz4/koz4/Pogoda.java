package com.koz4.koz4;

import com.firebase.client.Firebase;

/**
 * Created by Marek on 9/18/2016.
 */
public class Pogoda extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

    }
}
