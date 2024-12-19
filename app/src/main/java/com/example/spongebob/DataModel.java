package com.example.spongebob;

public class DataModel {

    private String name;
    private String description;
    private int image;
    private int id_;

    public DataModel(String name, String description, int image, int id_) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.id_ = id_;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId_() {
        return id_;
    }

    public int getImage() {
        return image;
    }
}








