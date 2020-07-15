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
    public List<String> getUserTimeline() throws NullPointerException{

        List<String> statusList = new ArrayList<String>();

        try {
            List<Status> statuses = twitter.getHomeTimeline();
            for (Status status : statuses) {
                statusList.add(status.getId() + ": " + status.getUser().getName() + " " + status.getText());
            }
            logger.debug("Statuslist is ready" , statusList);
            return statusList;
        } catch (TwitterException te) {
            logger.error("Error Encountered while trying to post: " + te.getStatusCode());
            logger.info(te.getMessage());
            return statusList;
        }
    }

    @Override
    public String getTweetByTweetId(String tweetId) {

        String status = null;
        try {
            Status status1 = twitter.showStatus(Long.parseLong(tweetId));
            logger.debug("Required status retrieved", status1);
            status = status1.getUser().getName() + " has posted the following " + status1.getText();
            return status;
        } catch (TwitterException te) {
            logger.info("Error Encountered while trying to get status by Id: " + te.getStatusCode());
            logger.error(te.getMessage());
            return status;
        }
    }

    @Override
    public List<String> getTweetByQuerySearch(Query query) {
        List<String> statusList = new ArrayList<String>();

        try {
            QueryResult queryResult;
            queryResult = twitter.search(query);
            logger.debug("QueryResult calculated", queryResult);
            List<Status> allStatus = queryResult.getTweets();
            for (Status status : allStatus) {
                statusList.add(status.getUser().getName() + "- has posted the following: " + status.getText());
            }
            return statusList;
        } catch (TwitterException te) {
            logger.info("Error Encountered while trying to get status by Id: " + te.getStatusCode());
            logger.error(te.getMessage());
            return statusList;
        }
    }

    @Override
    public List<String> getUserTimelineAfterPaging() {
        List<String> statusList = new ArrayList<String>();

        try {
            Paging paging = new Paging(1, 20);
            List<Status> statuses = twitter.getHomeTimeline(paging);
            for (Status status : statuses) {
                statusList.add(status.getId() + ": " + status.getUser().getName() + " " + status.getText());
            }
            logger.debug("Statuslist is ready upto 20 posts" , statusList);
            return statusList;
        } catch (TwitterException te) {
            logger.error("Error Encountered while trying to post: " + te.getStatusCode());
            logger.info(te.getMessage());
            return statusList;
        }
    }

    @Override
    public List<String> getAllTrendingLocations() {
        List<String> responseList = null;
        try {
            ResponseList<Location> locationList = twitter.getAvailableTrends();

            for (Location location : locationList) {
                System.out.println(location);
                responseList.add(location.getName() + ", " + location.getCountryName() + ", " + location.getPlaceName()
                        + ", " + location.toString());
            }
            logger.debug("All locations found", locationList);
            return responseList;
        } catch (TwitterException te) {
            logger.info("Error Encountered while trying to get Trendy Location: " + te.getStatusCode());
            logger.error(te.getMessage());
            return responseList;
        }
    }
}
