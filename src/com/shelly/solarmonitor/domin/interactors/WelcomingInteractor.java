package com.shelly.solarmonitor.domin.interactors;

import com.shelly.solarmonitor.domin.interactors.base.Interactor;

public interface WelcomingInteractor extends Interactor {

    interface Callback {
        void onMessageRetrieved(String message);

        void onRetrievalFailed(String error);
    }
}

