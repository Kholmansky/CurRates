package com.example.maximkholmansky.currencyrates.response;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class Rate {

    private static final String TAG = "Rate";

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

    public float getRate() {
        try {
            return Float.parseFloat(rate);
        } catch (NumberFormatException e) {
            Log.d(TAG, "getRate: ");
            return 0;
        }
    }
}