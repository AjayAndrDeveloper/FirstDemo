package com.example.demo.Adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    boolean flag = true;
    int[] colorArray = {Color.rgb(150, 100, 210), Color.rgb(235, 82, 52), Color.rgb(205, 235, 52), Color.rgb(52, 235, 86), Color.rgb(235, 52, 86)};
    //    int[] colorArray = {R.color.red1,R.color.red2,R.color.red3,R.color.red4,R.color.red5};
    Random rnd = new Random();
    int color;


    public GetJsonDataAdapter(ArrayList<QuotesModel> quotesModelArrayList, Context context) {
        this.quotesModelArrayList = quotesModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public JViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.getting_data_layout, parent, false);

        return new JViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JViewHolder holder, int position) {
        holder.quotes.setText(quotesModelArrayList.get(position).getText());
        holder.authorName.setText("-" + quotesModelArrayList.get(position).getAuthor());
        holder.constraintLayout.setBackgroundColor(colorArray[position % 5]);
        String newline = System.lineSeparator();

        String copyToText = quotesModelArrayList.get(position).getText() + newline + "\t" +
                "-"+  quotesModelArrayList.get(position).getAuthor();
        holder.copyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(null, copyToText);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show();
            }
        });
        holder.shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, copyToText);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                context.startActivity(shareIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return quotesModelArrayList.size();
    }

    public class JViewHolder extends RecyclerView.ViewHolder {
        TextView quotes, authorName;
        ConstraintLayout constraintLayout;
        ImageView copyImage, shareImage;

        public JViewHolder(@NonNull View itemView) {
            super(itemView);
            quotes = itemView.findViewById(R.id.quotesText);
            authorName = itemView.findViewById(R.id.authorText);
            constraintLayout = itemView.findViewById(R.id.container);
            copyImage = itemView.findViewById(R.id.copyImage);
            shareImage = itemView.findViewById(R.id.shareImage);

        }
    }
}
