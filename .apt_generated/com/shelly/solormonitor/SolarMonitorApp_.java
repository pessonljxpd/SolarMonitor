//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.2.
//


package com.shelly.solormonitor;


public final class SolarMonitorApp_
    extends SolarMonitorApp
{

    private static SolarMonitorApp INSTANCE_;

    public static SolarMonitorApp getInstance() {
        return INSTANCE_;
    }

    /**
     * Visible for testing purposes
     * 
     */
    public static void setForTesting(SolarMonitorApp application) {
        INSTANCE_ = application;
    }

    @Override
    public void onCreate() {
        INSTANCE_ = this;
        init_();
        super.onCreate();
    }

    private void init_() {
    }

}
