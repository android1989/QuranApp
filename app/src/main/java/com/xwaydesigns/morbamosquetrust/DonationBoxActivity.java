package com.xwaydesigns.morbamosquetrust;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class DonationBoxActivity extends AppCompatActivity {

    ImageView donate_btn;
    EditText donation_amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_box);

        donate_btn = findViewById(R.id.donate_now_button);
        donation_amount = findViewById(R.id.donation_amount_edittext);
    }

    public void DonateNow(View view)
    {

    }
}