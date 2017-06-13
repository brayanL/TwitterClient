package com.example.boma24.twitterclient.images.di;

import com.example.boma24.twitterclient.images.ImagesPresenter;
import com.example.boma24.twitterclient.images.ui.ImagesFragment;
import com.example.boma24.twitterclient.lib.base.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;

/**
 * Created by boma24 on 6/12/17.
 */

//Dagger implement this interface
@Singleton
@Component(modules = {LibsModule.class, ImagesModule.class})
public interface ImagesComponent {
    //two ways define dependency injection
    void inject(ImagesFragment fragment);
    ImagesPresenter getPresenter();
}
