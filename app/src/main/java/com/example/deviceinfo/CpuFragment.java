package com.example.deviceinfo;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.an.deviceinfo.device.DeviceInfo;

import java.io.IOException;
import java.io.InputStream;

public class CpuFragment extends Fragment {


    TextView hardware;


    ProcessBuilder processBuilder;
    String cpu_details = "";
    String[] DATA = {"/system/bin/cat", "/proc/cpuinfo"};
    InputStream inputStream;
    Process process ;
    byte[] byteArray=new byte[1024] ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cpu, container, false);


        hardware=view.findViewById(R.id.id_hardware);


        try{
            processBuilder = new ProcessBuilder(DATA);

            process = processBuilder.start();

            inputStream = process.getInputStream();

            while(inputStream.read(byteArray) != -1){

                cpu_details = cpu_details + new String(byteArray);
            }

            inputStream.close();

        } catch(IOException ex){

            ex.printStackTrace();
        }



hardware.setText(cpu_details+"");



    return  view;
    }



}