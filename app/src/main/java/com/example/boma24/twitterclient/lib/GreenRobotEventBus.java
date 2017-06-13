package com.example.boma24.twitterclient.lib;

import com.example.boma24.twitterclient.lib.base.EventBus;

/**
 * Created by boma24 on 6/9/17.
 */

public class GreenRobotEventBus implements EventBus {
    org.greenrobot.eventbus.EventBus eventBus;
    //The methods name have the same names that EventBus Class

    public GreenRobotEventBus(org.greenrobot.eventbus.EventBus eventBus){
        this.eventBus = eventBus;
    }

    @Override
    public void register(Object subscribe) {
        eventBus.register(subscribe);
    }

    @Override
    public void unregister(Object subscribe) {
        eventBus.register(subscribe);
    }

    @Override
    public void post(Object event) {
        eventBus.post(event);
    }
}
