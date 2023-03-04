package com.wmp.controller.responsemodel;

import lombok.Data;

import java.util.List;

@Data
public class ContentRm {

    private List<String> quotient;
    private String remainder;
}
