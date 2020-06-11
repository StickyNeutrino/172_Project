import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

//package TweetLucene;


class Tweet {
  public String date;
  public String coords;
  public String user;
  public String text;
  public String hashtags;
  public String link;
  public String ptitle;

  Tweet(String d, String c, String u, String t, String h, String l, String p) {
    this.date = d;
    this.coords = c;
    this.user = u;
    this.text = t;
    this.hashtags = h;
    this.link = l;
    this.ptitle = p;
  }
}

public class App
{
    public static void main( String[] args ) throws IOException
    {

        String json = new String(Files.readAllBytes(Paths.get("CS171_DATA")));

        String pretty = Pretty.toPrettyFormat(json);

        BufferedReader bufReader = new BufferedReader( new StringReader( pretty ));

        String tweetLine = null;
        String line = bufReader.readLine();
        while( (line != null)){
          Scanner tweetParts = new Scanner(line).useDelimiter("\\s*Date:\\s*|\\s*Coords:\\s*|\\s*User:\\s*|\\s*Text:\\s*|\\s*Hashtags:\\s*|\\s*URL:\\s*|\\s*Title:\\s*");
          String date = tweetParts.next();
          String coords = tweetParts.next();
          String user = tweetParts.next();
          String text = tweetParts.next();
          String hashtags = tweetParts.next();
          String link = tweetParts.next();
          String ptitle = tweetParts.next();
          String url = tweetParts.next();
          String title = tweetParts.next();
          Tweet tweet = new Tweet(date, coords, user, text, hashtags, url, title);
          addTweet(tweet);
        }
    }

public static void addTweet(Tweet tweet) {
  IndexWriter index = null;
  try {
    IndexWriterConfig indexConfig = new IndexWriterConfig(Version.LUCENE_34, new StandardAnalyzer( Version.LUCENE_35));
    index = new IndexWriter(FSDirectory.open(File("index")), indexConfig);

    Document doc = new Document();

    doc.add(new Field("date", tweet.date, Field.Store.YES, Field.Index.NO));
    doc.add(new Field("user", tweet.user, Field.Store.YES, Field.Index.NO));
    doc.add(new Field("coords", tweet.coords, Field.Store.YES, Field.Index.NO));
    doc.add(new Field("text", tweet.text, Field.Store.YES, Field.Index.ANALYZED));
    doc.add(new Field("hashtags", tweet.hashtags, Field.Store.YES, Field.Index.ANALYZED));
    doc.add(new Field("link", tweet.link, Field.Store.YES, Field.Index.NO));
    doc.add(new Field("ptitle", tweet.ptitle, Field.Store.YES, Field.Index.ANALYZED));
    index.addDocument(doc);
  } catch (Exception e) {
    e.printStackTrace();
  }
}

public static String[] search(String queryStr, int k) {
  IndexReader reader = IndexReader.open(FSDirectory.open(new File("index")));
  IndexSearcher searcher = new IndexSearcher(reader);
  QueryParser parser = new QueryParser(Version.LUCENE_34, "text", new StandardAnalyzer(Version.LUCENE_35));

  try {
    StringTokenizer tokenizer = new StringTokenizer(queryStr, " ~`!@#$%^&*()_-+={[}]|:;'<>,./?\"\'\/\n\t\b\r\f");

    String parseable = "";
    while ( tokenizer.hasMoreElements() ) {
      String token = tokenizer.nextToken();
      parseable += "text:" + token + "^1" + "hashtags:" + token + "^1.5" + "ptitle:" + token + "^2.0";
    }

    Query query = parser.parse(parseable);

    TopDocs result = searcher.search(query, k);

    String[] tweets = new String[result.scoreDocs.length]
    for (int i = 0; i < result.scoreDocs.length; i++) {
      String tweet = "@" + searcher.doc(result.scoreDocs[i].doc).getFieldable("user").stringValue();
      String date = searcher.doc(result.scoreDocs[i].doc).getFieldable("date").stringValue();
      tweet += ": " + searcher.doc(result.scoreDocs[i].doc).getFieldable("text").stringValue();
      tweet += "<br/>" + date + " score: " + result.scoreDocs[i].score);
      tweets[i] = tweets
    }

    return tweets

  } catch (Exception e) {
    e.printStackTrace()
  }

}
}
