package com.wmp.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParseUtilTest {

    @Test
    @DisplayName("HTML 태그 제거 테스트")
    public void removeHtmlTag(){
        String html = "<!doctype html>\n" +
                "<html> \n" +
                "    <head>\n" +
                "        <title>Example: 2-1</title>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>example 2-1</h1>\n" +
                "        <h2>html example</h2>\n" +
                "    </body>\n" +
                "</html>";

        String removeHtml = ParseUtil.removeHtmlTag(html);
        assertEquals("Example: 2-1 example 2-1 html example",removeHtml);
    }

    @Test
    @DisplayName("알파벳,숫자만 남기고 제거 테스트")
    public void remainingAlphabetAndNumber(){
        String content = "0123456789AaBb한글입니다~Zz";
        String removeContent = ParseUtil.remainingAlphabetAndNumber(content);
        assertEquals("0123456789AaBbZz",removeContent);
    }

    @Test
    @DisplayName("숫자 제거 테스트")
    public void removeNumber(){
        String content = "0123456789AaBb";
        String removeContent = ParseUtil.removeNumber(content);
        assertEquals("AaBb",removeContent);
    }

    @Test
    @DisplayName("알파벳 제거 테스트")
    public void removeAlphabet(){
        String content = "0123456789AaBb";
        String removeContent = ParseUtil.removeAlphabet(content);
        assertEquals("0123456789",removeContent);

    }
}