package com.williamgdev.example.recyclerpage.interactor;

import com.williamgdev.example.recyclerpage.dto.CatResponse;
import com.williamgdev.example.recyclerpage.restservice.CatsApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CatsInteractor {

    private static final CatsInteractor ourInstance = new CatsInteractor();
    private static final String BASE_URL = "https://chex-triplebyte.herokuapp.com/";
    private final CatsApiService catsApiService;

    public static CatsInteractor getInstance() {
        return ourInstance;
    }

    private CatsInteractor() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        catsApiService = retrofit.create(CatsApiService.class);
    }

    public void getCatsPage(int page, ApiListener<List<CatResponse>> listener) {
        catsApiService.getCatsPage(page).enqueue(getCatsPageCallback(listener));
    }

    private Callback<List<CatResponse>> getCatsPageCallback(final ApiListener<List<CatResponse>> listener) {
        return new Callback<List<CatResponse>>() {
            @Override
            public void onResponse(Call<List<CatResponse>> call, Response<List<CatResponse>> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body());
                        return;
                }
                listener.onError("Error: Retrieving data form the server.");
            }

            @Override
            public void onFailure(Call<List<CatResponse>> call, Throwable t) {
                listener.onError("Error: " + t.getMessage());
            }
        };
    }

    public interface ApiListener<T> {
        void onSuccess(T result);
        void onError(String error);
    }
}
