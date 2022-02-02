package com.example.demo.Activity;

import static androidx.constraintlayout.solver.widgets.ConstraintWidget.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo.Adapter.FavAdapter;
import com.example.demo.Helper.HelperFav;
import com.example.demo.Listners.updateActivity;
import com.example.demo.Model.Category;
import com.example.demo.R;

import java.util.ArrayList;

public class FavoriteQuotesActivity extends AppCompatActivity implements updateActivity {
TextView textView;
    ImageView imageView2;
    RecyclerView recyclerView;
   ArrayList<String> quotesData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_quotes);
         textView = findViewById(R.id.emptyText);
         textView.setVisibility(View.GONE);
           recyclerView = findViewById(R.id.fav_recyclerView);
         HelperFav helperFav = new HelperFav(this);
        quotesData=helperFav.getAllQuotes();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FavAdapter favAdapter =new FavAdapter(quotesData,this,this);
        recyclerView.setAdapter(favAdapter);
//
        if (quotesData.size()==0){
            textView.setVisibility(View.VISIBLE);
        }

//        if(cursor!= null);
//        {
//            cursor.moveToFirst();
//        }
//        StringBuilder stringBuilder = new StringBuilder();
//        do{
////            String category = cursor.getString(0);
//            String quotes = cursor.getString(0);
//            quotesData.add(quotes);
//            stringBuilder.append( " "  + quotes);
//    }while(cursor.moveToNext());
//        textView.setText(stringBuilder);


}

    @Override
    public void activityUpdation(ArrayList<String> arrayList) {
      if(quotesData.size()==0){
          textView.setVisibility(View.VISIBLE);
      }
    }

}
