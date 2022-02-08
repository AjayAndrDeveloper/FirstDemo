package com.example.demo.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demo.Activity.BigPhotoActivity;
import com.example.demo.Activity.ImageDisplayActivity;
import com.example.demo.Listners.CurrentItemListener;
import com.example.demo.Model.ImageByFolderModel;
import com.example.demo.R;

import java.util.ArrayList;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ListViewHolder> {
    ArrayList<ImageByFolderModel> imageList;
    Context context;
    CurrentItemListener currentItemListener;


    public ImageListAdapter(ArrayList<ImageByFolderModel> imageList, Context context, CurrentItemListener currentItemListener) {
        this.imageList = imageList;
        this.context = context;
        this.currentItemListener = currentItemListener;
    }

    public ImageListAdapter(ArrayList<ImageByFolderModel> imageList, Context context) {
        this.imageList = imageList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.imagelist_layout,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        ImageByFolderModel image = imageList.get(position);
        String imagePath = imageList.get(position).getPicturePath();
        Glide.with(context).load(imagePath).into(holder.imageView);
        Log.d("pos123", "onBindViewHolder: "+position);
        Log.d("Hello", "onBindViewHolder: " + BigPhotoActivity.currentPos + " position" + position );

        if (BigPhotoActivity.currentPos==position)
        {
                holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.rounded_bg));

        }
        else
        {
            holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.rounded_un_select_bg));

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentItemListener.ClickOnCurrentItem(position);
                notifyItemChanged(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ConstraintLayout constraintLayout;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
             imageView= itemView.findViewById(R.id.imageLists);
             constraintLayout = itemView.findViewById(R.id.containerList);
        }
    }
}
