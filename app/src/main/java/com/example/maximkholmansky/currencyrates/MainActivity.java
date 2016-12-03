package com.example.maximkholmansky.currencyrates;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maximkholmansky.currencyrates.response.ServerResponse;

//Retrofit - для работы с сервером
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory; //gson для парсинга jsonа
import retrofit2.http.GET;


public class MainActivity extends AppCompatActivity {
    //api нашел тут: "https://toster.ru/q/7088"
    private static final String BASE_URL = "https://query.yahooapis.com";
    private EditText usdValueEditText;
    private EditText eurValueEditText;
    private TextView eurRubValueTextView;
    private TextView usdRubValueTextView;
    private Button getRatesButton;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private ApiService apiService;


    SharedPreferences sPref;
    private float usdRubRate;
    private float eurRubRate;
    private float myUsd;
    private float myEur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiService = retrofit.create(ApiService.class);
        getRatesButton = (Button) findViewById(R.id.buttonDoit);
        usdValueEditText = (EditText) findViewById(R.id.editTextUSD);
        eurValueEditText = (EditText) findViewById(R.id.editTextEUR);
        usdRubValueTextView = (TextView) findViewById(R.id.textViewUSD);
        eurRubValueTextView = (TextView) findViewById(R.id.textViewEURO);

        getRatesButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                executeResponse();
            }
        });
    }

    private void executeResponse() {
        apiService.getResponse().enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                String usdRubValue = response.body().query.results.rate.get(0).rate;
                String eurRubValue = response.body().query.results.rate.get(1).rate;

                usdRubValueTextView.setText("USD/RUB:" + usdRubValue);
                eurRubValueTextView.setText("EUR/RUB:" + eurRubValue);

                usdRubRate = Float.parseFloat(usdRubValue);
                eurRubRate = Float.parseFloat(eurRubValue);

                if(!usdValueEditText.getText().toString().equals("")) {
                    myUsd = Float.parseFloat(usdValueEditText.getText().toString());
                }
                else myUsd = 0;

                if(!eurValueEditText.getText().toString().equals("")) {
                    myEur = Float.parseFloat(eurValueEditText.getText().toString());
                }
                else myEur = 0;

            }
            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
            }
        });

        //save:
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed =sPref.edit();
        ed.putFloat("USDRUB",usdRubRate);
        ed.putFloat("EURRUB",eurRubRate);
        ed.putFloat("myUSD",myUsd);
        ed.putFloat("myEUR",myEur);
        ed.commit();
        Toast.makeText(this,String.valueOf(myEur)+" "+String.valueOf(myUsd), Toast.LENGTH_SHORT).show();
    }

    public interface ApiService {
        @GET("/v1/public/yql?q=select+*+from+yahoo.finance.xchange+where+pair+=+%22USDRUB,EURRUB%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=")
        Call<ServerResponse> getResponse();
    }

}