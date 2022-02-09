package com.example.demo.Model;

public class ImgFolderModel {
    String FolderName;
    String ImagePath;
      int numberOfPics = 0;
      String firstImage;




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

    public ImgFolderModel() {
    }
    public ImgFolderModel(String imagePath, String folderName) {
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
