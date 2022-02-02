package com.example.demo.Adapter;

import static androidx.constraintlayout.solver.widgets.ConstraintWidget.VISIBLE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.Activity.FavoriteQuotesActivity;
import com.example.demo.Helper.HelperFav;
import com.example.demo.Listners.updateActivity;
import com.example.demo.R;
import java.util.ArrayList;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavViewHolder> {
    ArrayList<String> favList = new ArrayList<>();
    Context context;
    updateActivity updateActivity;

    public FavAdapter(ArrayList<String> favList, Context context, com.example.demo.Listners.updateActivity updateActivity) {
        this.favList = favList;
        this.context = context;
        this.updateActivity = updateActivity;
    }
//    SQLiteDatabase database;

    public FavAdapter(ArrayList<String> favList, Context context) {
        this.favList = favList;
        this.context = context;
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.quote_layout,parent,false);
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText(favList.get(position));
        Log.d("favList", "onClick: "+favList.get(position));

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HelperFav helperFav = new HelperFav(context);

                Log.d("favList", "onClick: "+favList.get(position));

                helperFav.deleteData(favList.get(position));

//                updation of recycleView
                favList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,favList.size());

                if (favList.size()==0){

                    Toast.makeText(context," Add the data", Toast.LENGTH_SHORT).show();
                }
                updateActivity.activityUpdation(favList);

            }
        });

    }
    @Override
    public int getItemCount() {
        return favList.size();
    }

    public class FavViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.quotes);
            imageView = itemView.findViewById(R.id.deleteImage);

        }
    }
}
