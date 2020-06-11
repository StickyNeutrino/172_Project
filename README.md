# 172_Project

## Overview
System to search tweets from a geo-fenced area.
Uses the twitter api for collection. 
Lucene is used to index and search the tweets.
### Architecture
One docker container streams tweets from Twitter into mongodb.
Another container annotates the documents with titles from the pages referenced.
This is then exported as a json text file.

Then we parse the json and index it in lucene.
Then lucene is used to find the documents most similar to user queries.

### Index Structure

### Search Algorithm

### Limitations

### Instructions for deployment

### WebApp

### Screenshots
