package com.example.all_in_one;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.all_in_one.FingerPrintManager.FingerPrintManager;

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

        Button manager = (Button) findViewById(R.id.FPManager);
        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FingerPrintManager.class);
                startActivity(intent);
            }
        });
    }
}