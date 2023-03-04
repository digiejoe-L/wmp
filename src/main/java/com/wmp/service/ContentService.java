package com.wmp.service;

import com.wmp.component.CrawlingComponent;
import com.wmp.controller.paramobject.ContentPo;
import com.wmp.controller.responsemodel.ContentRm;
import com.wmp.enums.ParseType;
import com.wmp.utils.ParseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final CrawlingComponent crawlingComponent;

    /**
     * 출력 데이터 가공
     * @param contentPo
     * @return
     * @throws IOException
     */
    public ContentRm getContent( ContentPo contentPo) {
        String content = crawlingComponent.getUrlContent(contentPo.getUrl());
        content = getParseContent(contentPo.getType(), content);
        String orderContent = getOrderedContent(content);

        String numbers = ParseUtil.removeAlphabet(orderContent);
        String characters = ParseUtil.removeNumber(orderContent);

        String combineText = getCombineText(numbers, characters);
        ContentRm contentRm = getQuoteAndRemainder(contentPo.getOperand(), combineText);
        return contentRm;
    }

    /**
     * 몫,나누기를 계산하여 object로 return
     * @param operand
     * @param combineText
     * @return
     */
    private ContentRm getQuoteAndRemainder(int operand, String combineText) {
        int totalLength = combineText.length();
        String quote;
        String remainder = null;
        int dividedSize = totalLength % operand;
        if (dividedSize == 0) {
            quote = combineText;
        } else {
            quote = combineText.substring(0, totalLength - dividedSize);
            remainder = combineText.substring(totalLength - dividedSize, totalLength);
        }
        return ContentRm.builder()
                .quotient(quote)
                .remainder(remainder)
                .build();
    }

    /**
     * 조합된 Content return
     * 조합식 : A0a0... [영문,숫자,영문,숫자...]
     * @param numbers
     * @param characters
     * @return
     */
    private String getCombineText(String numbers, String characters) {
        int minLength = Math.min(numbers.length(), characters.length());
        String subNumbers = numbers.substring(minLength);
        String subCharacters = characters.substring(minLength);

        StringBuilder sb = new StringBuilder();
        IntStream.range(0,minLength).forEach(i->{
            sb.append(characters.charAt(i));
            sb.append(numbers.charAt(i));
        });
        sb.append(subNumbers);
        sb.append(subCharacters);

        String combineText = sb.toString();
        return combineText;
    }

    /**
     * 정렬된 상태의 Content return
     * 정렬기준 012..789AaBbCc...Zz
     * @param content
     * @return
     */
    private String getOrderedContent(String content) {
        String orderContent = Arrays.stream(content.split(""))
                .sorted((o1, o2) -> {
                    int res = o1.compareToIgnoreCase(o2);
                    return (res == 0) ? o1.compareTo(o2) : res;
                })
                .collect(Collectors.joining());
        return orderContent;
    }

    /**
     * HTML규격의 String을 파싱하여 return
     * 1.html제외 타입시 html태그 제거
     * 2.알파벳과 숫자만 남김
     * @param parseType
     * @param content
     * @return
     */
    private String getParseContent(ParseType parseType, String content) {
        if (parseType.equals(ParseType.html)) {
            content = ParseUtil.removeHtmlTag(content);
        }
        content = ParseUtil.remainingAlphabetAndNumber(content);
        return content;
    }

}
