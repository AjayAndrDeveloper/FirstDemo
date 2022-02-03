package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demo.Activity.FavoriteQuotesActivity;
import com.example.demo.Activity.GridLayoutExampleActivity;
import com.example.demo.Activity.QuotesCategoryActivity;
import com.example.demo.Utilies.CommonIntent;

public class MainActivity extends AppCompatActivity {
   Button textQuotes ,favQuotes,textImageComboBtn,gridBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textQuotes = findViewById(R.id.textQuotes);
        favQuotes = findViewById(R.id.fav_quotes);
        textImageComboBtn = findViewById(R.id.viewPagerBtn);
        gridBtn = findViewById(R.id.GridLayoutBtn);
        Intent intent = new Intent(MainActivity.this,QuotesCategoryActivity.class);
        textQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CommonIntent.intentActivity(MainActivity.this, QuotesCategoryActivity.class);

                intent.putExtra("To","QuoteActivity");
                startActivity(intent);
            }

        });
        favQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonIntent.intentActivity(MainActivity.this, FavoriteQuotesActivity.class);
            }
        });
        textImageComboBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CommonIntent.intentActivity(MainActivity.this,QuotesCategoryActivity.class);

                intent.putExtra("To","txtImageComboActivity");
                startActivity(intent);
            }

        });
        gridBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonIntent.intentActivity(MainActivity.this, GridLayoutExampleActivity.class);
            }
        });
    }
}