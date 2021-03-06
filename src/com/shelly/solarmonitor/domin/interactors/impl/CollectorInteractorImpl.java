package com.shelly.solarmonitor.domin.interactors.impl;

import com.shelly.solarmonitor.domin.executor.Executor;
import com.shelly.solarmonitor.domin.executor.MainThread;
import com.shelly.solarmonitor.domin.interactors.CollectorInteractor;
import com.shelly.solarmonitor.domin.interactors.base.AbstractInteractor;
import com.shelly.solarmonitor.domin.repository.CollectorRepository;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class CollectorInteractorImpl extends AbstractInteractor implements CollectorInteractor {

    private CollectorInteractor.Callback mCallback;
    private CollectorRepository            mMessageRepository;

    public CollectorInteractorImpl(Executor threadExecutor,
                                   MainThread mainThread,
                                   Callback callback, CollectorRepository messageRepository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mMessageRepository = messageRepository;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onRetrievalFailed("Nothing Collector Info with :(");
            }
        });
    }

    private void postMessage(final String msg) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onMessageRetrieved(msg);
            }
        });
    }

    @Override
    public void run() {

        // retrieve the message
        final String message = mMessageRepository.getCollectorInfo();

        // check if we have failed to retrieve our message
        if (message == null || message.length() == 0) {

            // notify the failure on the main thread
            notifyError();

            return;
        }

        // we have retrieved our message, notify the UI on the main thread
        postMessage(message);
    }
}