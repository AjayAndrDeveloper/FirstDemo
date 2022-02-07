package com.example.demo.Utilies;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.demo.Activity.ImageDisplayActivity;
import com.example.demo.Model.ImageByFolderModel;

import java.util.ArrayList;

public class GetALLImagesByFolder {


    public static ArrayList<ImageByFolderModel> imageBy(String path,Context context){

        ArrayList<ImageByFolderModel> imageS = new ArrayList<>();

        Uri  uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.ImageColumns.DATA ,MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.SIZE,MediaStore.Images.Media.BUCKET_ID};
        Cursor cursor=context.getContentResolver().query(uri,projection,MediaStore.Images.Media.DATA + " like ? ", new String[] {"%"+path+"%"}, null);
        cursor.moveToFirst();
        do {
            ImageByFolderModel imageByFolderModel = new ImageByFolderModel();
            imageByFolderModel.setPictureName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)));
            imageByFolderModel.setPicturePath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)));
            imageByFolderModel.setPictureSize(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)));
            imageS.add(imageByFolderModel);
        }
        while (cursor.moveToNext());
        cursor.close();
        ArrayList<ImageByFolderModel> selection = new ArrayList<>();
        for (int i = imageS.size()-1;i>-1;i--){
            selection.add(imageS.get(i));
            Log.d("get123", "imageBy: "+ imageS.get(i));
        }
        imageS= selection;

            return imageS;
    }
}
