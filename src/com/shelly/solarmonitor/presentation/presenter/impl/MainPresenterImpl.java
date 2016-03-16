package com.shelly.solarmonitor.presentation.presenter.impl;

import com.shelly.solarmonitor.domin.executor.Executor;
import com.shelly.solarmonitor.domin.executor.MainThread;
import com.shelly.solarmonitor.domin.interactors.WtgInfoInteractor;
import com.shelly.solarmonitor.domin.interactors.impl.WtgInfoInteractorImpl;
import com.shelly.solarmonitor.domin.repository.WtgInfoRepository;
import com.shelly.solarmonitor.presentation.presenter.MainPresenter;
import com.shelly.solarmonitor.presentation.presenter.base.AbstractPresenter;

public class MainPresenterImpl extends AbstractPresenter implements MainPresenter,
        WtgInfoInteractor.Callback {

    private MainPresenter.View mView;
    private WtgInfoRepository  mWtgInfoRepository;

    public MainPresenterImpl(Executor executor, MainThread mainThread,
                             View view, WtgInfoRepository wtgInfoRepository) {
        super(executor, mainThread);
        mView = view;
        mWtgInfoRepository = wtgInfoRepository;
    }

    @Override
    public void resume() {

        mView.showLoading("");

        // initialize the interactor
        WtgInfoInteractor interactor = new WtgInfoInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mWtgInfoRepository
        );

        // run the interactor
        interactor.execute();
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {
        mView.showError(message);
    }

    @Override
    public void onMessageRetrieved(String message) {
    	mView.hideLoading();
    	System.out.println(mView+message);
        mView.displayWtgInfo(message);
    }

    @Override
    public void onRetrievalFailed(String error) {
        mView.hideLoading();
        onError(error);
    }
}

