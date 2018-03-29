package com.itslp.cafeteria.cafetaAdministrador;

import android.app.Application;

import com.onesignal.OneSignal;

public class Launch extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.startInit(this).init();
    }
}
