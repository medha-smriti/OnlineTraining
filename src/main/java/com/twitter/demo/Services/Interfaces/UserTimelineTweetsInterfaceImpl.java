package com.twitter.demo.Services.Interfaces;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class UserTimelineTweetsInterfaceImpl implements  UserTimelinetweetsInterface{

    Twitter twitter = new TwitterFactory().getInstance();

    @Override
    public String addTweets (String tweet){
        try {
            Status status = twitter.updateStatus(tweet);
            return ("Tweet successful with status: " + status.getText());
        } catch (TwitterException te) {
            return "Error Encountered while trying to post: " + te.getStatusCode() + "\n" + te.getMessage();
        }
    }
}
