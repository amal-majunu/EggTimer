package com.example.android.eggtimer1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    public void updateTimer(int progress){
        TextView time = (TextView) findViewById(R.id.time);
        int minutes = (int)progress/60;
        int seconds = progress - minutes*60;
        String secondString = Integer.toString(seconds);
        if(seconds<=9){
            secondString = "0" + secondString;
        }
        time.setText(Integer.toString(minutes)+":"+secondString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = (SeekBar) findViewById(R.id.bar);
        bar.setMax(600);
        bar.setProgress(30);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
    }
    SeekBar bar;
    TextView time;
    ImageView egg;
    boolean counterIsActive = false;

    public void controlTimer(View view){
        if(counterIsActive==false) {
            counterIsActive = true;
  //           bar = (SeekBar) findViewById(R.id.bar);
            bar.setEnabled(false);
            new CountDownTimer(bar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    time = (TextView) findViewById(R.id.time);
                    egg = (ImageView) findViewById(R.id.egg);
                    TextView text2 = (TextView) findViewById(R.id.text2);
                    LinearLayout text = (LinearLayout)findViewById(R.id.layout);
                    time.setText("0:00");
                    text.setVisibility(View.INVISIBLE);
                    egg.setVisibility(View.INVISIBLE);
                    text2.setVisibility(View.VISIBLE);

                }
            }.start();
        }
    }
}
