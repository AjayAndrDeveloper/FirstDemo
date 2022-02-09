package com.example.demo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demo.Activity.VideoActivity;
import com.example.demo.Model.VideoFolderModel;
import com.example.demo.R;
import com.example.demo.Utilies.VideoGallery;

import java.util.ArrayList;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder> {
    ArrayList<VideoFolderModel> arrayList = new ArrayList<>();
    Context context;

    public VideoListAdapter(ArrayList<VideoFolderModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.video_list_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VideoFolderModel videoFolderModel = arrayList.get(position) ;
        Glide.with(context).load(arrayList.get(position).getColumn_index_data()).into(holder.galleryImage);
        holder.folderName.setText(arrayList.get(position).getDisplay_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoActivity.class);
                intent.putExtra("video",arrayList.get(position).getColumn_index_data());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView galleryImage;
        TextView folderName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            folderName = itemView.findViewById(R.id.videoName);
            galleryImage = itemView.findViewById(R.id. videoPhoto);
        }
    }
}
