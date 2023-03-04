package com.wmp.component;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CrawlingComponent {

    public String getUrlContent(String url) throws IOException {
        Connection.Response response = Jsoup.connect(url)
                .execute();
        Document document = response.parse();
        return document.html();
    }
}
