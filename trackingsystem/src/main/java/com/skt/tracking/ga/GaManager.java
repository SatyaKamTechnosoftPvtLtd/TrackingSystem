package com.skt.tracking.ga;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.skt.tracking.config.TrackerConfig;
import com.skt.tracking.util.Strings;

import java.util.Map;

public final class GaManager
{
    private static GaManager instance = new GaManager();
    private boolean enabled;
    private Tracker mTracker;

    private GaManager()
    {
    }

    public static GaManager getInstance()
    {
        return instance;
    }

    public void init(final Context context, TrackerConfig config)
    {
        if(context == null || config == null || !(config instanceof GaTrackerConfig) || Strings.isEmpty(config.getTrackerId()))
        {
            throw new IllegalArgumentException(
                    "Context or Tracker Configuration should not be null or empty to initialized the GA Tracker. Tracker Id is mandatory in tracker configuration.");
        }

        this.enabled = config.isEnabled();

        GoogleAnalytics analytics = GoogleAnalytics.getInstance(context);
        //mTracker = analytics.getTracker(config.getTrackerId());
        mTracker = analytics.newTracker(config.getTrackerId());
        mTracker.enableExceptionReporting(((GaTrackerConfig) config).isExceptionReporting());
        mTracker.enableAutoActivityTracking(((GaTrackerConfig) config).isAutoActivityTracking());
        mTracker.setSessionTimeout(((GaTrackerConfig) config).getSessionTimeout());

        if(mTracker == null)
        {
            throw new IllegalArgumentException(
                    "Tracker is not initialized as per google guideline. Please check the configuration and try again.");
        }
    }

    public void trackEvent(String category, String action, String label, long value)
    {
        if(enabled)
        {
            mTracker.send(new HitBuilders.EventBuilder().setCategory(category).setAction(action).setLabel(label).setValue(value).build());
            //mTracker.send(MapBuilder.createEvent(category, action, label, value).build());
        }
    }

    public void trackScreen(String screenName, Map<String, String> fields)
    {
        if(enabled)
        {
            mTracker.setScreenName(screenName);
            //mTracker.set(Fields.SCREEN_NAME, screenName);

            HitBuilders.ScreenViewBuilder builder = new HitBuilders.ScreenViewBuilder();
            //MapBuilder builder = MapBuilder.createAppView();

            if(fields.size() > 0)
            {
                builder.setAll(fields);
            }
            mTracker.send(builder.build());
        }
    }
}
