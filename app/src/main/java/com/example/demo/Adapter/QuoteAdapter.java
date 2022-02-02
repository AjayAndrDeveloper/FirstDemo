package com.example.demo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.demo.Activity.PagerActivity;
import com.example.demo.Model.Category;
import com.example.demo.R;
import java.io.Serializable;

import java.util.ArrayList;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.QuotesViewHolder> {
    public QuoteAdapter(ArrayList<Category> quotesList, Context context) {
        this.quotesList = quotesList;
        this.context = context;
    }

  private   ArrayList<Category> quotesList  = new ArrayList<>() ;
   private Context context;;
    @NonNull
    @Override
    public QuotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.quote_layout,parent,false);

        return new QuotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuotesViewHolder holder, int position) {
        holder.textView.setText(quotesList.get(position).getQuotes());


    }

    @Override
    public int getItemCount() {
        return quotesList.size();
    }

    public class QuotesViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public QuotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.quotes);
            imageView = itemView.findViewById(R.id.deleteImage);
            imageView.setVisibility(View.GONE);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context, quotesList.get(getAdapterPosition()).getQuotes(), Toast.LENGTH_SHORT).show();
                    Intent intent  = new Intent(context, PagerActivity.class);
                    intent.putExtra("CurrentQuotes",quotesList.get(getAdapterPosition()).getQuotes());
                    intent.putExtra("PositionOfCurrent",getAdapterPosition());
                    intent.putExtra("quotesList",quotesList );
                    context.startActivity(intent);
                }
            });
            
        }
    }
}
