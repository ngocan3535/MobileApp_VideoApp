package com.example.moveuitemplate.models;

public class Phim {
    public Phim(String ID) {
        this.ID = ID;
    }

    public Phim(String ID, String tenPhim) {
        this.ID = ID;
        this.tenPhim = tenPhim;
    }

    public Phim(String ID, String tenPhim, String thumbnail) {
        this.ID = ID;
        this.tenPhim = tenPhim;
        this.thumbnail = thumbnail;
    }

    public Phim(String ID, String tenPhim, String thumbnail, String coverPhoto, String description) {
        this.ID = ID;
        this.tenPhim = tenPhim;
        this.thumbnail = thumbnail;
        this.description = description;
        this.coverPhoto = coverPhoto;
    }

    public String ID;
    public String tenPhim;
    private String description;
    private String thumbnail;
    private String coverPhoto;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }
}
