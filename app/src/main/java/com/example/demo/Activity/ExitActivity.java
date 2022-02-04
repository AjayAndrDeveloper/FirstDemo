package com.example.demo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demo.MainActivity;
import com.example.demo.R;

public class ExitActivity extends AppCompatActivity {
        Button yes,no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        yes = findViewById(R.id.yesBtn);
        no = findViewById(R.id.noBtn);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExitActivity.this, MainActivity.class));
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(ExitActivity.this, MainActivity.class));
        finish();


    }
}