package com.example.wifiqrcodescanner.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.example.wifiqrcodescanner.R;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.banner.BannerView;

public class MainActivity extends AppCompatActivity {

    private static final int MY_CAMERA_REQUEST_CODE = 101;
    private CodeScanner mCodeScanner;
    private boolean mPermissionGranted;
    ImageView btn_scan;
    public static TextView tvresult;
    ImageView imageView;
    RelativeLayout tool;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_scan=findViewById(R.id.btnScan);
        imageView=findViewById(R.id.iv_main);
        tool=findViewById(R.id.toolbar);








        // Obtain BannerView.
        BannerView bannerView = findViewById(R.id.hw_banner_view);
        // Set the ad unit ID and ad dimensions. "testw6vs28auh3" is a dedicated test ad unit ID.
        bannerView.setAdId("testw6vs28auh3");
        bannerView.setBannerAdSize(BannerAdSize.BANNER_SIZE_360_57);
        // Set the refresh interval to 30 seconds.
        bannerView.setBannerRefresh(30);
        // Create an ad request to load an ad.
        AdParam adParam = new AdParam.Builder().build();
        bannerView.loadAd(adParam);

        try {

            checkPermission(Manifest.permission.CAMERA, MY_CAMERA_REQUEST_CODE);

        }catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }



        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                startActivity(new Intent(MainActivity.this,ScanActivity.class));


            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //MainActivity.this.openOptionsMenu();
                myCustomPopup(v);


            }
        });

    }






   /*     @Override
        public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        }*/

        public void myCustomPopup(View v){
            PopupMenu popupMenu = new PopupMenu(this, v);
            MenuInflater inflater = popupMenu.getMenuInflater();
            inflater.inflate(R.menu.menu,popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.More_Apps:

                            Toast.makeText(MainActivity.this, "More Clicked", Toast.LENGTH_SHORT).show();
                        case R.id.Rate:

                            Toast.makeText(MainActivity.this, "Rate Clicked", Toast.LENGTH_SHORT).show();
                            break;
                    }

                    return true;
                }
            });

            popupMenu.show();
        }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == MY_CAMERA_REQUEST_CODE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
//            }
//        }
//    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void checkPermission(String permission, int requestCode)
    {

        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { permission }, requestCode);
        }
        else {
            Toast.makeText(MainActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera Permission Granted", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "camera Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }










    @Override
    protected void onResume() {
        super.onResume();
        if (mPermissionGranted) {
            mCodeScanner.startPreview();
        }
    }

    @Override
    protected void onPause() {
//        mCodeScanner.releaseResources();
        super.onPause();
    }
}





//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == MY_CAMERA_REQUEST_CODE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
//            }
//        }
//    }



  /*  @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.More_Apps:

                Toast.makeText(this, "More Clicked", Toast.LENGTH_SHORT).show();
            case R.id.Rate:

                Toast.makeText(this, "Rate Clicked", Toast.LENGTH_SHORT).show();

                break;
        }
        return true;
    }*/
