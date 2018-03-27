package com.williamgdev.example.ebay_codechallenge.network;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserResponse {
    @SerializedName("users")
    @Expose
    private List<User> users = null;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public static List<User> getExample() {
        final User user1 = User.create("123", "Tom", "Jhonson");
        final User user2 = User.create("234", "Claudia", "Jhonson");
        final User user3 = User.create("345", "Brenden", "Jhonson");
        List<User> response = new ArrayList<>();
        response.add(user1);
        response.add(user2);
        response.add(user3);
        return response;
    }
}
