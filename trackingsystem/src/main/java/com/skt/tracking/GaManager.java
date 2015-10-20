package com.skt.tracking;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.skt.tracking.util.Strings;

public class GaManager
{
    private static final String TAG = "GA Manager";

    private boolean enabled = false;
    private boolean debugMode = false;
    private Tracker mTracker;

    private static GaManager instance = new GaManager();

    public static GaManager getInstance()
    {
        return instance;
    }

    private GaManager()
    {
    }

    public void init(final Context context, final String trackerId, final boolean enabled, final boolean debugMode)
    {
        if(context == null || Strings.isEmpty(trackerId))
        {
            throw new IllegalArgumentException("Context or Tracker Id should not be null or empty to initialized the GA Tracker.");
        }
        this.enabled = enabled;
        this.debugMode = debugMode;

        GoogleAnalytics analytics = GoogleAnalytics.getInstance(context);
        mTracker = analytics.newTracker(trackerId);
    }

    private boolean check(String objectTracked)
    {
        if(enabled)
        {
            return true;
        }
        else if(mTracker != null)
        {
            Log.w(TAG, "Cannot track " + objectTracked + ". Call 'init()' before.");
        }
        return false;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void enable()
    {
        this.enabled = true;
    }

    public void disable()
    {
        this.enabled = false;
    }

    public void trackEvent(String category, String action, String label, long value)
    {
        if(check("event"))
        {
            if(!debugMode)
            {
                mTracker.send(new HitBuilders.EventBuilder().setCategory(category).setAction(action).setLabel(label).setValue(value)
                                      .build());
            }
            else
            {
                StringBuilder builder = new StringBuilder();
                builder.append("trackEvent(").append("\"").append(category).append("\", ").append("\"").append(action).append("\", ")
                        .append("\"").append(label).append("\", ").append(value).append(");");
                Log.i(TAG, builder.toString());
            }
        }
    }

    public void trackScreen(String screenName)
    {
        if(check("screen"))
        {
            if(!debugMode)
            {
                mTracker.setScreenName(screenName);
                mTracker.send(new HitBuilders.ScreenViewBuilder().build());
            }
            else
            {
                Log.i(TAG, "trackScreen(\"" + screenName + "\");");
            }
        }
    }
}
