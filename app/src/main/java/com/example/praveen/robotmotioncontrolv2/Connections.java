package com.example.praveen.robotmotioncontrolv2;

import android.bluetooth.BluetoothAdapter;

/**
 * Created by PRAVEEN on 05-01-2018.
 */

public class Connections {
    private static boolean state = false;
    public static boolean blueTooth() {

        BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();
        if (!bluetooth.isEnabled()) {
            //System.out.println("Bluetooth is Disable...");
            state = true;
        } else if (bluetooth.isEnabled()) {
            String address = bluetooth.getAddress();
            String name = bluetooth.getName();
            //System.out.println(name + " : " + address);
            state = false;
        }
        return state;
    }
}
