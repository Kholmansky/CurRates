package com.example.maximkholmansky.currencyrates;

import android.app.NotificationManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;
import com.example.maximkholmansky.currencyrates.response.ServerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateJob extends Job{

    private Storage storage;
    private ApiService service;


    public static final String TAG = "job_update_tag";
    @Override
    @NonNull
    protected Result onRunJob(Params params) {

        storage = new Storage(getContext());
        service = ServiceCreator.createService();
        service.getResponse().enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                float newUsdRate = response.body().query.results.rate.get(0).getRate();
                float newEurRate = response.body().query.results.rate.get(1).getRate();
                float oldUsdRate = storage.getUsdRate();
                float oldEurRate = storage.getEurRate();

                if(newUsdRate!=oldUsdRate || newEurRate!=oldEurRate){

                    final NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(getContext())
                                    .setSmallIcon(android.R.drawable.ic_menu_report_image)
                                    .setContentTitle("My notification")
                                    .setContentText("USDRUB:" + String.valueOf(newUsdRate)+"  EURRUB:" + String.valueOf(newEurRate));
                    final NotificationManager mNotifyMgr = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotifyMgr.notify(1, mBuilder.build());

                }
            }
            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });

        return Result.SUCCESS;
    }
    public static void scheduleJob() {
        new JobRequest.Builder(UpdateJob.TAG)
                .setExecutionWindow(30_000L, 40_000L)
                .build()
                .schedule();
    }
}

