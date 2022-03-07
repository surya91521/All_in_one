package com.example.all_in_one;


import androidx.appcompat.app.AppCompatActivity;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.all_in_one.FingerPrintManager.FingerPrintManager;

import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button prompt = (Button) findViewById(R.id.bio_prompt);
        prompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BiometricPromptActivity.class);
                startActivity(intent);
            }
        });


        Button camIntent = (Button) findViewById(R.id.camintent);
        camIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CameraIntent.class);
                  startActivity(intent);
            }
        });
      
        Button manager = (Button) findViewById(R.id.FPManager);
        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FingerPrintManager.class);

                startActivity(intent);
            }
        });

        Button camera2 = (Button) findViewById(R.id.camera2);
        camera2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Camera2Api.class);
                startActivity(intent);
            }
        });

        Button camera1 = (Button) findViewById(R.id.camera1);
        camera1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CameraApi.class);
                startActivity(intent);
            }
        });

        Button camera1Bar = (Button) findViewById(R.id.camera1Bar);
        camera1Bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Camera1Bar.class);
                startActivity(intent);
            }
        });

        Button keyGenParam = (Button) findViewById(R.id.keyparam);
        keyGenParam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, KeyGenParamBiometric.class);
                startActivity(intent);
            }
        });

        Button cameraX = (Button) findViewById(R.id.cameraX);
        cameraX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CameraxApi.class);
                startActivity(intent);
            }
        });

        Button cameraZxing = (Button) findViewById(R.id.zxing);
        cameraZxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CameraZxing.class);
                startActivity(intent);
            }
        });

        Button camera2Bar = (Button) findViewById(R.id.camera2bar);
        camera2Bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Camera2Bar.class);
                startActivity(intent);
            }
        });

        Button cameraIntent = (Button) findViewById(R.id.uri);
        cameraIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CameraUri.class);
                intent.putExtra("uri", true);
                startActivity(intent);
            }
        });

        Date currentTime = Calendar.getInstance().getTime();
        getSupportActionBar().setTitle(currentTime.toString());
        releaseScreenLock(MainActivity.this);
    }

    public void releaseScreenLock(Context context) {

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        KeyguardManager mKeyGuardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock mLock = mKeyGuardManager.newKeyguardLock("Unlock");
        mLock.disableKeyguard();
    }
}