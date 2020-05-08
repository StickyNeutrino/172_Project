const Twitter = require('twitter-lite');
const mongodb = require('mongodb');

var MongoClient = mongodb.MongoClient;
var url = "mongodb://root:example@mongo"

async function run() {

const app = new Twitter({
  consumer_key: process.env.TWITTER_CONSUMER_KEY,
  consumer_secret: process.env.TWITTER_CONSUMER_SECRET,
  access_token_key: process.env.TWITTER_TOKEN_KEY,
  access_token_secret: process.env.TWITTER_TOKEN_SECRET
});

function collectTweets( db ) {
  function tweetHandler( tweet ) {
        var dbo = db.db("tweets");
        var myobj = { tweeter: tweet };
        dbo.collection("texts").insertOne(myobj, function(err, res) {
          if (err) throw err;
          console.log("1 document inserted");
          // db.close();
        });
    // console.log( tweet ) ;
  }

  const params = {
    locations: '-117.347213,32.559664,-116.905013,32.956942'
  }

  const stream = app.stream('statuses/filter', params)
    .on("data", tweetHandler)
}

MongoClient.connect( url, function( err, db) { 
  if ( err ) throw err;
  console.log( "Attached to MongoDB" );
  collectTweets( db );
  // db.close();
});

}

run()
