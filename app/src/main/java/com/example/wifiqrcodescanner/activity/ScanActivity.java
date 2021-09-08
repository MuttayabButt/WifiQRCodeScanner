package com.example.wifiqrcodescanner.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.NetworkSpecifier;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSpecifier;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.wifiqrcodescanner.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.Result;

import java.util.Arrays;
import java.util.List;

import static com.example.wifiqrcodescanner.activity.MainActivity.tvresult;
import static com.example.wifiqrcodescanner.activity.MainActivity2.tvvvv;

public class ScanActivity extends AppCompatActivity {


    RelativeLayout relativeLayout;
    private CodeScanner mCodeScanner;
    String networkSSID = "test";
    String networkPass = "pass";
    ImageView imageView_scan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        relativeLayout=findViewById(R.id.parentLayout);
        imageView_scan =findViewById(R.id.iv_scan);


        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.Q)
                    @Override
                    public void run() {

//                        Snackbar.make(relativeLayout, result.getText(), Snackbar.LENGTH_LONG)
//                                .setAction("Action", null).show();

//                        Toast.makeText(ScanActivity.this, result.getText(), Toast.LENGTH_LONG).show();


                        Intent intent =new Intent(ScanActivity.this, MainActivity2.class);
                  //      startActivity(new Intent(ScanActivity.this, MainActivity2.class));

                        Log.d("TAG", "run: " + result);


                        String resl = result.getText().trim();

                        intent.putExtra("message", resl);
                        startActivity(intent);





//                        tvvvv.setText(resl);



                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });

        imageView_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myCustomPopup(v);

            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }



    public void myCustomPopup(View v){
        PopupMenu popupMenu = new PopupMenu(this, v);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu,popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){
                    case R.id.More_Apps:

                        Toast.makeText(ScanActivity.this, "More Clicked", Toast.LENGTH_SHORT).show();
                    case R.id.Rate:

                        Toast.makeText(ScanActivity.this, "Rate Clicked", Toast.LENGTH_SHORT).show();
                        break;
                }

                return true;
            }
        });

        popupMenu.show();
    }










}