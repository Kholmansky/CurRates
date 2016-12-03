package com.example.maximkholmansky.currencyrates.response;

import com.google.gson.annotations.SerializedName;

public class Query {

    @SerializedName("count")
    public Integer count;
    @SerializedName("created")
    public String created;
    @SerializedName("lang")
    public String lang;
    @SerializedName("results")
    public Results results;

}