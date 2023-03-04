package com.wmp.service;

import com.wmp.component.CrawlingComponent;
import com.wmp.controller.paramobject.ContentPo;
import com.wmp.controller.responsemodel.ContentRm;
import com.wmp.enums.ParseType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class ContentServiceTest {

    @InjectMocks
    ContentService contentService;

    @Mock
    private CrawlingComponent crawlingComponent;

    @DisplayName("출력 데이터 가공 테스트")
    @Test
    void getContent() {
        //given
        String content = "cbaCBA한글12312312";
        when(crawlingComponent.getUrlContent(any())).thenReturn(content);
        ContentPo contentPo = ContentPo.builder()
                .url("")
                .operand(3)
                .type(ParseType.text).build();
        //when
        ContentRm contentRm = contentService.getContent(contentPo);
        //then
        assertAll(
                () -> assertEquals(contentRm.getQuotient(), "A1a1B1b2C2c2"),
                () -> assertEquals(contentRm.getRemainder(), "33")
        );


    }

}