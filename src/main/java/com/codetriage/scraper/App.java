package com.codetriage.scraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Our scraper class
 */
public class App {

  /**
   * The main method of our class, which will also house the scraping
   * functionality.
   */
  public static void main(String[] args) {

    try {
      // Document doc = Jsoup.connect("https://www.linkedin.com/jobs/search/?keywords=java").get();

// Fetch url with proxy
      Document doc = Jsoup //
              .connect("https://www.linkedin.com/jobs/search/?distance=10&f_PP=104746682&geoId=104746682&keywords=java&location=S%C3%A3o%20Paulo%2C%20S%C3%A3o%20Paulo%2C%20Brasil") //
              .proxy("10.10.96.179", 3128) // sets a HTTP proxy
              .userAgent("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2") //
              .header("Content-Language", "pt-BR") //
              .get();

      System.out.printf("\nWebsite Title: %s\n\n", doc.title());

      // jobs-search-results__list artdeco-list
      Elements repositories = doc.getElementsByClass("result-card job-result-card result-card--with-hover-state" );

      int cont=1;
      for (Element repository : repositories) {
        // Extract the title
        // String repositoryTitle = repository.getElementsByClass("silk-icon-32").text();
        String repositoryTitle = repository.getElementsByClass("result-card__title job-result-card__title").text();
        String skill =  repository.getElementsByClass("job-result-card__snippet").text();


        System.out.println("vaga" + cont++ + ": " +   repositoryTitle );
        System.out.println("skill: " + skill );

        System.out.println("\n");

      }

    /**
     * Incase of any IO errors, we want the messages 
     * written to the console
     */
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
