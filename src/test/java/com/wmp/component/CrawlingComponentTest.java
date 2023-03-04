package com.wmp.component;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
@Slf4j
class CrawlingComponentTest {

    @InjectMocks
    private CrawlingComponent crawlingComponent;

    @Test
    @DisplayName("URL HTML CONTENT 내용 테스트")
    public void getUrlContent() throws IOException {
        String urlContent = crawlingComponent.getUrlContent("https://www.naver.com/");
//        log.debug(urlContent);

        Safelist whitelist = Safelist.none();
        String cleanStr = Jsoup.clean(urlContent, whitelist);
        log.debug(cleanStr);
    }

}