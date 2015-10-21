package com.skt.tracking.config;

public class DefaultTrackerConfig implements TrackerConfig
{
    private String trackerId;
    private boolean enabled = Boolean.TRUE;

    @Override
    public String getTrackerId()
    {
        return trackerId;
    }

    public void setTrackerId(String trackerId)
    {
        this.trackerId = trackerId;
    }

    @Override
    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
}
