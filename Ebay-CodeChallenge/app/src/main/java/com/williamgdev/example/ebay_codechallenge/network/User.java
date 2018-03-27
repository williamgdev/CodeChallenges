package com.williamgdev.example.ebay_codechallenge.network;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("name")
    @Expose
    private String name;
    @Expose
    @SerializedName("last_name")
    private String lastName;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static User create(String uid, String name, String lastName) {
        User user = new User();
        user.setUid(uid);
        user.setName(name);
        user.setLastName(lastName);

        return user;
    }
}
