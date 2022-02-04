package com.example.demo.Model;

public class GalleryModel {
    String FolderName;
    String ImagePath;

    public String getFolderName() {
        return FolderName;
    }

    public void setFolderName(String folderName) {
        FolderName = folderName;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public GalleryModel(String imagePath, String folderName) {
        FolderName = folderName;
        ImagePath = imagePath;
    }
}
