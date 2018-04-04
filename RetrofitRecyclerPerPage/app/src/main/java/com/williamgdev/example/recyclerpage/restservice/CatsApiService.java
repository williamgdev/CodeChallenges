package com.williamgdev.example.recyclerpage.restservice;

import com.williamgdev.example.recyclerpage.dto.CatResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CatsApiService {

    @GET("api/cats")
    Call<List<CatResponse>> getCatsPage(@Query("page") int page);
}
