package com.williamgdev.example.earthquakecodetest.restservice;

import com.williamgdev.example.earthquakecodetest.dto.LastDaysResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EarthQuakeApiService {

    @GET("earthquakes/feed/v1.0/summary/2.5_day.geojson")
    Call<LastDaysResponse> getLastDaysOccurrence();
}
