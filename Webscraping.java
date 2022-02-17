package controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Objects;

public class Webscraping {


    public  Webscraping() {

    }

    /**
     * Just a fun feature to give the user a motivational quote each day when they start the application.
     * This also fills a "Pane" of "TextArea"
     * @return
     */
    public String fillMainPageWithQuotes() {
        String str = null;

        try {
            Document doc = Jsoup.connect("https://www.azquotes.com/").userAgent("Mozilla/17.0").get();
            Elements element = doc.select("div.slide");
            int i = 0;
            System.out.println("lol");
            for(Element quotesList : element){
                System.out.println(i + Objects.requireNonNull(quotesList.getElementsByTag("a").first()).text());
                str = i + (Objects.requireNonNull(quotesList.getElementsByTag("a").first()).text());
                i++;
                System.out.println("lol");
                //quoteOfTheDay.setText(str);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     *  Filling a "Pane" of "TextArea" with commonly used websites, to make it easier for users to get inspiration and possibly remember what to block.
     *
     * tagName: "a" is the Anchor in html lingo.
     * div.slide: its the class called slide that we are selecting with the help of Jsoup library.
     *
     * @return String
     */
    public String fillMainPageWithWebsites() {
        String str = null;

        try {
            Document doc = Jsoup.connect("https://www.azquotes.com/").userAgent("Mozilla/17.0").get();
            Elements element = doc.select("div.slide");
            int i = 0;

            for(Element websiteList : element){
                System.out.println(i + Objects.requireNonNull(websiteList.getElementsByTag("a").first()).text());
                str = i + (Objects.requireNonNull(websiteList.getElementsByTag("a").first()).text());
                i++;

                //quoteOfTheDay.setText(str);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
