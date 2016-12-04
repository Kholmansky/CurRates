package com.example.maximkholmansky.currencyrates;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

/**
 * Created by maximkholmansky on 04/12/16.
 */

public class UpdateJobCreator implements JobCreator{

        @Override
        public Job create(String tag) {
            switch (tag) {
                case UpdateJob.TAG:
                    return new UpdateJob();
                default:
                    return null;
            }
        }

}
