package com.example.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Broadcast extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("Ayush 5");
        Toast.makeText(context, "Alarm Triggered!", Toast.LENGTH_LONG).show();
        System.out.println("Ayush 55");
    }
}