package com.example.boma24.twitterclient.lib.base.di;

import dagger.Module;
import dagger.Provides;
import android.support.v4.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.boma24.twitterclient.lib.GlideImageLoader;
import com.example.boma24.twitterclient.lib.GreenRobotEventBus;
import com.example.boma24.twitterclient.lib.base.EventBus;
import com.example.boma24.twitterclient.lib.base.ImageLoader;

import javax.inject.Singleton;

/**
 * Created by boma24 on 6/12/17.
 */

@Module
public class LibsModule {

    private Fragment fragment;

    public LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Fragment fragment){
        return Glide.with(fragment);
    }

    @Provides
    @Singleton
    Fragment providesFragment(){
        return this.fragment;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }
}

