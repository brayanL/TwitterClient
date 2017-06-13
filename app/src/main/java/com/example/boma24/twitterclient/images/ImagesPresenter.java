package com.example.boma24.twitterclient.images;

import com.example.boma24.twitterclient.images.event.ImagesEvent;

/**
 * Created by boma24 on 6/12/17.
 */

public interface ImagesPresenter {
    void onResumen();
    void onPause();
    void onDestroy();

    void getImagesTweets();
    void onEventMainThread(ImagesEvent event);
}
