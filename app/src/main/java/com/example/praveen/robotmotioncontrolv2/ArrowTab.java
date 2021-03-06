package com.example.praveen.robotmotioncontrolv2;

import android.bluetooth.BluetoothAdapter;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import android.widget.Button;
import android.app.Activity;
import android.content.Context;
import android.bluetooth.BluetoothDevice;
/**
 * Created by PRAVEEN on 14-12-2017.
 */
//Implements Click listening on the View.
public class ArrowTab extends Fragment implements View.OnClickListener{
    private View rootView;
    Context context;
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
    String deviceName, deviceHardwareAddress;
    BluetoothDevice praveen;
    //Interface Method
    OnHeadlineSelectedListener mCallback;
    public interface OnHeadlineSelectedListener{
        public void sendDataToCar(Datapacket packet);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflates the view on the creation of the fragment
        rootView = inflater.inflate(R.layout.arrow_tab, container, false);
        //finds each button on the view
        Button b_up = rootView.findViewById(R.id.UP);
        Button b_down = rootView.findViewById(R.id.DOWN);
        Button b_left = rootView.findViewById(R.id.LEFT);
        Button b_right = rootView.findViewById(R.id.RIGHT);
        //Assigns the buttons for the Listening activity
        b_up.setOnClickListener(this);
        b_down.setOnClickListener(this);
        b_left.setOnClickListener(this);
        b_right.setOnClickListener(this);
        for (BluetoothDevice device : pairedDevices) {
            praveen = device; // TO - DO Select From Multiple Options
        }
        return rootView;
    }
    //God knows what, doesn't work if this is removed.
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        context = activity;

        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+ " must implement OnHeadlineSelectedListener");
        }

    }
    //part of listener activity, executes when corresponding listener is clicked
    @Override
    public void onClick(View v){
        Datapacket newdata;
        switch(v.getId()) {
            case R.id.UP:
                Toast.makeText(context,"UP1",Toast.LENGTH_SHORT).show();
                newdata = new Datapacket(Integer.toString(100),Integer.toString(600));
                mCallback.sendDataToCar(newdata);
                break;
            case R.id.DOWN:
                Toast.makeText(context,"DOWN",Toast.LENGTH_SHORT).show();
                newdata = new Datapacket(Integer.toString(200),Integer.toString(500));
                mCallback.sendDataToCar(newdata);
                break;
            case R.id.LEFT:
                Toast.makeText(context,"LEFT",Toast.LENGTH_SHORT).show();
                newdata = new Datapacket(Integer.toString(300),Integer.toString(900));
                mCallback.sendDataToCar(newdata);
                break;
            case R.id.RIGHT:
                Toast.makeText(context,"RIGHT",Toast.LENGTH_SHORT).show();
                newdata = new Datapacket(Integer.toString(400),Integer.toString(200));
                mCallback.sendDataToCar(newdata);
                break;
        }
    }
}
