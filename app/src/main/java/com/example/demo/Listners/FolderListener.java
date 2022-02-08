package com.example.demo.Listners;

import com.example.demo.Adapter.ImageByFolderAdapter;
import com.example.demo.Model.ImageByFolderModel;

import java.util.ArrayList;

public interface FolderListener {
     public void onPhotoClick(String path,String folderName, int folderSize);
//     void onPhotoClick(ImageByFolderAdapter.FolderViewHolder holder, int position, ArrayList<ImageByFolderModel> pics);
}
