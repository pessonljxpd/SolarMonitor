package com.shelly.solarmonitor.presentation.presenter.impl;

import com.shelly.solarmonitor.domin.executor.Executor;
import com.shelly.solarmonitor.domin.executor.MainThread;
import com.shelly.solarmonitor.domin.interactors.CollectorInteractor;
import com.shelly.solarmonitor.domin.interactors.impl.CollectorInteractorImpl;
import com.shelly.solarmonitor.domin.repository.CollectorRepository;
import com.shelly.solarmonitor.presentation.presenter.MainPresenter;
import com.shelly.solarmonitor.presentation.presenter.base.AbstractPresenter;

public abstract class MainPresenterImpl extends AbstractPresenter implements MainPresenter,
        CollectorInteractor.Callback {

    private MainPresenter.View mView;
    private CollectorRepository  mCollectorRepository;
	private CollectorInteractor mInteractor;

    public MainPresenterImpl(Executor executor, MainThread mainThread,
                             View view, CollectorRepository wtgInfoRepository) {
        super(executor, mainThread);
        this.mView = view;
        this.mCollectorRepository = wtgInfoRepository;
        // initialize the interactor
        this.mInteractor = new CollectorInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mCollectorRepository
        );

    }

    @Override
    public void resume() {

        mView.showLoading(null);
        
        // run the interactor
        mInteractor.execute();
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
        mView.displayCollectorInfo(message);
    }

    @Override
    public void onRetrievalFailed(String error) {
        mView.hideLoading();
        onError(error);
    }
}

