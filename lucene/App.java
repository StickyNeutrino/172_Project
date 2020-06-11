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
    public static void main( String[] args )
    {

        File file = new File("tweets.json");

    }
}

public static void addTweet(Tweet tweet) {
  File indexFile = new File("index");
  IndexWriter index = null;
  try {
    IndexWriterConfig indexConfig = new IndexWriterConfig(Version.LUCENE_34, new StandardAnalyzer( Version.LUCENE_35))
    index = new IndexWriter(FSDirectory.open(indexFile), indexConfig)

    Document doc = new Document();

    doc.add(new Field("date", tweet.date, Field.Store.YES, Field.Index.));
    doc.add(new Field("user", tweet.user, Field.Store.YES, Field.Index.));
    doc.add(new Field("coords", tweet.coords, Field.Store.YES, Field.Index.));
    doc.add(new Field("text", tweet.text, Field.Store.YES, Field.Index.));
    doc.add(new Field("hashtags", tweet.hashtags, Field.Store.YES, Field.Index.));
    doc.add(new Field("link", tweet.link, Field.Store.YES, Field.Index.));
    doc.add(new Field("ptitle", tweet.ptitle, Field.Store.YES, Field.Index.));
    writer.addDocument(doc);
  } catch (Exception e) {
    e.printStackTrace();
  }
}
