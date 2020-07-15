package com.twitter.demo.Controllers;

import com.twitter.demo.Services.Interfaces.UserTimelineFetcherInterfaceImpl;
import com.twitter.demo.Services.Interfaces.UserTimelineTweetsInterfaceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

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
 * <h1>Getting Newsfeed/timeline of </h1>
 * <p>Reloading the timeline of the logged in user,
 * with the new feeds that were generated from the previous time
 * it was loaded.
 * Also loads a single status using a status id.
 * And provides a list of location considered trendy.
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
    @GetMapping(path = "/getAllTweets")
    public List<String> getAllTweetsOnTimeline() {
        List<String> statuses = userTimelineFetcherInterfaceImp.getUserTimeline();
        logger.info("Got all the statuses");
        return statuses;
    }
    /**
     * Getting a single post from the user's timeline,
     * which has the status id as the input value.
     * @param tweetId
     * @return the tweet which has tweet id as the input parameter
     */
    @GetMapping(path = "/getTweetById")
    public String getTweetById(@RequestParam String tweetId) {
        if(tweetId == null) {
            logger.error("Enter a tweet Id to proceed", tweetId);
            return "Enter a TweetId";
        }
        String status = userTimelineFetcherInterfaceImp.getTweetByTweetId(tweetId);
        return  status;
    }

    /**
     * Getting few posts from the user's timeline,
     * which satisfies the input query.
     * @param query
     * @return a list of tweets which contains the query.
     */
    @GetMapping(path = "/getTweetByQuery")
    public List<String> getTweetById(@RequestParam Query query) {
        List<String> statuses = null;

        if(query == null) {
            logger.error("Enter a query term to move forward", query);
            statuses.add("Enter a correct query term");
            return statuses;
        }
        statuses = userTimelineFetcherInterfaceImp.getTweetByQuerySearch(query);
        return statuses;
    }

    /**
     * Getting all the posts from users timeline,
     * after applying pagination.
     * @return a list of all all the tweets posted on the timeline
     */
    @GetMapping(path = "/getTweetsAfterPagination")
    public List<String> getTweetsAfterPaging()
    {
        List<String> statuses = userTimelineFetcherInterfaceImp.getUserTimelineAfterPaging();
        logger.info("Got all the statuses");
        return statuses;
    }

    /**
     * Gets a List of all the locations which are considered
     * trendy according to twitter
     * @return a list of all the location which are trending on twitter
     */
    @GetMapping(path = "/getAllTrendyLocations")
    public List<String> getAllLatestTrendyLocations() {
        List<String> locationList = userTimelineFetcherInterfaceImp.getAllTrendingLocations();
        logger.info("Gained all the location details");
        return  locationList;
    }
}

