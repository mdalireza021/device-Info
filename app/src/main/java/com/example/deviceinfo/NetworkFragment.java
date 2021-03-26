package com.example.deviceinfo;

import android.annotation.SuppressLint;
import android.app.admin.DeviceAdminInfo;
import android.content.Context;
import android.net.Network;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.an.deviceinfo.device.DeviceInfo;
import com.an.deviceinfo.device.model.Device;

import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import static android.content.Context.TELEPHONY_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class NetworkFragment extends Fragment {

TextView imei,imsi,phn_type,operator,nfc_present,nfc_enable,wifi_enable,mac_address,ip_address;
    @SuppressLint("HardwareIds")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_network, container, false);
        DeviceInfo deviceInfo=new DeviceInfo(getActivity());

        TelephonyManager telephonyManager = (TelephonyManager)getActivity().getSystemService(Context.TELEPHONY_SERVICE);

        imei=view.findViewById(R.id.id_imei);
        imsi=view.findViewById(R.id.id_imsi);
        phn_type=view.findViewById(R.id.id_phone_type);
        operator=view.findViewById(R.id.id_operator);
        nfc_present=view.findViewById(R.id.id_nfc_present);
        nfc_enable=view.findViewById(R.id.id_nfc_enable);
        wifi_enable=view.findViewById(R.id.id_wifi_enable);
        mac_address=view.findViewById(R.id.id_mac_address);
        ip_address=view.findViewById(R.id.id_ip_address);


        //imei.setText(String.format("%s", telephonyManager.getDeviceId()));

        imsi.setText(deviceInfo.getSerial()+"");
        phn_type.setText(deviceInfo.getPhoneType()+"");
        operator.setText(deviceInfo.getOperator()+"");
        nfc_present.setText(deviceInfo.isNfcPresent()+"");
        nfc_enable.setText(deviceInfo.isNfcEnabled()+"");
        wifi_enable.setText(deviceInfo.isWifiEnabled()+"");
        mac_address.setText(getMacAddress());


        WifiManager wifiManager = (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        int ip = wInfo.getIpAddress();
        String ipAddress = Formatter.formatIpAddress(ip);

        ip_address.setText(ipAddress);



    return  view;
    }


    public static String getMacAddress() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(Integer.toHexString(b & 0xFF) + ":");
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }








}

