package com.williamgdev.example.recyclerpage.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatResponse {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("description")
    @Expose
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
