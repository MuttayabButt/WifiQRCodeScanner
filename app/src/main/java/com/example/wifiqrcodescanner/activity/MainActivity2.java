package com.example.wifiqrcodescanner.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wifiqrcodescanner.R;

public class MainActivity2 extends AppCompatActivity {

    public static TextView tvvvv;
    Button btnWifi,btn_rate;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvvvv = findViewById(R.id.tvShow);
        btnWifi = findViewById(R.id.btn_Wifi);
        btn_rate = findViewById(R.id.btn_rate);
        imageView =findViewById(R.id.iv_main2);


        Intent intent = getIntent();
        String resl = intent.getStringExtra("message");


        tvvvv.setText(resl);


        btnWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                startActivity(intent);


            }
        });

        btn_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity2.this, "Rate", Toast.LENGTH_SHORT).show();


            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myCustomPopup(v);
            }
        });


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

                            Toast.makeText(MainActivity2.this, "More Clicked", Toast.LENGTH_SHORT).show();
                        case R.id.Rate:

                            Toast.makeText(MainActivity2.this, "Rate Clicked", Toast.LENGTH_SHORT).show();
                            break;
                    }

                    return true;
                }
            });

            popupMenu.show();
        }
        
    }
