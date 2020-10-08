package com.xwaydesigns.morbamosquetrust;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xwaydesigns.morbamosquetrust.Databases.DatabaseAccess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

public class PrayerTimeActivity extends AppCompatActivity {

    String date, fazr,sunrise,dhuhr,asr,maghrib,isha;
    TextView current_date,fazr_text,sunrise_text,dhuhr_text,asr_text,maghrib_text,isha_text;
    TextView next_prayer;
    HashMap<String,String> user = new HashMap<String,String>();
    public static final String DATE_FORMAT = "dd-MMM-yyyy";
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_time);
        current_date = findViewById(R.id.current_date);
        fazr_text = findViewById(R.id.fazr_time);
        sunrise_text = findViewById(R.id.sunrise_time);
        dhuhr_text = findViewById(R.id.dhuhr_time);
        asr_text = findViewById(R.id.asar_time);
        maghrib_text = findViewById(R.id.maghrib_time);
        isha_text = findViewById(R.id.isha_time);
        next_prayer = findViewById(R.id.next_prayer);

        DatabaseAccess db_access = DatabaseAccess.getInstance(getApplicationContext());
        db_access.openDatabase();

        user = db_access.getPrayers();
        db_access.closeDatabase();

       // Toast.makeText(PrayerTimeActivity.this,"prayers:"+ prayers,Toast.LENGTH_SHORT).show();
         date =  user.get("date");
         fazr =  user.get("fazr");
         sunrise =  user.get("sunrise");
         dhuhr =  user.get("dhuhr");
         asr =  user.get("asr");
         maghrib =  user.get("maghrib");
         isha =  user.get("isha");

        //setting textview
       // current_date.setText(date);
        fazr_text.setText(fazr);
        sunrise_text.setText(sunrise);
        dhuhr_text.setText(dhuhr);
        asr_text.setText(asr);
        maghrib_text.setText(maghrib);
        isha_text.setText(isha);

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date today = Calendar.getInstance().getTime();
        current_date.setText(dateFormat.format(today));

        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
        String currentTime = sdf.format(d);

       // Toast.makeText(PrayerTimeActivity.this,"Current Time:"+ currentTime ,Toast.LENGTH_LONG).show();
   //-----------------------------------------------------------------------------------------------------------\\

        SimpleDateFormat parser = new SimpleDateFormat("hh:mm a",Locale.ENGLISH);
        Date prayer_time = null;
        try {
            prayer_time = parser.parse(fazr);
            Date current_time = parser.parse(currentTime);
            if (current_time.before(prayer_time))//1
            {
                //before fazr time
              //  Toast.makeText(PrayerTimeActivity.this,"before FAZR" + currentTime ,Toast.LENGTH_LONG).show();
                next_prayer.setText("FAJR"+" "+fazr);

            }
            else if(current_time.after(prayer_time))//1
            {
               // Toast.makeText(PrayerTimeActivity.this,"after FAZR"+ currentTime ,Toast.LENGTH_LONG).show();
               //check for sunrise
                prayer_time = parser.parse( sunrise);
                if(current_time.before(prayer_time))//2
                {
                    //before sunrise time
                  //  Toast.makeText(PrayerTimeActivity.this,"before SUNRISE" + currentTime ,Toast.LENGTH_LONG).show();
                    next_prayer.setText("SUNRISE"+" "+sunrise);
                }
                else if(current_time.after(prayer_time))//2
                {
                  //  Toast.makeText(PrayerTimeActivity.this,"after SUNRISE"+ currentTime ,Toast.LENGTH_LONG).show();
                    //check for dhuhr
                    prayer_time = parser.parse( dhuhr);
                    if(current_time.before(prayer_time))//3
                    {
                        //before dhuhr time
                      //  Toast.makeText(PrayerTimeActivity.this,"before DHUHR" + currentTime ,Toast.LENGTH_LONG).show();
                        next_prayer.setText("DHUHR"+" "+dhuhr);
                    }
                    else if(current_time.after(prayer_time))//3
                    {
                      //  Toast.makeText(PrayerTimeActivity.this, "after Dhuhr" + currentTime, Toast.LENGTH_LONG).show();
                        //check for dhuhr
                        prayer_time = parser.parse(asr);
                       if(current_time.before(prayer_time))//4
                       {
                           //before asr
                        //   Toast.makeText(PrayerTimeActivity.this,"before ASR" + currentTime ,Toast.LENGTH_LONG).show();
                           next_prayer.setText("ASR"+" "+asr);
                       }
                       else if(current_time.after(prayer_time))//4
                       {
                         //  Toast.makeText(PrayerTimeActivity.this, "after ASR" + currentTime, Toast.LENGTH_LONG).show();
                           //check for Maghrib
                           prayer_time = parser.parse(maghrib);
                           if(current_time.before(prayer_time))//5
                           {
                               //before maghrib
                             //  Toast.makeText(PrayerTimeActivity.this,"before Maghrib" + currentTime ,Toast.LENGTH_LONG).show();
                               next_prayer.setText("MAGHRIB"+" "+maghrib);
                           }
                           else if(current_time.after(prayer_time))//5
                           {
                             //  Toast.makeText(PrayerTimeActivity.this, "after MAGHRIB" + currentTime, Toast.LENGTH_LONG).show();
                               //check for Maghrib
                               prayer_time = parser.parse(isha);
                               if(current_time.before(prayer_time))//6
                               {
                                   //before isha
                                //  Toast.makeText(PrayerTimeActivity.this,"before ISHA" + currentTime ,Toast.LENGTH_LONG).show();
                                   next_prayer.setText("ISHA"+" "+isha);
                               }
                               else if(current_time.after(prayer_time))//6
                               {
                                   //after isha
                                 //  Toast.makeText(PrayerTimeActivity.this,"after ISHA" + currentTime ,Toast.LENGTH_LONG).show();
                                   next_prayer.setText("FAJR"+" "+fazr);
                               }

                               else
                               {
                                 // Toast.makeText(PrayerTimeActivity.this,"now ISHA"+ currentTime ,Toast.LENGTH_LONG).show();
                                   next_prayer.setText("FAJR"+" "+fazr);
                               }
                           }
                           else//5
                           {
                             //  Toast.makeText(PrayerTimeActivity.this,"now MAGHRIB"+ currentTime ,Toast.LENGTH_LONG).show();
                               next_prayer.setText("ISHA"+" "+isha);
                           }
                       }
                       else//4
                       {
                         // Toast.makeText(PrayerTimeActivity.this,"now ASR"+ currentTime ,Toast.LENGTH_LONG).show();
                           next_prayer.setText("MAGHRIB"+" "+maghrib);
                       }

                    }
                    else//3
                    {
                     //  Toast.makeText(PrayerTimeActivity.this,"now Dhuhr"+ currentTime ,Toast.LENGTH_LONG).show();
                        next_prayer.setText("ASR"+" "+asr);

                    }

                }
                else//2
                {
                  //  Toast.makeText(PrayerTimeActivity.this,"now Sunrise"+ currentTime ,Toast.LENGTH_LONG).show();
                    next_prayer.setText("DHUHR"+" "+dhuhr);
                }
            }
            else//1
            {
              //  Toast.makeText(PrayerTimeActivity.this,"now FAZR"+ currentTime ,Toast.LENGTH_LONG).show();
                next_prayer.setText("SUNRISE"+" "+sunrise);
            }

        } catch (ParseException e) {
            // Invalid date was entered
            Log.e("fetch JSONException ", String.valueOf(e));
            e.printStackTrace();
            displayExceptionMessage1(e.getMessage());
        }

    }

    public void displayExceptionMessage1(String msg) {
        Toast.makeText(PrayerTimeActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
    }

}