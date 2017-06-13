package com.example.boma24.twitterclient;

import android.app.Application;
import android.support.v4.app.Fragment;


import com.example.boma24.twitterclient.images.di.DaggerImagesComponent;
import com.example.boma24.twitterclient.images.di.ImagesComponent;
import com.example.boma24.twitterclient.images.di.ImagesModule;
import com.example.boma24.twitterclient.images.ui.ImagesView;
import com.example.boma24.twitterclient.images.ui.adapters.OnItemClickListener;
import com.example.boma24.twitterclient.lib.base.di.LibsModule;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

/**
 * Created by boma24 on 6/9/17.
 */

public class TwitterClientApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        intiFabric();
    }

    private void intiFabric() {
        TwitterAuthConfig twitterAuthConfig = new TwitterAuthConfig(BuildConfig.TWITTER_KEY,
                BuildConfig.TWITTER_SECRET);
        Fabric.with(this, new Twitter(twitterAuthConfig));
    }

    public ImagesComponent getImagesComponent(Fragment fragment, ImagesView imagesView,
                                              OnItemClickListener clickListener){
        return DaggerImagesComponent
                .builder()
                .libsModule(new LibsModule(fragment))
                .imagesModule(new ImagesModule(imagesView, clickListener))
                .build();
    }
}
