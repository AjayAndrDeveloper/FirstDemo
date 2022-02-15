package com.example.demo.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.demo.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    ArrayList<String> imageList = new ArrayList<>();
    Context context ;



    public ImageAdapter(ArrayList<String> imageList, Context context) {
        this.imageList = imageList;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.combo_image_layout,parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        Glide.with(context).load(imageList).into(holder.imageView);
        Glide.with(context).load(imageList.get(position)).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
               holder.progressBar.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.VISIBLE);

                return false;
            }
        }).into(holder.imageView);
        holder.share_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable)holder.imageView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                String bitmapPath = MediaStore.Images.Media.insertImage(context.getContentResolver(),bitmap,"hello","");
                Uri uri = Uri.parse(bitmapPath);


                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
                sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                sendIntent.setType("image/*");

                context.startActivity(Intent.createChooser(sendIntent,"Share"));
            }
        });

        Log.d("imageUrl", "onBindViewHolder: "+ imageList);
//        holder.imageView.setImageResource(R.drawable.delete_24);
//        holder.textView.setText("ABCDEDf");
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ,share_button;
        ProgressBar progressBar;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            share_button = itemView.findViewById(R.id.shareButton);
            imageView = itemView.findViewById(R.id.imageViewQuotes);
            progressBar = itemView.findViewById(R.id.progressBar);
//            textView = itemView.findViewById(R.id.textFinal);
        }
    }
}
