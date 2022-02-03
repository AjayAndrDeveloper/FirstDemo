package com.example.demo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo.Adapter.QuoteAdapter;
import com.example.demo.Model.Category;
import com.example.demo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QuoteActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    ArrayList<Category> dataQuotes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        recyclerView = findViewById(R.id.quoteRecyclerView);
        floatingActionButton = findViewById(R.id.floatingActionButtonQuotes);
//        getSupportActionBar().hide(); // hide the title bar.

        Intent i = getIntent();
      int po= i.getIntExtra("position",0);
//       String currentCategory= i.getStringExtra("CurrentCategory");
       String currentCategory= i.getStringExtra("categoryName");
 //        Toast.makeText(this, currentCategory, Toast.LENGTH_SHORT).show();
//
        dataQuotes=getQoutes(currentCategory);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(QuoteActivity.this);

                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.custom_dialogbox_quotes, null, false);
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                EditText editText = dialogView.findViewById(R.id.editQuotes);
                Button addDialog = dialogView.findViewById(R.id.addDialogQ);
                addDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String qoutesString = editText.getText().toString();
                        if (qoutesString.trim().isEmpty()){
                            Toast.makeText(QuoteActivity.this, "Please Add the Data", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            dataQuotes.add(new Category(qoutesString));
                            alertDialog.cancel();
                        }
                    }
                });

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new QuoteAdapter(dataQuotes,QuoteActivity.this));

//        Bundle bundle = new Bundle();
//         bundle.putParcelableArrayList("dataQuotes", dataQuotes);
//        TextFragment textFragment = new TextFragment();
//        textFragment.setArguments(bundle);
    }

    public static ArrayList<Category> getQoutes(String currentCategory) {
        ArrayList<Category> qoutes = new ArrayList<Category>();
        switch (currentCategory){
            case "Happy":
                qoutes.add(new Category("Happy"));
                qoutes.add(new Category("happy2"));
                qoutes.add(new Category("Happy3"));
                qoutes.add(new Category("Happy4"));
                qoutes.add(new Category("Happy5"));
                break;
            case "Love":
                qoutes.add(new Category("lv1"));
                qoutes.add(new Category("lv2"));
                qoutes.add(new Category("lv3"));
                qoutes.add(new Category("lv4"));
                qoutes.add(new Category("lv5"));                break;


            case "Motivational":
                qoutes.add(new Category("Motivational"));
                qoutes.add(new Category("Motivational2"));
                qoutes.add(new Category("Motivational3"));
                qoutes.add(new Category("Motivational4"));
                qoutes.add(new Category("Motivational5"));
                break;

            case "Positive":
                qoutes.add(new Category("Positive1"));
                qoutes.add(new Category("Positive23"));
                qoutes.add(new Category("Positive3"));
                qoutes.add(new Category("Positive4"));
                qoutes.add(new Category("Positive5"));                break;

            case "inspirational":
                qoutes.add(new Category("Inspirational1"));
                qoutes.add(new Category("Inspirational2"));
                qoutes.add(new Category("Inspirational3"));
                qoutes.add(new Category("Inspirational4"));
                qoutes.add(new Category("Inspirational5"));                break;

            case "Sad":
                qoutes.add(new Category("Sad1"));
                qoutes.add(new Category("Sad2"));
                qoutes.add(new Category("Sad24"));
                qoutes.add(new Category("Sad25"));
                qoutes.add(new Category("Sad26"));                break;

            case "Deep Relationship":
                qoutes.add(new Category("Deep Relationship1"));
                qoutes.add(new Category("Deep Relationship2"));
                qoutes.add(new Category("Deep Relationship3"));
                qoutes.add(new Category("Deep Relationship4"));
                qoutes.add(new Category("Deep Relationship5"));                break;

            case "Gratitude":
                qoutes.add(new Category("Gratitude1"));
                qoutes.add(new Category("Gratitude2"));
                qoutes.add(new Category("Gratitude3"));
                qoutes.add(new Category("Gratitude4"));
                qoutes.add(new Category("Gratitude5"));                break;


        }

        return qoutes;
    }
}