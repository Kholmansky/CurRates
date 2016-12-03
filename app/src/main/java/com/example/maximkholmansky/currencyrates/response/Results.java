package com.example.maximkholmansky.currencyrates.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Results {

    @SerializedName("rate")
    public List<Rate> rate = new ArrayList<>();

}