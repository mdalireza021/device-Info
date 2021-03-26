package com.example.deviceinfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.an.deviceinfo.device.DeviceInfo;

import static android.content.Context.BATTERY_SERVICE;

public class BatteryFragment extends Fragment {



    IntentFilter intentfilter;


    TextView battery_level, battery_health, battery_status, battery_voltage, battery_capacity, battery_tech, battery_temp, battery_power_source;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_battery, container, false);

        intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        DeviceInfo deviceInfo = new DeviceInfo(getContext());


        battery_level = view.findViewById(R.id.id_battery_level);
        battery_health = view.findViewById(R.id.id_battery_health);
        battery_status = view.findViewById(R.id.id_status);
        battery_voltage = view.findViewById(R.id.id_battery_voltage);
        battery_capacity = view.findViewById(R.id.id_battery_capacity);
        battery_tech = view.findViewById(R.id.id_battery_technology);
        battery_temp = view.findViewById(R.id.id_battery_temp);
        battery_power_source = view.findViewById(R.id.id_power_source);


        battery_level.setText(deviceInfo.getBatteryPercent() + "%");
        battery_health.setText(deviceInfo.getBatteryHealth() + "");
        battery_voltage.setText(deviceInfo.getBatteryVoltage() + " mV");
        battery_capacity.setText(getBatteryCapacity(getContext())+ "mAh");
        battery_tech.setText(deviceInfo.getBatteryTechnology() + "");
        battery_temp.setText(deviceInfo.getBatteryTemperature() + "°C");
        battery_power_source.setText(deviceInfo.getChargingSource() + "");
        getActivity().registerReceiver(broadcastreceiver,intentfilter);

        return view;

    }

    private BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int deviceStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);

            if(deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING){

                battery_status.setText("Charging");

            }

            if(deviceStatus == BatteryManager.BATTERY_STATUS_DISCHARGING){

                battery_status.setText("Discharging");

            }

            if (deviceStatus == BatteryManager.BATTERY_STATUS_FULL){

                battery_status.setText("Battery Full");

            }

            if(deviceStatus == BatteryManager.BATTERY_STATUS_UNKNOWN){

                battery_status.setText("Unknown");
            }


            if (deviceStatus == BatteryManager.BATTERY_STATUS_NOT_CHARGING){

                battery_status.setText("Not Charging");

            }

        }
    };


    public int getBatteryCapacity(Context context) {
        Object mPowerProfile;
        double batteryCapacity = 0;
        final String POWER_PROFILE_CLASS = "com.android.internal.os.PowerProfile";

        try {
            mPowerProfile = Class.forName(POWER_PROFILE_CLASS)
                    .getConstructor(Context.class)
                    .newInstance(context);

            batteryCapacity = (double) Class
                    .forName(POWER_PROFILE_CLASS)
                    .getMethod("getBatteryCapacity")
                    .invoke(mPowerProfile);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return (int) batteryCapacity;

    }

}