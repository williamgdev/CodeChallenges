package com.williamgdev.example.earthquakecodetest.interactor;


import com.williamgdev.example.earthquakecodetest.dto.LastDaysResponse;
import com.williamgdev.example.earthquakecodetest.restservice.EarthQuakeApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EarthQuakeInteractor {

    private static final EarthQuakeInteractor ourInstance = new EarthQuakeInteractor();
    private static final String BASE_URL = "https://earthquake.usgs.gov/";
    private final EarthQuakeApiService earthQuakeApiService;

    public static EarthQuakeInteractor getInstance() {
        return ourInstance;
    }

    private EarthQuakeInteractor() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        earthQuakeApiService = retrofit.create(EarthQuakeApiService.class);
    }

    public void getLastDaysOccurrence(ApiListener<LastDaysResponse> listener) {
        earthQuakeApiService.getLastDaysOccurrence().enqueue(getLastDaysOccurrenceCallback(listener));
    }

    private Callback<LastDaysResponse> getLastDaysOccurrenceCallback(final ApiListener<LastDaysResponse> listener) {
        return  new Callback<LastDaysResponse>() {
            @Override
            public void onResponse(Call<LastDaysResponse> call, Response<LastDaysResponse> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body());
                        return;
                }
                listener.onError("Error: Retrieving data form the server.");
            }

            @Override
            public void onFailure(Call<LastDaysResponse> call, Throwable t) {
                listener.onError("Error: " + t.getMessage());
            }
        };
    }

    public interface ApiListener<T> {
        void onSuccess(T result);
        void onError(String error);
    }
}
