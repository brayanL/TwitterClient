package com.example.boma24.twitterclient.api;

import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterApiClient;

/**
 * Created by boma24 on 6/9/17.
 */

public class CustomTwitterApiClient extends TwitterApiClient {

    public CustomTwitterApiClient(Session session) {
        super(session);
    }

    public TimeLineServices getTimeLineServices(){
        return  getService(TimeLineServices.class);
    }
}
