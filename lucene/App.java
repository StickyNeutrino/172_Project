//package TweetLucene;

import com.mongodb.MongoClient;


import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;


public class App
{
    public static void main( String[] args )
    {
        MongoClient mongoClient = new MongoClient( "mongo" );
        MongoDatabase database = mongoClient.getDatabase("tweets");
        MongoCollection<Document> collection = database.getCollection("test");
    }
}
