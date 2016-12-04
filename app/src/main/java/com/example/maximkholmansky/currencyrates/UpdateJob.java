package com.example.maximkholmansky.currencyrates;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

/**
 * Created by maximkholmansky on 04/12/16.
 */

public class UpdateJob extends Job{
    public static final String TAG = "job_demo_tag";
    @Override
    @NonNull
    protected Result onRunJob(Params params) {
        // run your job here
        return Result.SUCCESS;
    }
    public static void scheduleJob() {
        new JobRequest.Builder(UpdateJob.TAG)
                .setExecutionWindow(30_000L, 40_000L)
                .build()
                .schedule();
    }
}

