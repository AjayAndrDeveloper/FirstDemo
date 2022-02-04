package com.example.demo.Utilies;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.demo.Model.GalleryModel;

import java.util.ArrayList;

public class ImageGallery {


    public static ArrayList<GalleryModel> listOfImages(Context context) {
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        ArrayList<GalleryModel> listOfAllImages = new ArrayList<>();
        String pathOfImage,folderName;

        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.MediaColumns.DATA,
                MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME};
        String orderBy = MediaStore.Images.ImageColumns.DATE_TAKEN+" DESC ";
//        String where = MediaStore.Files.FileColumns.MIME_TYPE + "=?"
//                +" OR " +MediaStore.Files.FileColumns.MIME_TYPE + "=?"
//                ;
//        String jpeg = MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf");
//        String png = MimeTypeMap.getSingleton().getMimeTypeFromExtension("png");
//        String[] args = new String[]{jpeg};


        cursor = context.getContentResolver().query(uri, projection, null, null, orderBy);
        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            pathOfImage = cursor.getString(column_index_data);
            folderName = cursor.getString(column_index_folder_name);
            listOfAllImages.add(new GalleryModel(pathOfImage,folderName));
        }

        return listOfAllImages;
    }

}
