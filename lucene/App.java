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

        System.out.println("hi");
    }
}
