# OnlineTraining/ TwitterAPI

This java application contains warapper APIs, to interact with Twitter API. 
The various Methods to interact are:
1. Post Tweet
2. Get User Timeline Tweets
3. Search for Tweets using keywords
4. Get any Tweet using the tweetId
5. Get all trending locations on Twitter
6. Get User Feeds after paging

Post Tweet:
   This endpoint is used for creating a new tweet for the user. 
   Endpoints: localhost:8080/tweet
   RequestBody: Any string you want to put as a tweet.
   Response: "Tweet successful with status: <RequestBody> "
  
Get User Timeline Tweets:
   This endpoint is used for creating a new tweet for the user. 
   Endpoints: localhost:8080/getUserTimelineTweets
   RequestBody: n/a
   Response: List of Tweets appear
   
Search for Tweets using keywords
   This endpoint is used to search for a tweet using keywords. 
   Endpoints: localhost:8080/searchTweets?query=<keyword>
   RequestParamter: query = <>
   Response: list of tweets realated to the query keyword. 
  
Search for Tweets using TweetId
   This endpoint is used to search for a tweet using it's id. 
   Endpoints: localhost:8080/getTweet/{id}
   PathVariable: id (long)
   Response: Return the tweet mapped to the id.
   
Get all the Trending locations on Twitter. 
   This endpoint is used to know the most tending locations on Twitter. 
   Endpoints: localhost:8080/getAllTrendyLocations
   PathVariable: n/a
   Response: Return a list of locations
  
Get Tweet feeds on a User Timeline after filtering via pagination.
   Fetches for the tweets on a particular page and upto to a certain number. 
   Endpoints: localhost:8080/getTweetsAfterPagination?pageNumber=<x>&numberOfTweets=<y>
   RequestParameters: pageNumber, numberofTweets. 
   Response: list of tweets on page x upto y feeds. 
  
