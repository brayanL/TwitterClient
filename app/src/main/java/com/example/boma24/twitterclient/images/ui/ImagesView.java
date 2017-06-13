package com.example.boma24.twitterclient.images.ui;


import com.example.boma24.twitterclient.entities.Image;

import java.util.List;

/**
 * Created by boma24 on 6/12/17.
 */

public interface ImagesView {
    void showImages();
    void hideImages();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Image> items);

}
