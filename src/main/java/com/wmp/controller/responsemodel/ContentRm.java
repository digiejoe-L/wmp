package com.wmp.controller.responsemodel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContentRm {

    /**
     * 몫
     */
    private String quotient;

    /**
     * 나머지
     */
    private String remainder;
}
