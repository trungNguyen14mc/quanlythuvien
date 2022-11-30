package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class Hello extends AppCompatActivity {
    LinearLayout hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        hello = findViewById(R.id.hello);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hello.setVisibility(View.GONE);
                Intent intent = new Intent (Hello.this,LoginActivity.class);
                startActivity (intent);
            }
        },5000);
    }
}