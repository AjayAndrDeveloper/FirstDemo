package com.example.demo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.demo.Adapter.GetJsonDataAdapter;
import com.example.demo.Model.QuotesModel;
import com.example.demo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonPracticeActivity extends AppCompatActivity {
RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_practice);
        recyclerView =  findViewById(R.id.JsonRecyclerView);
            String jsonString = JSONDataFromAsset();
//        Log.d("Ajay", "onCreate: + jsonStrng" + jsonString);
        Gson gson =new Gson();
        Type listUserType = new TypeToken<ArrayList<QuotesModel>>(){}.getType();
        ArrayList<QuotesModel> quotesModelArrayList=gson.fromJson(jsonString,listUserType);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new GetJsonDataAdapter(quotesModelArrayList,this));
        Log.d("Ajay", "onCreate: "+ quotesModelArrayList.get(1).getText());

    }

    private String JSONDataFromAsset() {
     String json = null;
     try {
         InputStream inputStream =this.getAssets().open("quote.json");
         int sizeOfFile = inputStream.available();
         Log.d("ajay", "JSONDataFromAsset: "+ sizeOfFile);
         byte[] bufferData =new byte[sizeOfFile];
         inputStream.read(bufferData);
         inputStream.close();
         json = new String(bufferData,"UTF-8");


     }catch (IOException ioException){
         ioException.printStackTrace();
         return null;
     }
     return json;
    }
}