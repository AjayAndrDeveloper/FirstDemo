package com.example.demo.Model;

public class VideoFolderModel {
    String column_index_data, column_index_folder_name,data_size,display_name,column_id,thumb;
    int numberOfVideo = 0;
    String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getColumn_index_data() {
        return column_index_data;
    }

    public void setColumn_index_data(String column_index_data) {
        this.column_index_data = column_index_data;
    }

    public String getColumn_index_folder_name() {
        return column_index_folder_name;
    }

    public void setColumn_index_folder_name(String column_index_folder_name) {
        this.column_index_folder_name = column_index_folder_name;
    }

    public String getData_size() {
        return data_size;
    }

    public void setData_size(String data_size) {
        this.data_size = data_size;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getColumn_id() {
        return column_id;
    }

    public void setColumn_id(String column_id) {
        this.column_id = column_id;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
    public void addVid(){
        this.numberOfVideo++;
    }
}

