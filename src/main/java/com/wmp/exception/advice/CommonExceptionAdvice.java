package com.wmp.exception.advice;

import com.wmp.exception.CommonException;
import com.wmp.exception.response.CommonErrorMsg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.security.InvalidParameterException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class CommonExceptionAdvice {

    @ExceptionHandler(CommonException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public CommonErrorMsg commonException(WebRequest req, CommonException ex) {
        String message = ex.getMessage();
        return new CommonErrorMsg(message);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CommonErrorMsg bindException(WebRequest req, BindException ex) {
        Map<String, String> errorFieldDetail = new HashMap<>();
        ex.getFieldErrors().forEach(error -> errorFieldDetail.put(error.getField(),  error.getDefaultMessage()));
        return new CommonErrorMsg("입력값이 올바르지 않습니다.", errorFieldDetail, Timestamp.valueOf(LocalDateTime.now()).toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CommonErrorMsg methodArgumentNotValidException(WebRequest req, MethodArgumentNotValidException ex) {
        Map<String, String> errorFieldDetail = new HashMap<>();
        ex.getFieldErrors().forEach(error -> errorFieldDetail.put(error.getField(),  error.getDefaultMessage()));
        return new CommonErrorMsg("입력값이 올바르지 않습니다.", errorFieldDetail, Timestamp.valueOf(LocalDateTime.now()).toString());
    }

    @ExceptionHandler(InvalidParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CommonErrorMsg invalidParameterException(WebRequest req, InvalidParameterException ex) {
        Map<String, String> errorFieldDetail = new HashMap<>();
        errorFieldDetail.put("ERROR", ex.getMessage());
        return new CommonErrorMsg("입력값이 올바르지 않습니다.", errorFieldDetail, Timestamp.valueOf(LocalDateTime.now()).toString());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CommonErrorMsg unknownException(WebRequest req, Exception ex) {
        return new CommonErrorMsg("예기치 못한 오류가 발생했습니다.");
    }
}
