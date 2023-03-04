package com.wmp.utils;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class ParseUtil {

    /**
     * HTML 태그 제거
     * @param content
     * @return
     */
    public static String removeHtmlTag(String content) {
        return Jsoup.clean(content, Safelist.none());
    }

    /**
     * 알파벳,숫자만 남기고 제거
     * @param content
     * @return
     */
    public static String remainingAlphabetAndNumber(String content) {
        return content.replaceAll("[^a-zA-Z0-9]", "");
    }

    /**
     * 숫자 제거
     * @param content
     * @return
     */
    public static String removeNumber(String content) {
        return content.replaceAll("[^a-zA-Z]", "");
    }

    /**
     * 알파벳 제거
     * @param content
     * @return
     */
    public static String removeAlphabet(String content) {
        return content.replaceAll("[^0-9]", "");
    }

}
