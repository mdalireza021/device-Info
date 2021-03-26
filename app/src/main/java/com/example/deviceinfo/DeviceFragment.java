package com.example.deviceinfo;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.an.deviceinfo.device.DeviceInfo;

public class DeviceFragment extends Fragment {

    TextView model,manufacture,board,brand,hardware,bootloader,build_id,kernel,android_version,api_level,security_patch,display,resolution,root_access,system_uptime;

    int width,height;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.fragment_device, container, false);

        DeviceInfo deviceInfo=new DeviceInfo(getContext());





        model=view.findViewById(R.id.id_model);
        manufacture=view.findViewById(R.id.id_manufacture);
        board=view.findViewById(R.id.id_board);
        brand=view.findViewById(R.id.id_brand);

        hardware=view.findViewById(R.id.id_hardware);
        bootloader=view.findViewById(R.id.id_bootloader);
        build_id=view.findViewById(R.id.id_build_id);
        kernel=view.findViewById(R.id.id_kernel_version);
        android_version=view.findViewById(R.id.id_android_version);
        api_level=view.findViewById(R.id.id_api_level);
        security_patch=view.findViewById(R.id.id_security_patch_level);
        display=view.findViewById(R.id.id_display);
        resolution=view.findViewById(R.id.id_resolution);
        root_access=view.findViewById(R.id.id_root_access);



        manufacture.setText(deviceInfo.getManufacturer()+"");
        brand.setText(deviceInfo.getBuildBrand()+"");
        model.setText(deviceInfo.getModel()+"");
        board.setText(deviceInfo.getBoard()+"");
        hardware.setText(deviceInfo.getHardware()+"");

        bootloader.setText(Build.BOOTLOADER+"");
        build_id.setText(Build.DISPLAY+"");
        kernel.setText(System.getProperty("os.version"));

        android_version.setText(deviceInfo.getOSVersion());
        api_level.setText(Build.VERSION.SDK_INT+"");
        security_patch.setText(Build.VERSION.SECURITY_PATCH+"");


        String[] dimens=getDimension();

        display.setText(dimens[2]+" inches");
        resolution.setText(dimens[0]+"*"+dimens[1]+" pixels");


        if (deviceInfo.isDeviceRooted()==true)
        {
            root_access.setText("Yes");
        }
        else
        {
            root_access.setText("No");
        }








        return  view;
    }



    private String[] getDimension(){
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int dens = dm.densityDpi;
        double wi = (double)width / (double)dens;
        double hi = (double)height / (double)dens;
        double x = Math.pow(wi, 2);
        double y = Math.pow(hi, 2);
        double screenInches = Math.sqrt(x+y);

        String[] screenInformation = new String[3];
        screenInformation[0] = width +"";
        screenInformation[1] = height+"" ;
        screenInformation[2] = String.format("%.2f", screenInches);

        return screenInformation;
    }
}