package com.wmp.controller;

import com.wmp.component.CrawlingComponent;
import com.wmp.controller.paramobject.ContentPo;
import com.wmp.controller.responsemodel.ContentRm;
import com.wmp.controller.utils.ParseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ContentController {

    private final CrawlingComponent crawlingComponent;

    @GetMapping("/content")
    public ModelAndView content(ModelAndView mv) {
        mv.setViewName("content");
        return mv;
    }

    @PostMapping("/content")
    public ContentRm getContent(ContentPo contentPo,ModelAndView mv) throws IOException {

        String content = crawlingComponent.getUrlContent(contentPo.getUrl());
        if(contentPo.getType().equals("html")){
            content = ParseUtil.removeHtmlTag(content);
        }
        content = ParseUtil.remainingAlphabetAndNumber(content);

        String orderContent = Arrays.stream(content.split(""))
                .sorted((o1, o2) -> {
                    int res = o1.compareToIgnoreCase(o2);
                    return (res == 0) ? o1.compareTo(o2) : res;
                })
                .collect(Collectors.joining());

        String numbers = ParseUtil.removeAlphaber(orderContent);
        String characters = ParseUtil.removeNumber(orderContent);

        StringBuilder stringBuilder = new StringBuilder();
        int minLength = Math.min(numbers.length(),characters.length());

        String subNumbers = numbers.substring(minLength);
        String subCharacters = characters.substring(minLength);

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<minLength;i++){
            sb.append(characters.charAt(i));
            sb.append(numbers.charAt(i));
        }
        sb.append(subNumbers);
        sb.append(subCharacters);

        String combineText = sb.toString();
        int totalLength = combineText.length();
        String quote = null;
        String remainder = null;
        int dividedSize = totalLength % contentPo.getOperand();
        if(dividedSize ==0){
            quote = combineText;
        }else{
            quote = combineText.substring(0,totalLength-dividedSize);
            remainder = combineText.substring(totalLength-dividedSize, totalLength);
        }
        System.out.println(quote);
        System.out.println(remainder);
        return null;
    }
}