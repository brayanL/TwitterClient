package com.example.boma24.twitterclient.images.event;

import com.example.boma24.twitterclient.entities.Image;

import java.util.List;

/**
 * Created by boma24 on 6/12/17.
 */

public class ImagesEvent {
    private String error;
    private List<Image> images;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
