package com.example.demo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.Activity.VideoListByFolder;
import com.example.demo.Model.VideoFolderModel;
import com.example.demo.R;

import java.util.ArrayList;

public class VFolderListAdapter extends RecyclerView.Adapter<VFolderListAdapter.ViewHolder> {
    ArrayList<VideoFolderModel> videoFolderModelArrayList = new ArrayList<>();
    Context context;

    public VFolderListAdapter(ArrayList<VideoFolderModel> videoFolderModelArrayList, Context context) {
        this.videoFolderModelArrayList = videoFolderModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.video_folder_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VideoFolderModel videoFolderModel = videoFolderModelArrayList.get(position);
        String path= videoFolderModelArrayList.get(position).getColumn_index_folder_name();
        holder.textView.setText(videoFolderModelArrayList.get(position).getColumn_index_folder_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(context, VideoListByFolder.class);
                intent.putExtra("videoList",path);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoFolderModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.videoFolderName);
        }
    }
}
