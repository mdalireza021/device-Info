package com.example.deviceinfo;

import android.app.ActivityManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.an.deviceinfo.device.DeviceInfo;

import static android.content.Context.ACTIVITY_SERVICE;

public class MemoryFragment extends Fragment {

    TextView total_ram,free_ram,total_internal,use_internal,free_internal,total_external,use_external,free_external;

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_memory, container, false);

        DeviceInfo deviceInfo=new DeviceInfo(getContext());

        total_ram=view.findViewById(R.id.id_total_ram);
        free_ram=view.findViewById(R.id.id_free_ram);
        total_internal=view.findViewById(R.id.id_total_internal);
        free_internal=view.findViewById(R.id.id_free_internal);

        use_internal=view.findViewById(R.id.id_use_internal);
        free_external=view.findViewById(R.id.id_free_external);
        total_external=view.findViewById(R.id.id_total_external);
        use_external=view.findViewById(R.id.id_use_external);


        long Total_Ram= (long) (deviceInfo.getTotalRAM() /(1000000));
        total_ram.setText(Total_Ram+" MB");
        free_ram.setText(freeRamMemorySize()+" MB");


        long Total_Internal=deviceInfo.getTotalInternalMemorySize()/(1000000000);
        total_internal.setText(Total_Internal+" GB");

        long Available_Internal=deviceInfo.getAvailableInternalMemorySize()/(1000000000);
        free_internal.setText(Available_Internal+" GB");

        use_internal.setText(Total_Internal-Available_Internal+" GB");

        boolean has_external=deviceInfo.hasExternalSDCard();

        if (has_external==false)
        {
            total_external.setText(deviceInfo.getTotalExternalMemorySize()+"MB");
            free_external.setText(deviceInfo.getAvailableExternalMemorySize()+"MB");
            use_external.setText(deviceInfo.getTotalExternalMemorySize()-deviceInfo.getAvailableExternalMemorySize()+"");

        }
        else
        {
            total_external.setText("0 GB");
            free_external.setText("0 GB");
            use_external.setText("0 GB");
        }



    return  view;

    }

    private long freeRamMemorySize() {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        long availableMegs = mi.availMem / 1048576L;

        return availableMegs;
    }
}