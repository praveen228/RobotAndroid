package com.example.praveen.robotmotioncontrolv2;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by PRAVEEN on 06-01-2018.
 */
//Private to be used
public class AcceptThread extends Thread {
    private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public AcceptThread(BluetoothDevice Device) {
        // Use a temporary object that is later assigned to mmServerSocket
        // because mmServerSocket is final.
        BluetoothSocket tmp = null;
        mmDevice = Device;
        try {
            if (mmDevice != null){
                tmp = Device.createRfcommSocketToServiceRecord(mmDevice.getUuids()[0].getUuid());
            }
            else tmp = null;
            // MY_UUID is the app's UUID string, also used by the client code.

        } catch (IOException e) {
            try {
                tmp = Device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        mmSocket = tmp;
    }
    public void run() {
        //BA.cancelDiscovery();
        // Keep listening until exception occurs or a socket is returned
        try {
            mmSocket.connect();
        } catch (IOException connectException) {
            // Log.e(TAG, "Socket's accept() method failed", e);
            try{
                mmSocket.close();
            }catch (IOException closeException){
                return;
            }
        }

        if (mmSocket != null) {
            // A connection was accepted. Perform work associated with
            // the connection in a separate thread.
            // manageMyConnectedSocket(socket);
            // mmServerSocket.close();
            //break;
            ConnectedThread mConnectedThread = new ConnectedThread(mmSocket);
            mConnectedThread.start();
            //mConnectedThread.write("A".getBytes());
            //mConnectedThread.write("L".getBytes());
        }

    }

    // Closes the connect socket and causes the thread to finish.
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) {
             //Log.e(TAG, "Could not close the connect socket", e);
        }
    }
}

