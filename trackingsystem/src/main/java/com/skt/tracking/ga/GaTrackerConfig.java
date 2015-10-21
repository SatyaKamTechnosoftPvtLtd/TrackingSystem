package com.skt.tracking.ga;

import com.skt.tracking.config.TrackerConfig;

public class GaTrackerConfig implements TrackerConfig
{
    private String trackerId;
    private boolean enabled;
    private long sessionTimeout;
    private boolean exceptionReporting;
    private boolean autoActivityTracking;

    private GaTrackerConfig()
    {
    }

    @Override
    public String getTrackerId()
    {
        return trackerId;
    }

    @Override
    public boolean isEnabled()
    {
        return enabled;
    }

    public long getSessionTimeout()
    {
        return sessionTimeout;
    }

    public boolean isExceptionReporting()
    {
        return exceptionReporting;
    }

    public boolean isAutoActivityTracking()
    {
        return autoActivityTracking;
    }

    public static class Builder
    {
        private TrackerConfig config;

        public Builder()
        {
            config = new GaTrackerConfig();
        }

        public Builder withTrackerId(String trackerId)
        {
            ((GaTrackerConfig) config).trackerId = trackerId;
            return this;
        }

        public Builder withSessionTimeout(long sessionTimeout)
        {
            ((GaTrackerConfig) config).sessionTimeout = sessionTimeout;
            return this;
        }

        public Builder withExceptionReporting(boolean exceptionReporting)
        {
            ((GaTrackerConfig) config).exceptionReporting = exceptionReporting;
            return this;
        }

        public Builder withAutoActivityTracking(boolean autoActivityTracking)
        {
            ((GaTrackerConfig) config).autoActivityTracking = autoActivityTracking;
            return this;
        }

        public Builder withEnabled(boolean enabled)
        {
            ((GaTrackerConfig) config).enabled = enabled;
            return this;
        }

        public TrackerConfig build()
        {
            return config;
        }
    }
}
