package com.xwaydesigns.morbamosquetrust;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class QiblaDirectionActivity extends AppCompatActivity implements SensorEventListener
{

    ImageView compass;
  //  TextView txtDegrees;
    private static SensorManager manager;
    private static Sensor sensor;
    private float currentDegree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qibla_direction);

        compass = findViewById(R.id.qibla_finder);
       // txtDegrees = findViewById(R.id.textview);
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


    }

    @Override
    protected void onResume()
    {
// TODO: Implement this method
        super.onResume();
        manager.registerListener(this,manager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause()
    {
// TODO: Implement this method
        super.onPause();
        manager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {

        float degree=Math.round(sensorEvent.values[0]);
       // txtDegrees.setText("Rotation: "+Float.toString(degree)+" degrees");
        RotateAnimation ra=new RotateAnimation(currentDegree,-degree,Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        ra.setDuration(120);
        ra.setFillAfter(true);
        compass.startAnimation(ra);
        currentDegree=-degree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}