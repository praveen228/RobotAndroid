package com.example.praveen.robotmotioncontrolv2;

/**
 * Created by PRAVEEN on 14-12-2017.
 */

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
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


public class BluetoothTab extends Fragment implements View.OnClickListener{

    private TextView mTextMessage;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    private View rootView;
    ListView lv;
    String msg = "Android Log: ";
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.bluetooth_tab, container, false);
        Button b_on = rootView.findViewById(R.id.on);
        Button b_off = rootView.findViewById(R.id.off);
        Button b_disc = rootView.findViewById(R.id.discover);
        Button b_list = rootView.findViewById(R.id.list);
        b_on.setOnClickListener(this);
        b_off.setOnClickListener(this);
        b_disc.setOnClickListener(this);
        b_list.setOnClickListener(this);
        return rootView;
    }
    //Initializes Bluetooth Adapter
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BA = BluetoothAdapter.getDefaultAdapter();

    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        context = activity;
    }

    @Override
    public void onStart() {

        super.onStart();
        //Check if Bluetooth available in device.
        if(BA == null){
            Log.d(msg," NO Bluetooth Available");
        }
        else {
            Log.d(msg,"Bluetooth Available");
        }
    }
    @Override
    public void onClick(View v){
        switch(v.getId()) {
            case R.id.on:
                if (!BA.isEnabled()) {
                    Log.d(msg, "Button Pressed");
                    Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(turnOn, 0);
                    Toast.makeText(getActivity().getApplicationContext(), "Turned on", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.off:
                BA.disable();
                Toast.makeText(getActivity().getApplicationContext(), "Turned off" ,Toast.LENGTH_LONG).show();
            break;
            case R.id.discover:
                Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivityForResult(getVisible, 0);
            break;
            case R.id.list:
                pairedDevices = BA.getBondedDevices();
                Log.d(msg, "Paired devices");
                ArrayList list = new ArrayList();
                lv = (ListView) rootView.findViewById(R.id.listView1);
                for (BluetoothDevice bt : pairedDevices)
                    list.add(bt.getName());
                Toast.makeText(context, "Showing Paired Devices", Toast.LENGTH_SHORT).show();
                ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, list);
                Log.d(msg,"Done");
                lv.setAdapter(adapter);
            break;
        }
    }
}
