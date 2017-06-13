package com.example.boma24.twitterclient.images;

/**
 * Created by boma24 on 6/12/17.
 */

public class ImagesInteractorImpl implements ImagesInteractor {

    private ImagesRepository repository;

    public ImagesInteractorImpl(ImagesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getImages();
    }
}
