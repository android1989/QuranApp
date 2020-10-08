package com.xwaydesigns.morbamosquetrust;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    CardView mcardview1, mcardview2, mcardview3, mcardview4, mcardview5 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mcardview1 = findViewById(R.id.cardview1);
        mcardview2 = findViewById(R.id.cardview2);
        mcardview3 = findViewById(R.id.cardview3);
        mcardview4 = findViewById(R.id.cardview4);
        mcardview5 = findViewById(R.id.cardview5);

        //-------------------------------------------------------\\
        mcardview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this,PrayerTimeActivity.class);
                startActivity(intent);
            }
        });
        //-------------------------------------------------------\\

        //-------------------------------------------------------\\
        mcardview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,QiblaDirectionActivity.class);
                startActivity(intent);
            }
        });
        //-------------------------------------------------------\\

        //-------------------------------------------------------\\
        mcardview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this,DonationBoxActivity.class);
                startActivity(intent);
            }
        });
        //-------------------------------------------------------\\

        //-------------------------------------------------------\\
        mcardview4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this,SurahActivity.class);
                startActivity(intent);
            }
        });
        //-------------------------------------------------------\\

        //-------------------------------------------------------\\
        mcardview5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this,MasjidGalleryActivity.class);
                startActivity(intent);
            }
        });
        //-------------------------------------------------------\\

    }

    @Override
    public void onBackPressed() {
        ExitApp();
    }

    private void ExitApp() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(getString(R.string.app_name))
                .setMessage("Do you want to Exit App ?")
                .setIcon(R.drawable.app_icon_48)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();

                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .show();
    }
}