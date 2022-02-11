package com.example.demo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo.Adapter.QuotesCategoryAdapter;
import com.example.demo.Model.Category;
import com.example.demo.R;
import com.example.demo.Listners.CategoryClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QuotesCategoryActivity extends AppCompatActivity implements CategoryClickListener {
    RecyclerView recyclerView;
    String[] categories;
    String[] imageUrl;

    ArrayList<Category> categoryArrayList = new ArrayList<>();
    FloatingActionButton floatingActionButton;

    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_category);
        recyclerView = findViewById(R.id.cateRecyclerView);
        floatingActionButton = findViewById(R.id.floatingActionButton);
//        getSupportActionBar().hide(); // hide the title bar.

        categories = getResources().getStringArray(R.array.categoryList);
        imageUrl = getResources().getStringArray(R.array.imageUrlList);
        Log.d("category", "oncreate:" + categories.length);

        Intent intent = getIntent();
        msg = intent.getStringExtra("To");
        Toast.makeText(QuotesCategoryActivity.this, msg, Toast.LENGTH_SHORT).show();
//        String msgForCombo = intent.getStringExtra("To a viewPage Activity");
        for (int i = 0; i < categories.length; i++) {
            categoryArrayList.add(new Category(categories[i], imageUrl[i]));
//            Log.d("checkingArray", "onCreate: "+categories[i]);

        }
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new QuotesCategoryAdapter(categoryArrayList, this, this));


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(QuotesCategoryActivity.this);

                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.custom_dialogbox_cat, null, false);
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                EditText editText = dialogView.findViewById(R.id.editCategory);
                Button addDialog = dialogView.findViewById(R.id.addDialogQ);

                addDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String catString = editText.getText().toString();
                        if (catString.trim().isEmpty()) {
                            Toast.makeText(QuotesCategoryActivity.this, "please add category", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(QuotesCategoryActivity.this, catString, Toast.LENGTH_SHORT).show();

//                            categoryArrayList.add(categoryArrayList.size(),catString," data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAACVBMVEX///8AAAAEBAQaWQCcAAABAElEQVR4nO3PARHAMAgAMZh/data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBw0NDw0NDQ8NDQ0NDQ0NDQ0NDQ8NDw0NFREWFhURFRUYHSggGBolGxUVITEhJSkrLi4uFx8zODMsNygtLisBCgoKDQ0NDg0PEisZFRkrKysrKysrKysrKystKysrLSsrKysrKysrKysrKysrKysrKysrKysrKysrKysrKysrK//AABEIAKwBJgMBIgACEQEDEQH/xAAXAAEBAQEAAAAAAAAAAAAAAAAAAQIH/8QAIhABAQACAAQHAAAAAAAAAAAAAAERIQJBgfEiMVFhcbHw/8QAFAEBAAAAAAAAAAAAAAAAAAAAAP/EABQRAQAAAAAAAAAAAAAAAAAAAAD/2gAMAwEAAhEDEQA/AO4gAAAlvlrr6KAAAAJICgAAAAAAAAAAAAAAAM8XFZeHEtluLczw6tzfrq0AAAAAAAAAAAAAAAAAAAAAAAAAAAAACZ7qAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJfZQAAAAAgAAAAAAAAAAAAAAAAAACfvlQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAoAAAAAAAAAJYCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAARQAAAAAAAAAAAAEwoAAAACSqAAAAAAAAAAAAAAAAAAAAAAAAAAAAACXPLW5yzrKgAAAAAAAAAAAAAAAmFAAAAAAAABKoAAAAA//Z ",)
                            categoryArrayList.add(new Category(catString, "  data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAACVBMVEX///8AAAAEBAQaWQCcAAABAElEQVR4nO3PARHAMAgAMZh/data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBw0NDw0NDQ8NDQ0NDQ0NDQ0NDQ8NDw0NFREWFhURFRUYHSggGBolGxUVITEhJSkrLi4uFx8zODMsNygtLisBCgoKDQ0NDg0PEisZFRkrKysrKysrKysrKystKysrLSsrKysrKysrKysrKysrKysrKysrKysrKysrKysrKysrK//AABEIAKwBJgMBIgACEQEDEQH/xAAXAAEBAQEAAAAAAAAAAAAAAAAAAQIH/8QAIhABAQACAAQHAAAAAAAAAAAAAAERIQJBgfEiMVFhcbHw/8QAFAEBAAAAAAAAAAAAAAAAAAAAAP/EABQRAQAAAAAAAAAAAAAAAAAAAAD/2gAMAwEAAhEDEQA/AO4gAAAlvlrr6KAAAAJICgAAAAAAAAAAAAAAAM8XFZeHEtluLczw6tzfrq0AAAAAAAAAAAAAAAAAAAAAAAAAAAAACZ7qAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJfZQAAAAAgAAAAAAAAAAAAAAAAAACfvlQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAoAAAAAAAAAJYCgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQAAAAAAAAARQAAAAAAAAAAAAEwoAAAACSqAAAAAAAAAAAAAAAAAAAAAAAAAAAAACXPLW5yzrKgAAAAAAAAAAAAAAAmFAAAAAAAABKoAAAAA//Z"));
                            alertDialog.cancel();

                        }
                    }
                });
            }
        });
    }


    @Override
    public void onCategoryClick(String categoryName, int catPosition, Category category) {
        Toast.makeText(this, categoryName + catPosition + category.getCategory(), Toast.LENGTH_SHORT).show();

        if (msg.equals("QuoteActivity")) {
            Intent intent = new Intent(this, QuoteActivity.class);
            intent.putExtra("position", catPosition);
            intent.putExtra("categoryName", categoryName);
            intent.putExtra("Category", category);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, TxtImageComboActivity.class);
            intent.putExtra("categoryName", categoryName);
            startActivity(intent);
        }
    }


}