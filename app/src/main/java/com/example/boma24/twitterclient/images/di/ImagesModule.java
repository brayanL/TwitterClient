package com.example.boma24.twitterclient.images.di;

import com.example.boma24.twitterclient.api.CustomTwitterApiClient;
import com.example.boma24.twitterclient.entities.Image;
import com.example.boma24.twitterclient.images.ImagesInteractor;
import com.example.boma24.twitterclient.images.ImagesInteractorImpl;
import com.example.boma24.twitterclient.images.ImagesPresenter;
import com.example.boma24.twitterclient.images.ImagesRepository;
import com.example.boma24.twitterclient.images.ImagesRepositoryImpl;
import com.example.boma24.twitterclient.images.ui.ImagesPresenterImpl;
import com.example.boma24.twitterclient.images.ui.ImagesView;
import com.example.boma24.twitterclient.images.ui.adapters.ImageAdapter;
import com.example.boma24.twitterclient.images.ui.adapters.OnItemClickListener;
import com.example.boma24.twitterclient.lib.base.EventBus;
import com.example.boma24.twitterclient.lib.base.ImageLoader;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by boma24 on 6/12/17.
 */
@Module
public class ImagesModule {
    private ImagesView view;
    private OnItemClickListener clickListener;

    public ImagesModule(ImagesView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    ImageAdapter providesAdapter(List<Image> dataset, ImageLoader imageLoader,
                                 OnItemClickListener clickListener){
        return new ImageAdapter(dataset, imageLoader, clickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.clickListener;
    }

    //This case return Empty List in some moment any method would be to use this list, after this
    //instance
    @Provides
    @Singleton
    List<Image> providesItemList(){
        return new ArrayList<Image>();
    }

    //Presenter
    @Provides
    @Singleton
    ImagesPresenter providesImagesPresenter(ImagesView view, EventBus eventBus,
                                            ImagesInteractor interactor){
        return new ImagesPresenterImpl(view, eventBus, interactor);
    }

    @Provides
    @Singleton
    ImagesView providesImageView(){
        return this.view;
    }

    @Provides
    @Singleton
    ImagesInteractor providesImageInteractor(ImagesRepository repository){
        return new ImagesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ImagesRepository providesImageRepository(EventBus eventBus, CustomTwitterApiClient client){
        return new ImagesRepositoryImpl(eventBus, client);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(Session session){
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    Session providesTwitter(){
        return Twitter.getSessionManager().getActiveSession();
    }
}
