package com.wmp.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ParseType {

    text("Text 전체"),
    html("HTML 태그 제외");

    private String value;

    ParseType(String value) {
        this.value = value;
    }

    public String getName() {
        return name();
    }

    public String getValue() {
        return value;
    }


    public static List<ParseType> getAll() {
        return Arrays.asList(ParseType.values()).stream().collect(Collectors.toList());
    }


}
