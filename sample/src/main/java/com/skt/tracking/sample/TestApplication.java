package com.skt.tracking.sample;

import android.app.Application;

import com.skt.tracking.config.TrackerConfig;
import com.skt.tracking.ga.GaManager;
import com.skt.tracking.ga.GaTrackerConfig;

import java.util.HashMap;

public class TestApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        TrackerConfig config = new GaTrackerConfig.Builder()
                .withTrackerId("UA-3882094-20")
                .withEnabled(true)
                .withExceptionReporting(true)
                .withSessionTimeout(300)
                .build();
        GaManager.getInstance().init(getApplicationContext(), config);
    }
}
