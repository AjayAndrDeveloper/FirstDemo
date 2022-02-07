package com.example.demo.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demo.Model.Category;
import com.example.demo.R;
import com.example.demo.Listners.CategoryClickListener;

import java.util.ArrayList;

public class QuotesCategoryAdapter extends RecyclerView.Adapter<QuotesCategoryAdapter.ViewHolder>{
    ArrayList<Category> categoryArrayList = new ArrayList<>();
    Context context;

    CategoryClickListener categoryClickListener;

//    public QuotesCategoryAdapter(ArrayList<Category> categoryArrayList, Context context) {
//        this.categoryArrayList = categoryArrayList;
//        this.context = context;
//    }

    public QuotesCategoryAdapter(ArrayList<Category> categoryArrayList, Context context, CategoryClickListener categoryClickListener) {
        this.categoryArrayList = categoryArrayList;
        this.context = context;
        this.categoryClickListener = categoryClickListener;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.category_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Category data = categoryArrayList.get(position);
        holder.textView.setText(data.getCategory());
        Glide.with(context).load(data.getImageURl()).into(holder.imageView);


        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickListener.onCategoryClick(categoryArrayList.get(position).getCategory(),position,categoryArrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        RelativeLayout container;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.categories);
            imageView = itemView.findViewById(R.id.bg_image);
            container = itemView.findViewById(R.id.container);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Log.d("Zahir", "onClick: ");
//                    categoryClickListner.onCategoryClick();
////                    Toast.makeText(context, categoryArrayList.get(getAdapterPosition()).getCategory(), Toast.LENGTH_SHORT).show();
////                    Intent intent = new Intent(context, QuoteActivity.class);
////                    intent.putExtra("position",getAdapterPosition());
////                    intent.putExtra("CurrentCategory",categoryArrayList.get(getAdapterPosition()).getCategory());
////                    context.startActivity(intent);
//                }
//            });
        }
    }
    }

