package com.twitter.demo.Controllers;

import com.sun.java.swing.action.OkAction;
import com.sun.tools.internal.ws.processor.model.Response;
import com.twitter.demo.Services.Interfaces.UserTimelineTweetsInterfaceImpl;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <h1>Tweeting new Posts on timeline</h1>
 * <p>When a person wants to tweet on their timeline,
 * the status is updated with the provided input string
 * </p>
 *
 *  @author medha.smriti
 *  @since  14.07.2020
 *  @version 1.0
 */
@RestController
public class UserTimelineTweets {

    UserTimelineTweetsInterfaceImpl userTimelineTweetsInterfaceImpl = new UserTimelineTweetsInterfaceImpl();

    /**
     * The method takes a string as input and tweet it
     * on the current user's timeline.
     * @param tweet
     * @return
     */
    @RequestMapping(path = "/tweet", method = RequestMethod.POST)
    public String addTweet(@RequestBody  String tweet) {
        return userTimelineTweetsInterfaceImpl.addTweets(tweet);
    }
}
