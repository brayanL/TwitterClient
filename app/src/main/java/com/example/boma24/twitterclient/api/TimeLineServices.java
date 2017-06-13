package com.example.boma24.twitterclient.api;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by boma24 on 6/9/17.
 */

public interface TimeLineServices {
    @GET("/1.1/statuses/home_timeline.json")
    void homeTimeLine(@Query("count") Integer count,
                      @Query("trim_user") Boolean trim_user,
                      @Query("exclude_replies") Boolean exclude_replies,
                      @Query("contributor_details") Boolean contributor_details,
                      Callback<List<Tweet>> callback);
}
