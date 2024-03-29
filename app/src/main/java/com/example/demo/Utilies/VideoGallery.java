package com.example.demo.Utilies;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.demo.Activity.VideoListByFolder;
import com.example.demo.Model.VideoFolderModel;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class VideoGallery {

    public static ArrayList<VideoFolderModel> getVideos(String path, Context context) {
        Cursor cursor;
        ArrayList<VideoFolderModel> allVideos = new ArrayList<>();
        ArrayList<String> videoPaths = new ArrayList<>();


        String column_index_data, column_index_folder_name, display_name, column_id, thumb;
        long duration = 0;
        long data_size;


        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {
                MediaStore.MediaColumns.DATA,
                MediaStore.Video.VideoColumns.BUCKET_DISPLAY_NAME,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media._ID,
                MediaStore.Video.Thumbnails.DATA,
                MediaStore.Video.VideoColumns.DURATION};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;

        String selection = MediaStore.Video.Media.DATA + " like?";
        if (path == null) {
            cursor = context.getContentResolver().query(uri, projection, null, null, null);
            Log.d("abc123", "getVideos: " + VideoListByFolder.abc);
            VideoListByFolder.abc = false;
        } else {
            cursor = context.getContentResolver().query(uri, projection, selection, new String[]{"%" + path + "%" }, orderBy);
            Log.d("abc123", "getVideos: " + path);
            VideoListByFolder.abc = true;
        }

        if (cursor != null) {
            cursor.moveToFirst();
        }
        do {
            column_index_data = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA));
            column_index_folder_name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.BUCKET_DISPLAY_NAME));
            display_name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
            data_size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
            column_id = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
            thumb = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA));
            duration =  cursor.getLong(cursor.getColumnIndex(MediaStore.Video.VideoColumns.DURATION));
            String strDataSize = changedData(data_size);
            VideoFolderModel videoFolderModel = new VideoFolderModel();
            String folderPaths = column_index_data.substring(0, column_index_data.lastIndexOf(column_index_folder_name + "/"));
            folderPaths = folderPaths + column_index_folder_name + "/";
            Log.d("folderPath", "getVideos: " + folderPaths);
            Log.d("folderPath", "getVideos: " + column_index_data + "123456789");

            if (path == null) {
                if (!videoPaths.contains(folderPaths)) {
                    videoPaths.add(folderPaths);
//                videoFolderModel.setImagePath(folderPaths);
                    videoFolderModel.setColumn_index_data(column_index_data);
                    videoFolderModel.setColumn_index_folder_name(column_index_folder_name);
                    videoFolderModel.setDisplay_name(display_name);
                    videoFolderModel.setData_size(strDataSize);
                    videoFolderModel.setColumn_id(column_id);
                    videoFolderModel.setThumb(thumb);
                    videoFolderModel.setDuration(duration);
                    Log.d("hiiii", " hello ");
                    videoFolderModel.addVid();
                    allVideos.add(videoFolderModel);
                } else {
                    for (int i = 0; i < allVideos.size(); i++) {
                        if (allVideos.get(i).getColumn_index_data().equals(folderPaths)) {
                            videoFolderModel.setColumn_index_folder_name(column_index_folder_name);

                            Log.d("sss123", "getVideos: ");
                        }
                    }
                }
            } else {
                videoPaths.add(folderPaths);
//                videoFolderModel.setImagePath(folderPaths);
                videoFolderModel.setColumn_index_data(column_index_data);
                videoFolderModel.setColumn_index_folder_name(thumb);
                videoFolderModel.setDisplay_name(display_name);
                videoFolderModel.setData_size(strDataSize);
                videoFolderModel.setColumn_id(column_id);
                videoFolderModel.setThumb(thumb);
                videoFolderModel.setDuration(duration);
                Log.d("hiiii", " hello ");
                videoFolderModel.addVid();
                allVideos.add(videoFolderModel);
            }

        } while (cursor.moveToNext());
        Log.d("heyy", "getVideos: " + allVideos.size());

        cursor.close();


        return allVideos;


    }

    private static String changedData(long data_size) {
//
        if (data_size <= 0)
            return "0";
        final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(data_size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(data_size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];


}}
