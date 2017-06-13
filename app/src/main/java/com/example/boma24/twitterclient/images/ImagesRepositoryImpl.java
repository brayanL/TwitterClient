package com.example.boma24.twitterclient.images;

import android.util.Log;

import com.example.boma24.twitterclient.api.CustomTwitterApiClient;
import com.example.boma24.twitterclient.entities.Image;
import com.example.boma24.twitterclient.images.event.ImagesEvent;
import com.example.boma24.twitterclient.lib.base.EventBus;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.crashlytics.android.answers.Answers.TAG;

/**
 * Created by boma24 on 6/12/17.
 */

public class ImagesRepositoryImpl implements ImagesRepository {
    private EventBus eventBus;
    private CustomTwitterApiClient client;
    private final static int TWEET_COUNT = 100;

    public ImagesRepositoryImpl(EventBus eventBus, CustomTwitterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }

    @Override
    public void getImages() {
        Callback<List<Tweet>> callback = new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                Log.d(TAG, "success: Get images Twitter");
                List<Image> items = new ArrayList<>();
                for (Tweet tweet : result.data){
                    if(containsImages(tweet)){
                        Image tweetModel = new Image();

                        tweetModel.setId(tweet.idStr);
                        tweetModel.setFavoriteCount(tweet.favoriteCount);

                        String tweetText = tweet.text;

                        int index = tweetText.indexOf("http");
                        if(index > 0){
                            tweetText = tweetText.substring(0, index);
                        }
                        tweetModel.setTweetText(tweetText);

                        MediaEntity currentPhoto = tweet.entities.media.get(0);
                        final String imageUrl = currentPhoto.mediaUrl;
                        tweetModel.setImageUrl(imageUrl);

                        items.add(tweetModel);

                        Collections.sort(items, new Comparator<Image>() {
                            @Override
                            public int compare(Image o1, Image o2) {
                                return o2.getFavoriteCount() - o1.getFavoriteCount();
                            }
                        });
                        post(items);
                    }
                }
            }

            @Override
            public void failure(TwitterException e) {
                Log.e(TAG, "failure Twitter: ", e);
                post(e.getLocalizedMessage());
            }
        };
        client.getTimeLineServices().homeTimeLine(TWEET_COUNT, true, true, true, callback);
    }

    //Only Images
    private boolean containsImages(Tweet tweet){
        return tweet.entities !=null &&
                tweet.entities.media!=null &&
                !tweet.entities.media.isEmpty();
    }

    private void post(List<Image> images){
        post(images, null);
    }

    private void post(String error){
        post(null, error);
    }

    private void post(List<Image> images, String error){
        ImagesEvent event = new ImagesEvent();
        event.setError(error);
        event.setImages(images);
        eventBus.post(event);
    }
}
