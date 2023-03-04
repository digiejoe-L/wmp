package com.wmp.component;

import com.wmp.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@Slf4j
class CrawlingComponentTest {

    @InjectMocks
    private CrawlingComponent crawlingComponent;

    @Test
    @DisplayName("HTML 내용 획득 성공")
    public void successGetUrlContent() {
        String urlContent = crawlingComponent.getUrlContent("https://www.naver.com/");
        assertNotNull(urlContent);
    }

    @Test
    @DisplayName("HTML 내용 획득 실패")
    public void failGetUrlContent() {
        CommonException commonException = assertThrows(CommonException.class,()->crawlingComponent.getUrlContent("https://111eeqwewqe.com/"));
        assertEquals(commonException.getMessage(),"URL에 문제가 있습니다.");
    }

}