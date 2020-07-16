package com.twitter.demo.Services.Interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.lang.invoke.MethodHandles;

public class UserTimelineTweetsInterfaceImpl implements  UserTimelinetweetsInterface{

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
    Twitter twitter = new TwitterFactory().getInstance();

    @Override
    public String addTweets (String tweet) {
        try {
            Status status = twitter.updateStatus(tweet);
            logger.info("Posted tweet successfully");
            return ("Tweet successful with status: " + status.getText());
        } catch (TwitterException te) {
            return "Error Encountered while trying to post: " + te.getStatusCode() + "\n" + te.getMessage();
        }
    }
}
