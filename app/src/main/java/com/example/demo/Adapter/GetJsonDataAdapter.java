package com.example.demo.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.Fragments.TextFragment;
import com.example.demo.Model.QuotesModel;
import com.example.demo.R;

import java.util.ArrayList;
import java.util.Random;


public class GetJsonDataAdapter extends RecyclerView.Adapter<GetJsonDataAdapter.JViewHolder> {
    ArrayList<QuotesModel> quotesModelArrayList = new ArrayList<>();
     Context context;
     boolean flag =true;
     int[] colorArray = {Color.argb(44,55,66,22),Color.argb(44,55,66,22),Color.argb(44,55,0,22),Color.argb(40,50,66,0),Color.argb(44,52,16,221)};
    Random rnd = new Random();
    int color;


    public GetJsonDataAdapter(ArrayList<QuotesModel> quotesModelArrayList, Context context) {
        this.quotesModelArrayList = quotesModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public JViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =  LayoutInflater.from(parent.getContext());
        View view  = layoutInflater.inflate(R.layout.getting_data_layout,parent,false);

        return new JViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JViewHolder holder, int position) {
        holder.quotes.setText(quotesModelArrayList.get(position).getText());
        holder.authorName.setText("-"+quotesModelArrayList.get(position).getAuthor());
//
//        if (position%5==0) {
//            flag = false;
//            color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
//        }
            for(int i = 0;i>colorArray.length;i++){
        holder.constraintLayout.setBackgroundColor(colorArray[i]);};
    }

    @Override
    public int getItemCount() {
        return quotesModelArrayList.size();
    }

    public class JViewHolder extends RecyclerView.ViewHolder {
        TextView quotes,authorName;
        ConstraintLayout constraintLayout;
        public JViewHolder(@NonNull View itemView) {
            super(itemView);
            quotes = itemView.findViewById(R.id.quotesText);
            authorName = itemView.findViewById(R.id.authorText);
            constraintLayout = itemView.findViewById(R.id.container);

        }
    }
}
