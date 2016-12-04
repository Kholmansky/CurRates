package com.example.maximkholmansky.currencyrates;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.evernote.android.job.JobRequest;
import com.example.maximkholmansky.currencyrates.response.ServerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView eurRubValueTextView;
    private TextView usdRubValueTextView;
    private Button getRatesButton;
    private ApiService apiService;
    private Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storage = new Storage(this);
        apiService = ServiceCreator.createService();
        getRatesButton = (Button) findViewById(R.id.buttonDoit);
        usdRubValueTextView = (TextView) findViewById(R.id.textViewUSD);
        eurRubValueTextView = (TextView) findViewById(R.id.textViewEURO);

        executeResponse();

        getRatesButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                new JobRequest.Builder(UpdateJob.TAG)
                        .setPeriodic(900000)
                        .setUpdateCurrent(true)
                        .build()
                        .schedule();
            }
        });
    }

    private void executeResponse() {
        apiService.getResponse().enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                float usdRate = response.body().query.results.rate.get(0).getRate();
                float eurRate = response.body().query.results.rate.get(1).getRate();
                storage.saveUsdRate(usdRate);
                storage.saveEurRate(eurRate);
                usdRubValueTextView.setText("USD/RUB:" + usdRate);
                eurRubValueTextView.setText("EUR/RUB:" + eurRate);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Что-то пошло не так", Toast.LENGTH_SHORT).show();
            }
        });
    }

}