package com.wmp.component;

import com.wmp.exception.CommonException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CrawlingComponent {

    /**
     * Jsoup을 사용하여 url의 HTML 획득
     * *timeout 10초
     * @param url
     * @return
     * @throws IOException
     */
    public String getUrlContent(String url){
        Connection.Response response = null;
        Document document = null;
        try {
            response = Jsoup.connect(url)
                    .timeout(10000)
                    .execute();
            document = response.parse();
        } catch (IOException e) {
            throw new CommonException("URL에 문제가 있습니다.");
        }
        return document.html();
    }
}
