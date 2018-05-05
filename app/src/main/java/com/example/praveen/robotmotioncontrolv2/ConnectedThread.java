package com.example.praveen.robotmotioncontrolv2;

import android.app.Notification;
import android.bluetooth.BluetoothSocket;
import android.icu.util.Output;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.os.Handler;
/**
 * Created by PRAVEEN on 04-04-2018.
 */

public class ConnectedThread extends Thread {
    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;
    private Handler mHandler;

    public ConnectedThread(BluetoothSocket socket){
        mmSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;

        try{
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        }catch(IOException e){}
        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }

    public void run(){
        byte[] buffer = new byte[1024];
        int begin = 0;
        int bytes = 0;
        while(true){
            try{
                bytes += mmInStream.read(buffer, bytes,buffer.length - bytes);
                for(int i = begin; i < bytes; i++){
                    if(buffer[i] == "#".getBytes()[0]){
                        //mHandler.obtainMessage().sendToTarget();
                        mHandler.obtainMessage(1,begin,i,buffer).sendToTarget();
                        begin = i + 1;
                        if(i == bytes -1){
                            bytes = 0;
                            begin = 0;
                        }
                    }
                }
            }catch(IOException e){
                break;
            }
        }
    }
    public void write(byte[] bytes){
        try{
            mmOutStream.write(bytes);
        }catch(IOException e){}
    }
    public void cancel(){
        try{
            mmSocket.close();
        }catch (IOException e){}
    }
}

  //  void mHandler = new Handler(){
    //    @Override
      //  public void handleMessage(){
        //    byte[] writeBuf = (byte[]) msg.obj;
           // int begin = (int) msg.arg1;
          //  int end   = (int) msg.arg2;
           // switch(msg.what){
             //   case 1:
               //     String writeMessage = new String(writeBuf);
                 //   writeMessage = writeMessage.substring(begin,end);
                   // break;
            //}
        //}
    //}
