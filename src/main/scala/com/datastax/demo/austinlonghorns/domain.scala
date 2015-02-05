package com.datastax.demo.austinlonghorns

import java.util.Date

/**
 * Created by patrick on 13/01/15.
 */


case class Tweet(id:String,
                 creation_day: String,
                 creation_at: Date,
                 text: String,
                 user_id: String,
                 user_name: String,
                 retweet_count: Int,
                 favorite_count: Int,
                 sentiment: String)


case class Word(id: String,
                 word:String,
                 count: String,
                 value: String)
