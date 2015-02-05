DROP KEYSPACE IF EXISTS austin_longhorns_ks;

CREATE KEYSPACE austin_longhorns_ks WITH replication = {'class': 'NetworkTopologyStrategy', 'Cassandra' : 2, 'Analytics' : 1, 'Solr' : 1};

USE austin_longhorns_ks;

CREATE TABLE tweets2 (
    id VARCHAR,
    creation_day VARCHAR,
    creation_at TIMESTAMP,
    user_id VARCHAR,
    user_name VARCHAR,
    text VARCHAR,
    retweet_count INT,
    favorite_count INT,
    sentiment double,
    PRIMARY KEY(creation_day, creation_at, id)
);

CREATE TABLE tweets_by_user_id2 (
    id VARCHAR,
    creation_day VARCHAR,
    creation_at TIMESTAMP,
    user_id VARCHAR,
    user_name VARCHAR,
    text VARCHAR,
    retweet_count INT,
    favorite_count INT,
    PRIMARY KEY(creation_day, user_id, id)
);


CREATE TABLE tweets_by_retweet_count2 (
    id VARCHAR,
    creation_day VARCHAR,
    creation_at TIMESTAMP,
    user_id VARCHAR,
    user_name VARCHAR,
    text VARCHAR,
    retweet_count INT,
    favorite_count INT,
    PRIMARY KEY(creation_day, retweet_count, id)
);

CREATE TABLE tweets_by_favorite_count2 (
    id VARCHAR,
    creation_day VARCHAR,
    creation_at TIMESTAMP,
    user_id VARCHAR,
    user_name VARCHAR,
    text VARCHAR,
    retweet_count INT,
    favorite_count INT,
    PRIMARY KEY(creation_day, favorite_count, id)
);

CREATE TABLE dictionnary (
    id VARCHAR,
    word VARCHAR,
    count INT,
    value INT,
    PRIMARY KEY(id, word)
);