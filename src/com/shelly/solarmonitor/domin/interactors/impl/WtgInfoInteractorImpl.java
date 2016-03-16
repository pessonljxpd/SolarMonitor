package com.shelly.solarmonitor.domin.interactors.impl;

import com.shelly.solarmonitor.domin.executor.Executor;
import com.shelly.solarmonitor.domin.executor.MainThread;
import com.shelly.solarmonitor.domin.interactors.WtgInfoInteractor;
import com.shelly.solarmonitor.domin.interactors.base.AbstractInteractor;
import com.shelly.solarmonitor.domin.repository.WtgInfoRepository;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class WtgInfoInteractorImpl extends AbstractInteractor implements WtgInfoInteractor {

    private WtgInfoInteractor.Callback mCallback;
    private WtgInfoRepository            mMessageRepository;

    public WtgInfoInteractorImpl(Executor threadExecutor,
                                   MainThread mainThread,
                                   Callback callback, WtgInfoRepository messageRepository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mMessageRepository = messageRepository;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onRetrievalFailed("Nothing to welcome you with :(");
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
        final String message = mMessageRepository.getWtgInfo();

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