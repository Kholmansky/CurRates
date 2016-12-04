package com.example.maximkholmansky.currencyrates;

import com.example.maximkholmansky.currencyrates.response.ServerResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/v1/public/yql?q=select+*+from+yahoo.finance.xchange+where+pair+=+%22USDRUB,EURRUB%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=")
    Call<ServerResponse> getResponse();
}