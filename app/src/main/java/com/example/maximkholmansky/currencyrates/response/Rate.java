package com.example.maximkholmansky.currencyrates.response;

import com.google.gson.annotations.SerializedName;

public class Rate {

    @SerializedName("id")
    public String id;
    @SerializedName("Name")
    public String name;
    @SerializedName("Rate")
    public String rate;
    @SerializedName("Date")
    public String date;
    @SerializedName("Time")
    public String time;
    @SerializedName("Ask")
    public String ask;
    @SerializedName("Bid")
    public String bid;

}