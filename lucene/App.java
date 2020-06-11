import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.json.JSONObject;

//package TweetLucene;

class Tweet {
	/**
	 * Struct of tweet in JSON files created_at, location, screen_name, description,
	 * hashtags, url, pageTitle
	 */
	public String date, coords, user, text, hashtags, link, ptitle;

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

public class App {
	public static void main(String[] args) throws IOException, org.json.simple.parser.ParseException {
		BufferedReader b = null;
		File file = new File("CS171_DATA");

		System.out.println("Reading: " + file + "leggo");

		b = new BufferedReader(new FileReader(file));

//        BufferedReader bufReader = new BufferedReader( new StringReader( pretty ));

		String line = null;
		line = b.readLine();
		while ((line != null)) {
			System.out.println("HELLO");
//			Scanner tweetParts = new Scanner(line).useDelimiter(
//					"\\s*created_at:\\s*|\\s*location:\\s*|\\s*screen_name:\\s*|\\s*description:\\s*|\\s*hashtags:\\s*|\\s*url:\\s*|\\s*pageTitle:\\s*");
//			if (tweetParts == null) {
//				System.out.println("wat");
//			}
//			JSONObject obj = new JSONObject(line);
			Scanner tweetParts = new Scanner(line);
			tweetParts.useDelimiter("created_at:");
//			System.out.println("created_at " + tweetParts.next());
//			tweetParts.
			System.out.println(tweetParts.next());
//			String date = tweetParts.next();
//			String coords = tweetParts.next();
//			String user = tweetParts.next();
//			String text = tweetParts.next();
//			String hashtags = tweetParts.next();
//			String url = tweetParts.next();
//			String ptitle = tweetParts.next();
//			Tweet tweet = new Tweet(date, coords, user, text, hashtags, url, ptitle);
			/**
			 * Struct of tweet in JSON files created_at, location, screen_name, full_text,
			 * hashtags, url, pageTitle
			 */
//			Tweet DUMMY_TWEET = new Tweet("Sat May 09 01:50:31 +0000 2020", "Rowland Heights, CA", "RowlandRaiders",
//					"Senior Cyrille Mae Naces will be enlisting in the Navy - Thank you for your service! https://t.co/0u6TJnnfOl @rowlandhs @RowlandSchools #RowlandRaiders #WeAreRUSD https://t.co/HOkkEtNojw",
//					"", "https://t.co/yvGFGmAckf", "Rowland High School");
//			System.out.println(date + coords + user + text + hashtags + url + ptitle);
//			addTweet(tweet);
//			addTweet(DUMMY_TWEET);
//			System.out.println(DUMMY_TWEET.ptitle);
//	       	tweetParts.close();
		}
	}

	public static void addTweet(Tweet tweet) throws CorruptIndexException, IOException {
		IndexWriter index = null;
//  File f = new File("")
		try {
			IndexWriterConfig indexConfig = new IndexWriterConfig(Version.LUCENE_34,
					new StandardAnalyzer(Version.LUCENE_35));
			index = new IndexWriter(FSDirectory.open(new File("index")), indexConfig);
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
  } finally {
    if ( index != null ){
      index.close();
    }
  }
}

	public static String[] search(String queryStr, int k) throws CorruptIndexException, IOException {
		IndexReader reader = IndexReader.open(FSDirectory.open(new File("index")));
		IndexSearcher searcher = new IndexSearcher(reader);
		QueryParser parser = new QueryParser(Version.LUCENE_34, "text", new StandardAnalyzer(Version.LUCENE_35));

		try {
			StringTokenizer tokenizer = new StringTokenizer(queryStr,
					" ~`!@#$%^&*()_-+={[}]|:;'<>,./?\"\'\\/\n\t\b\f\r");

			String parseable = "";
			while (tokenizer.hasMoreElements()) {
				String token = tokenizer.nextToken();
				parseable += "text:" + token + "^1" + "hashtags:" + token + "^1.5" + "ptitle:" + token + "^2.0";
			}

			Query query = parser.parse(parseable);

			TopDocs result = searcher.search(query, k);

			String[] tweets = new String[result.scoreDocs.length];
			for (int i = 0; i < result.scoreDocs.length; i++) {
				String tweet = "@" + searcher.doc(result.scoreDocs[i].doc).getFieldable("user").stringValue();
				String date = searcher.doc(result.scoreDocs[i].doc).getFieldable("date").stringValue();
				tweet += ": " + searcher.doc(result.scoreDocs[i].doc).getFieldable("text").stringValue();
				tweet += "<br/>" + date + " score: " + result.scoreDocs[i].score;
				tweets[i] = tweet;
			}

			return tweets;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
