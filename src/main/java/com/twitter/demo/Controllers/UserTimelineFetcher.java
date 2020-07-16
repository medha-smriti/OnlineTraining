package com.twitter.demo.Controllers;

import com.twitter.demo.Services.Interfaces.UserTimelineFetcherInterfaceImpl;
import com.twitter.demo.Services.Interfaces.UserTimelineTweetsInterfaceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.*;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <h1>Getting Newsfeed/timeline of User</h1>
 * <p>Reloading the timeline of the logged in user,
 * with the new feeds that were generated from the previous time
 * it was loaded.
 * Searches for a tweet using keyword.
 * Loads a single status using a status id.
 * provides a list of location considered trendy.
 * </p>
 *
 *  @author medha.smriti
 *  @since  14.07.2020
 *  @version 1.0
 */
@RestController
public class UserTimelineFetcher {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
    UserTimelineFetcherInterfaceImpl userTimelineFetcherInterfaceImp = new UserTimelineFetcherInterfaceImpl();

    /**
     * Getting all the posts from users timeline,
     * from the time it was loaded previously.
     * @return a list of all all the tweets posted on the timeline
     */
    @GetMapping(path = "/getUserTimelineTweets")
    public List<String> getAllTweetsOnTimeline() {
        return userTimelineFetcherInterfaceImp.getUserTimeline();
    }

    /**
     * Getting a single post from the user's timeline,
     * which has the status id as the input value.
     * @param tweetId
     * @return the tweet which has tweet id as the input parameter
     */
    @GetMapping(path = "/getTweet/{tweetId}")
    public String getTweetById(@PathVariable String tweetId) {
        if (tweetId.equals(null)) {
            logger.error("Enter a tweet Id to proceed", tweetId);
            return "Enter a TweetId";
        }
        return userTimelineFetcherInterfaceImp.getTweetByTweetId(tweetId);
    }

    /**
     * Getting posts from the user's timeline,
     * which satisfies the input query.
     * @param query
     * @return a list of tweets which contains the query.
     */
    @GetMapping(path = "/searchTweets")
    public List<String> getTweetByQuery(@RequestParam Query query) {
        if (query.getQuery().equals("")) {
            logger.error("Enter a query term to move forward", query);
            return null;
        }
        return userTimelineFetcherInterfaceImp.getTweetByQuerySearch(query);
    }

    /**
     * Getting all the posts from users timeline,
     * after applying pagination.
     * @param pageNumber
     * @param numberOfTweets
     * @return a list of all all the tweets posted on the timeline
     */
    @GetMapping(path = "/getTweetsAfterPagination")
    public List<String> getTweetsAfterPaging(@RequestParam  Integer pageNumber, @RequestParam  Integer numberOfTweets) {
        return userTimelineFetcherInterfaceImp.getUserTimelineAfterPaging(pageNumber, numberOfTweets);
    }

    /**
     * Gets a List of all the locations which are considered
     * trendy according to twitter
     * @return a list of all the location which are trending on twitter
     */
    @GetMapping(path = "/getAllTrendyLocations")
    public List<String> getAllLatestTrendyLocations() {
         return userTimelineFetcherInterfaceImp.getAllTrendingLocations();
    }
}

