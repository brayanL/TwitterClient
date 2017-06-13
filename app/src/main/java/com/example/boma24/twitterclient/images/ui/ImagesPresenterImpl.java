package com.example.boma24.twitterclient.images.ui;

import android.util.Log;

import com.example.boma24.twitterclient.images.ImagesInteractor;
import com.example.boma24.twitterclient.images.ImagesPresenter;
import com.example.boma24.twitterclient.images.event.ImagesEvent;
import com.example.boma24.twitterclient.lib.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

import static android.content.ContentValues.TAG;

/**
 * Created by boma24 on 6/12/17.
 */

public class ImagesPresenterImpl implements ImagesPresenter {

    private ImagesView view;
    private EventBus eventBus;
    private ImagesInteractor interactor;

    public ImagesPresenterImpl(ImagesView view, EventBus eventBus, ImagesInteractor interactor) {
        this.view = view;
        this.eventBus = eventBus;
        this.interactor = interactor;
    }

    @Override
    public void onResumen() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getImagesTweets() {
        if(view!=null){
            Log.d(TAG, "getImagesTweets: ShowProgress");
            view.hideImages();
            view.showProgress();
        }
        interactor.execute();

    }

    @Override
    @Subscribe
    public void onEventMainThread(ImagesEvent event) {
        Log.d(TAG, "onEventMainThread: Llego");
        Log.d(TAG, "onEventMainThread: "+event.getImages());
        String errorMsg = event.getError();
        if(view!=null){
            Log.d(TAG, "onEventMainThread: no es null");
            view.showImages();
            view.hideProgress();

            if(errorMsg!= null){
                view.onError(errorMsg);
            }else {
                Log.d(TAG, "onEventMainThread: ");
                view.setContent(event.getImages());
            }
        }
    }
}
