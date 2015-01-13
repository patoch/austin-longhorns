package com.datastax.demo.austinlonghorns

import java.util.Date

/**
 * Created by patrick on 13/01/15.
 */


case class Tweet(id:String,
                 creation_ts: Date,
                 user: String,
                 text: String,
                 hashtags: String,
                 mentions: String,
                 location: String,
                 favoriteCount: Int,
                 retweetCount: Int)
