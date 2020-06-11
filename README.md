# 172_Project

## Overview
System to search tweets from a geo-fenced area.
Uses the twitter api for collection. 
Lucene is used to index and search the tweets.
### Architecture
One docker container streams tweets from Twitter and into a mongodb.
Another annotates those documents with titles for the pages referenced.
This is then exported to a json text file.

Then we parse the json and index it in lucene for later search.
Then as lucene is used to find the most similar documents to user queries.

### Index Structure

### Search Algorithm

### Limitations

### Instructions for deployment

### Screenshots