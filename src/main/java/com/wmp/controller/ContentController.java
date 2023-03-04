package com.wmp.controller;

import com.wmp.controller.paramobject.ContentPo;
import com.wmp.controller.responsemodel.ContentRm;
import com.wmp.enums.ParseType;
import com.wmp.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    /**
     * index 페이지
     * @param mv
     * @return
     */
    @GetMapping("/")
    public ModelAndView content(ModelAndView mv) {
        mv.setViewName("content");
        mv.addObject("parseType", ParseType.getAll());
        return mv;
    }

    /**
     * 출력 데이터 전송
     * @param contentPo [Json]
     * @return Json
     * @throws IOException
     */
    @PostMapping(value = "/content")
    @ResponseBody
    public ContentRm getContent(@Valid  @RequestBody ContentPo contentPo){
       return contentService.getContent(contentPo);
    }
}