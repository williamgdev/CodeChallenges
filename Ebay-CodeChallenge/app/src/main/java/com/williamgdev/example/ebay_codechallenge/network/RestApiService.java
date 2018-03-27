package com.williamgdev.example.ebay_codechallenge.network;


import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApiService {
    @GET("users")
    Call<UserResponse> getUsers();
}
