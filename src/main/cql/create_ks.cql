DROP KEYSPACE IF EXISTS austin_longhorns_ks;

CREATE KEYSPACE austin_longhorns_ks WITH replication = {'class': 'NetworkTopologyStrategy', 'Cassandra' : 2, 'Analytics' : 1, 'Solr' : 1};

USE austin_longhorns_ks;

CREATE TABLE tweets (
    id VARCHAR,
    creation_ts TIMESTAMP,
    user VARCHAR,
    text VARCHAR,
    hashtags VARCHAR,
    mentions VARCHAR,
    location VARCHAR,
    favorite_count INT,
    retweet_count INT,
    PRIMARY KEY(id)
);

// 1;2015-01-01;tony;hey this is a tweet;a hashtag;a mention;paris;5;9


3;2015-01-01;tony;hey this is a tweet;a hashtag;a mention;paris;5;9