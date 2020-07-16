package com.twitter.demo.Services.Interfaces;

import org.slf4j.LoggerFactory;
import twitter4j.*;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserTimelineFetcherInterfaceImpl implements UserTimelineFetcherInterface {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
    Twitter twitter = new TwitterFactory().getInstance();

    @Override
    public List<String> getUserTimeline() {
        List<String> tweetList = new ArrayList<String>();

        try {
            List<Status> tweets = twitter.getHomeTimeline();
            for (Status tweet : tweets) {
                tweetList.add(tweet.getId() + ": " + tweet.getUser().getName() + " " + tweet.getText());
            }
            logger.info("Retrieved all Tweets on timeline");
            logger.debug("Tweets are ready" , tweetList);
            return tweetList;
        } catch (TwitterException te) {
            logger.error("Error Encountered while trying to post: " + te.getStatusCode());
            logger.info(te.getMessage());
            return tweetList;
        }
    }

    @Override
    public String getTweetByTweetId(String tweetId) {
        String tweet = null;

        try {
            Status tweet_Id = twitter.showStatus(Long.parseLong(tweetId));
            tweet = (tweet_Id != null) ? tweet_Id.getUser().getName() + " has posted the following " + tweet_Id.getText(): null;
            logger.debug("Required tweet retrieved", tweet_Id);
            return tweet;
        } catch (TwitterException te) {
            logger.info("Error Encountered while trying to get status by Id: " + te.getStatusCode());
            logger.error(te.getMessage());
            return null;
        }
    }

    @Override
    public List<String> getTweetByQuerySearch(Query query) {
        List<String> tweetList = new ArrayList<String>();

        try {
            QueryResult queryResult;
            queryResult = twitter.search(query);
            logger.debug("QueryResult calculated", queryResult);
            List<Status> allTweets = queryResult.getTweets();
            for (Status tweet : allTweets) {
                tweetList.add(tweet.getUser().getName() + "- has posted the following: " + tweet.getText());
            }
            logger.info("Received the list of tweets", tweetList);
            return tweetList;
        } catch (TwitterException te) {
            logger.info("Error Encountered while trying to get status by Id: " + te.getStatusCode());
            logger.error(te.getMessage());
            return tweetList;
        }
    }

    @Override
    public List<String> getUserTimelineAfterPaging(Integer pageNumber, Integer numberOfTweets) {
        List<String> tweetList = new ArrayList<String>();

        try {
            Paging paging = new Paging(pageNumber, numberOfTweets);
            List<Status> tweets = twitter.getHomeTimeline(paging);
            for (Status tweet : tweets) {
                tweetList.add(tweet.getId() + ": " + tweet.getUser().getName() + " " + tweet.getText());
            }
            logger.debug("TweetList is ready" , tweetList);
            return tweetList;
        } catch (TwitterException te) {
            logger.error("Error Encountered while trying to post: " + te.getStatusCode());
            logger.info(te.getMessage());
            return tweetList;
        }
    }

    @Override
    public List<String> getAllTrendingLocations() {
        List<String> responseList = null;
        try {
            ResponseList<Location> locationList = twitter.getAvailableTrends();

            for (Location location : locationList) {
                responseList.add(location.getName() + ", " + location.getCountryName() + ", " + location.getPlaceName()
                        + ", " + location.toString());
            }
            logger.info("Locations found" + locationList);
            logger.debug("All locations found", locationList);
            return responseList;
        } catch (TwitterException te) {
            logger.info("Error Encountered while trying to get Trendy Location: " + te.getStatusCode());
            logger.error(te.getMessage());
            return responseList;
        }
    }
}
