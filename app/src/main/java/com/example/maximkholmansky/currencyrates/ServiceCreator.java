package com.example.maximkholmansky.currencyrates;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceCreator {

    private static final String BASE_URL = "https://query.yahooapis.com";
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiService createService() {
        return retrofit.create(ApiService.class);
    }
}