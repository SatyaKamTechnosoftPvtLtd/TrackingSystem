package com.skt.tracking.sample;

import android.app.Activity;
import android.os.Bundle;

import com.skt.tracking.annotation.TrackScreenView;

public class TestActivity extends Activity
{
    @TrackScreenView(name = "TestScreen")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}
