package com.wmp.exception.response;

import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class CommonErrorMsg {
    private String errorMsg;
    private Map<String, String> errorFieldDetail;
    private String timestamp;

    /**
     * @param errorMsg
     */
    public CommonErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        timestamp = Timestamp.valueOf(LocalDateTime.now()).toString();
    }

    /**
     * @param errorMsg
     * @param timestamp
     */
    public CommonErrorMsg(String errorMsg, String timestamp) {
        this.errorMsg = errorMsg;
        this.timestamp = timestamp;
    }

    /**
     * @param errorMsg
     * @param errorFieldDetail
     * @param timestamp
     */
    public CommonErrorMsg(String errorMsg, Map<String, String> errorFieldDetail, String timestamp) {
        this.errorMsg = errorMsg;
        this.errorFieldDetail = errorFieldDetail;
        this.timestamp = timestamp;
    }
}
