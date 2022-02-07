package com.example.demo.Model;

public class GalleryModel {
    String FolderName;
    String ImagePath;
      int numberOfPics = 0;
      String firstImage;
      long bucketId;

    public long getBucketId() {
        return bucketId;
    }

    public void setBucketId(long bucketId) {
        this.bucketId = bucketId;
    }

    public String getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    public int getNumberOfPics() {
        return numberOfPics;
    }

    public void setNumberOfPics(int numberOfPics) {
        this.numberOfPics = numberOfPics;
    }

    public GalleryModel() {
    }
    public GalleryModel(String imagePath, String folderName) {
        FolderName = folderName;
        ImagePath = imagePath;
    }

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
    public void addpics(){
        this.numberOfPics++;
    }



}
