package com.williamgdev.example.ebay_codechallenge.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApi {

    private static final RestApi ourInstance = new RestApi();
    private static final String BASE_URL = "https://api.example.com/";
    private final RestApiService apiService;

    // @TODO REMOVE FLAG IN CASE THAT BASE URL EXIST
    private static final boolean MOCK = true;

    public static RestApi getInstance() {
        return ourInstance;
    }

    private RestApi() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(RestApiService.class);
    }

    public void getUsers(ApiListener<List<User>> listener) {apiService.getUsers().enqueue(getUsersCallback(listener));
    }

    private Callback<UserResponse> getUsersCallback(final ApiListener<List<User>> listener) {
        return new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body().getUsers());
                        return;
                }
                listener.onError("Error: Retrieving data form the server.");
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                if (MOCK) {
                    List<User> users = UserResponse.getExample();
                    listener.onSuccess(users);
                    return;
                }
                listener.onError("Error: " + t.getMessage());
            }
        };
    }

    public interface ApiListener<T> {
        void onSuccess(T result);
        void onError(String error);
    }
}
