package com.example.boma24.twitterclient.lib.base;

/**
 * Created by boma24 on 6/9/17.
 */

public interface EventBus {
    void register(Object subscribe);
    void unregister(Object subscribe);
    void post(Object event);
}
