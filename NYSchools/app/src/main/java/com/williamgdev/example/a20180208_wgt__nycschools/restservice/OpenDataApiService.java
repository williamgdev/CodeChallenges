package com.williamgdev.example.a20180208_wgt__nycschools.restservice;

import com.williamgdev.example.a20180208_wgt__nycschools.model.School;
import com.williamgdev.example.a20180208_wgt__nycschools.model.SchoolSAT;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenDataApiService {

    @GET("resource/97mf-9njv.json")
    Call<List<School>> getSchools();

    @GET("resource/734v-jeq5.json")
    Call<List<SchoolSAT>> getSchoolsSAT(@Query("dbn") String dbn);
}
