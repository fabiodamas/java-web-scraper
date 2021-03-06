package com.codetriage.scraper;

import java.io.IOException;
import java.util.ArrayList;

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

// Fetch url with proxy
      ArrayList<Document> docs = new ArrayList();

      int cont = 1;
      int contUrl = 0;
        for (int i = 0; i <= 100; i = i + 25) {
            try {

                String paginacao;


                if (i==0){
                    paginacao="";
                }else{
                    paginacao= "&start=" + i;
                }

                String url="https://www.linkedin.com/jobs/search/?distance=10&geoId=104746682&keywords=java&location=S%C3%A3o%20Paulo%2C%20S%C3%A3o%20Paulo%2C%20Brasil" + paginacao;

                docs.add(Jsoup //
                        .connect(url)
                        .proxy("10.10.96.179", 3128) // sets a HTTP proxy
                        .userAgent("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2") //
                        .header("Content-Language", "pt-BR") //
                        .get() );

                System.out.println(url);
                System.out.println(docs.get(contUrl).location());

                // jobs-search-results__list artdeco-list
                Elements repositories = docs.get(contUrl).getElementsByClass("result-card job-result-card result-card--with-hover-state");

                for (Element repository : repositories) {
                    // Extract the title
                    // String repositoryTitle = repository.getElementsByClass("silk-icon-32").text();
                    String repositoryTitle = repository.getElementsByClass("result-card__title job-result-card__title").text();
                    String skill = repository.getElementsByClass("job-result-card__snippet").text();


                    System.out.println("vaga" + cont++ + ": " + repositoryTitle);
                    System.out.println("skill: " + skill);

                    System.out.println("\n");

                }


                contUrl++;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
