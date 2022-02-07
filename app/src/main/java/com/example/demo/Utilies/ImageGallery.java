package com.example.demo.Utilies;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.demo.Model.GalleryModel;

import java.util.ArrayList;

public class ImageGallery {


    public static ArrayList<GalleryModel> listOfImages(Context context) {
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        ArrayList<GalleryModel> listOfAllImages = new ArrayList<>();
        ArrayList<String> picPaths = new ArrayList<>();
        String dataPath,folderName,idBucket;

        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.MediaColumns.DATA,
                MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,MediaStore.Images.Media.BUCKET_ID};
        String orderBy = MediaStore.Images.ImageColumns.DATE_TAKEN+" DESC ";
//        String where = MediaStore.Files.FileColumns.MIME_TYPE + "=?"
//                +" OR " +MediaStore.Files.FileColumns.MIME_TYPE + "=?"
//                ;
//        String jpeg = MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf");
//        String png = MimeTypeMap.getSingleton().getMimeTypeFromExtension("png");
//        String[] args = new String[]{jpeg};


        cursor = context.getContentResolver().query(uri, projection, null, null, null);
        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        int id = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID);

            if (cursor != null) {
                cursor.moveToFirst();
            }
            do {
                GalleryModel galleryModel = new GalleryModel();
                String folder = cursor.getString(column_index_folder_name);
                dataPath = cursor.getString(column_index_data);
                idBucket = cursor.getString(id);
                String folderpaths = dataPath.substring(0, dataPath.lastIndexOf(folder + "/"));
                folderpaths = folderpaths + folder + "/";
                if (!picPaths.contains(folderpaths)) {
                    picPaths.add(folderpaths);
                    galleryModel.setBucketId(id);
                    galleryModel.setImagePath(folderpaths);
                    galleryModel.setFolderName(folder);
                    galleryModel.setFirstImage(dataPath);
                    galleryModel.addpics();
                    listOfAllImages.add(galleryModel);

                } else {
                    for (int i = 0; i<listOfAllImages.size(); i++) {
                        if (listOfAllImages.get(i).getImagePath().equals(folderpaths)){
                            listOfAllImages.get(i).setFirstImage(dataPath);
                            listOfAllImages.get(i).setBucketId(id);
                            listOfAllImages.get(i).addpics();

                        }

                    }
                }
            }while (cursor.moveToNext());
            cursor.close();
        for(int i = 0;i < listOfAllImages.size();i++){
            Log.d("picture folders",listOfAllImages.get(i).getFolderName()+" and path = "+listOfAllImages.get(i).getImagePath()+" "+listOfAllImages.get(i).getNumberOfPics());
        }










//        while (cursor.moveToNext()) {
//            dataPath = cursor.getString(column_index_data);
//
//            folderName = cursor.getString(column_index_folder_name);
//            String folder = dataPath.substring(0,dataPath.lastIndexOf(folderName+"/"));
//            folder= folder+folderName+"/";
//
//            if (!listOfAllImages.contains(folder)){                listOfAllImages.add(new GalleryModel(dataPath,folderName));
//            }
//
//
//        }

        return listOfAllImages;
    }

}
