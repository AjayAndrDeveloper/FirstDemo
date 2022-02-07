package com.example.demo.Model;

import java.io.Serializable;

public class ImageByFolderModel implements Serializable {


    private String pictureName;
    private String picturePath;
    private  String pictureSize;
    private  String imageUri;
    private Boolean selected = false;
    private long bucketID;

    public long getBucketID() {
        return bucketID;
    }

    public void setBucketID(long bucketID) {
        this.bucketID = bucketID;
    }

    public ImageByFolderModel(String pictureName, String picturePath, String pictureSize, String imageUri) {
        this.pictureName = pictureName;
        this.picturePath = picturePath;
        this.pictureSize = pictureSize;
        this.imageUri = imageUri;
    }

    public ImageByFolderModel() {
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getPictureSize() {
        return pictureSize;
    }

    public void setPictureSize(String pictureSize) {
        this.pictureSize = pictureSize;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
