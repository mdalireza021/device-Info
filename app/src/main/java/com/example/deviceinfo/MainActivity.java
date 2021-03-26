package com.example.deviceinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.WindowManager;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        chipNavigationBar=findViewById(R.id.id_bottom_navigation);


        if (savedInstanceState==null)
        {
            DeviceFragment deviceFragment=new DeviceFragment();
            chipNavigationBar.setItemSelected(R.id.id_device,true);
            getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_layout,deviceFragment).commit();


        }


        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {

                Fragment fragment=null;
                switch (i)
                {

                    case R.id.id_device:
                        fragment=new DeviceFragment();
                        break;
                    case R.id.ic_cpu:
                        fragment=new CpuFragment();
                        break;
                    case R.id.id_battery:
                        fragment=new BatteryFragment();
                        break;
                    case R.id.id_memory:
                        fragment=new MemoryFragment();
                        break;
                    case R.id.id_network:
                        fragment=new NetworkFragment();
                        break;
                    case R.id.id_about:
                        fragment=new AboutFragment();
                        break;

                }
                if (fragment!=null)
                {

                    getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_layout,fragment).commit();

                }

            }
        });

    }
}