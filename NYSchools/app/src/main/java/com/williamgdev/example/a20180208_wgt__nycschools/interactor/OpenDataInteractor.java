package com.williamgdev.example.a20180208_wgt__nycschools.interactor;

import com.williamgdev.example.a20180208_wgt__nycschools.model.School;
import com.williamgdev.example.a20180208_wgt__nycschools.model.SchoolSAT;
import com.williamgdev.example.a20180208_wgt__nycschools.restservice.OpenDataApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenDataInteractor {

    private static final OpenDataInteractor ourInstance = new OpenDataInteractor();
    private static final String BASE_URL = "https://data.cityofnewyork.us/";
    private final OpenDataApiService apiService;

    public static OpenDataInteractor getInstance() {
        return ourInstance;
    }

    private OpenDataInteractor() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(OpenDataApiService.class);
    }

    public void getSchools(ApiListener<List<School>> listener) {
        apiService.getSchools().enqueue(getSchoolsCallback(listener));
    }

    public void getSchoolSAT(String schoolCode, ApiListener<List<SchoolSAT>> listener) {
        apiService.getSchoolsSAT(schoolCode).enqueue(getSchoolSATCallback(listener));
    }

    private Callback<List<SchoolSAT>> getSchoolSATCallback(final ApiListener<List<SchoolSAT>> listener) {
        return  new Callback<List<SchoolSAT>>() {
            @Override
            public void onResponse(Call<List<SchoolSAT>> call, Response<List<SchoolSAT>> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body());
                        return;
                }
                listener.onError("Error: Retrieving data form the server.");
            }

            @Override
            public void onFailure(Call<List<SchoolSAT>> call, Throwable t) {
                listener.onError("Error: " + t.getMessage());
            }
        };
    }

    private Callback<List<School>> getSchoolsCallback(final ApiListener<List<School>> listener) {
        return new Callback<List<School>>() {
            @Override
            public void onResponse(Call<List<School>> call, Response<List<School>> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body());
                        return;
                }
                listener.onError("Error: Retrieving data form the server.");
            }

            @Override
            public void onFailure(Call<List<School>> call, Throwable t) {
                listener.onError("Error: " + t.getMessage());
            }
        };
    }

    public interface ApiListener<T> {
        void onSuccess(T result);
        void onError(String error);
    }
}
