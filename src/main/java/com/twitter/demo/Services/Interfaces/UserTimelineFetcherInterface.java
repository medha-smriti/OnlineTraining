package com.twitter.demo.Services.Interfaces;

import twitter4j.Query;
import twitter4j.Status;

import java.util.List;

public interface UserTimelineFetcherInterface {

    List<String> getUserTimeline();

    String getTweetByTweetId(String tweetId);

    List<String> getTweetByQuerySearch(Query query);

    List<String> getUserTimelineAfterPaging(Integer pageNumber, Integer numberOfTweets);

    List<String> getAllTrendingLocations();
}
