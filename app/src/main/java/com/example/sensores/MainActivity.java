package com.example.sensores;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    private LinearLayout llTela;
    private ImageView ivMicrofone;

    private SensorManager mSensorManager;
    private Sensor mLuz;
    private Sensor mProx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llTela = (LinearLayout) findViewById(R.id.llTela);
        ivMicrofone = (ImageView) findViewById(R.id.ivMicrofone);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mLuz = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mProx = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        mSensorManager.registerListener(new LuzSensor(), mLuz,
                SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(new ProxSensor(), mProx,
                SensorManager.SENSOR_DELAY_UI);

    }

    class ProxSensor implements SensorEventListener {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent event) {
            float vl = event.values[0];

            if (vl >= 10 ) {
                ivMicrofone.setImageResource(R.drawable.luffy1);
            } else {
                ivMicrofone.setImageResource(R.drawable.luffy);
            }

        }
    }

    class LuzSensor implements SensorEventListener {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent event) {
            float vl = event.values[0];

            if (vl < 10) {
                llTela.setBackgroundColor(Color.WHITE);
            } else {
                llTela.setBackgroundColor(Color.BLACK);
            }
        }
    }
}