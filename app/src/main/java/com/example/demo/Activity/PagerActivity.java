package com.example.demo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.demo.Adapter.ViewPagerAdapter;
import com.example.demo.Helper.HelperFav;
import com.example.demo.Model.Category;
import com.example.demo.R;

import java.util.ArrayList;

public class PagerActivity extends AppCompatActivity {
    int positionOfCurrent;
    String CurrentQuotes;
    String currentCategory;
    ViewPager viewPager;
    Button next, previous;
    ImageView favButton;
    HelperFav helperFav;
    Boolean flag = true;
    ArrayList<Category> quotesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        viewPager = findViewById(R.id.viewPage);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        favButton = findViewById(R.id.fav_button);
        helperFav = new HelperFav(this);
//        getSupportActionBar().hide(); // hide the title bar.
//intent to get data from previous activity
        Intent intent = getIntent();
//        String CurrentQuotes = intent.getStringExtra()
        positionOfCurrent = intent.getIntExtra("PositionOfCurrent", 0);
        quotesList = (ArrayList<Category>) intent.getSerializableExtra("quotesList");

    //  method calling
        setData(positionOfCurrent);
//    view pager object initialize and setadapter
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(quotesList, this);
        viewPager.setAdapter(viewPagerAdapter);

//  when we click the button showing current quotes (page)
        viewPager.setCurrentItem(positionOfCurrent);
//    when we change change the page update or changes
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.d("ajay", "onPageSelected: "+position);
            }

            @Override
            public void onPageSelected(int position) {
                positionOfCurrent = position;
                setData(positionOfCurrent);
                Log.d("ajay", "onPageSelected: " + positionOfCurrent);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (positionOfCurrent == 0) {
                    positionOfCurrent = quotesList.size();
                } else {
                    positionOfCurrent = positionOfCurrent - 1;
                }
                viewPager.setCurrentItem(positionOfCurrent);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (positionOfCurrent == quotesList.size()) {
                    positionOfCurrent = 0;
                } else {
                    positionOfCurrent = positionOfCurrent + 1;
                }
                viewPager.setCurrentItem(positionOfCurrent);
            }
        });


        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(PagerActivity.this, CurrentQuotes+" ", Toast.LENGTH_SHORT).show();
//                favList.add(CurrentQuotes);
//                Intent intent1 = new Intent(PagerActivity.this, HelperFav.class);
//                intent1.putStringArrayListExtra("favList",favList);
//                String db = helperFav.getDatabaseName();
                Log.d("false1234", "onClick: " + flag);
                if (flag == false) {

                    helperFav.deleteData(CurrentQuotes);
                    favButton.setImageResource(R.drawable.ic_iconmonstr_heart_blacki);
                    flag=true;
                    Toast.makeText(PagerActivity.this, "SuccessFully delete data from YOur favorite list" +"   "+CurrentQuotes, Toast.LENGTH_SHORT).show();

                } else {
                    helperFav.insertData(CurrentQuotes);
                    favButton.setImageResource(R.drawable.ic_iconmonstr_heart_redi);
                    flag = false;
                    Toast.makeText(PagerActivity.this, "SuccessFully added data in YOur favorite list"+"   "+CurrentQuotes, Toast.LENGTH_SHORT).show();

                }


            }

        });
//        if (flag=false){
//            favButton.setImageResource(R.drawable.ic_iconmonstr_heart_redi);
//        }


    }

    private void setData(int positionOfCurrent) {

        currentCategory = quotesList.get(positionOfCurrent).getCategory();
        CurrentQuotes = quotesList.get(positionOfCurrent).getQuotes();

        for (int i = 0; i < helperFav.getAllQuotes().size(); i++) {

            Log.d("forloop", "setData: list=" + CurrentQuotes + "  fav:" + (helperFav.getAllQuotes().get(i)));
            if (CurrentQuotes.equals(helperFav.getAllQuotes().get(i))) {
                flag = false;
                favButton.setImageResource(R.drawable.ic_iconmonstr_heart_redi);
                break;
            } else {
                flag=true;
                favButton.setImageResource(R.drawable.ic_iconmonstr_heart_blacki);
            }
//            }
        }
//        if (helperFav.getAllQuotes().contains(CurrentQuotes)) {
//            favButton.setImageResource(R.drawable.ic_iconmonstr_heart_redi);
//            Log.d("image123", "onCreate: " + CurrentQuotes);
//            flag = false;
//
//        }


    }
//    public void getData(int positionOfCurrent)
//    {
//        Intent intent = new Intent(PagerActivity.this,FavoriteQuotesActivity.class);
//        intent.putExtra("position",positionOfCurrent);
//
//    }
}