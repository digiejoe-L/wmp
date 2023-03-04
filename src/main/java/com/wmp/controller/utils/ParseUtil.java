package com.wmp.controller.utils;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class ParseUtil {


    public static String removeHtmlTag(String content){
        return Jsoup.clean(content, Safelist.none());
    }

    public static String remainingAlphabetAndNumber(String content){
        return content.replaceAll("[^a-zA-Z0-9]", "");
    }

    public static String removeNumber(String content){
        return content.replaceAll("[^a-zA-Z]", "");
    }

    public static String removeAlphaber(String content) {
        return content.replaceAll("[^0-9]", "");
    }

}
