package com.example.maximkholmansky.currencyrates;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by maximkholmansky on 04/12/16.
 */

public class Storage {
    private final SharedPreferences preferences;

    public Storage(Context context) {
        preferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    public float getUsdRate() {
        // тут достаешь String из sharedPreferences,
        return preferences.getFloat("usd", 0f);
    }

    public float getEurRate() {
        return preferences.getFloat("eur", 0f);
    }

    public void saveUsdRate(float usdRate) {
        preferences.edit().putFloat("usd", usdRate).commit();
    }

    public void saveEurRate(float eurRate) {
        preferences.edit().putFloat("eur", eurRate).commit();
    }
}
