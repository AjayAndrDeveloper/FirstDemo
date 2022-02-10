package com.example.demo.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
    ArrayList<String> videoPath = new ArrayList<>();
    Context context;

    public VideoListAdapter(ArrayList<VideoFolderModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.video_list_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        VideoFolderModel videoFolderModel = arrayList.get(position) ;
        Glide.with(context).load(arrayList.get(position).getColumn_index_data()).into(holder.galleryImage);
        holder.folderName.setText(arrayList.get(position).getDisplay_name());
        holder.size.setText(arrayList.get(position).getData_size());
        Log.d("thumb", "onBindViewHolder: " + arrayList.get(position).getColumn_id());
        String s = convertMillieToHMmSs(arrayList.get(position).getDuration());
//                Long.toString(arrayList.get(position).getDuration());
        videoPath.add(arrayList.get(position).getColumn_index_data());
        holder.duration.setText(s);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoActivity.class);
                intent.putExtra("video", arrayList.get(position).getColumn_index_data());
                intent.putExtra("PathList", videoPath);
                intent.putExtra("position", position);
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
        TextView folderName, duration, size;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            folderName = itemView.findViewById(R.id.videoName);
            duration = itemView.findViewById(R.id.duration);
            size = itemView.findViewById(R.id.size);
            galleryImage = itemView.findViewById(R.id.videoPhoto);
        }
    }

    public static String convertMillieToHMmSs(long millie) {
        long seconds = (millie / 1000);
        long second = seconds % 60;
        long minute = (seconds / 60) % 60;
        long hour = (seconds / (60 * 60)) % 24;

        String result = "";
        if (hour > 0) {
            return String.format("%02d:%02d:%02d", hour, minute, second);
        } else {
            return String.format("%02d:%02d", minute, second);
        }

    }
}
