package com.example.marathon.exeption;


import com.example.marathon.constant.Enum.ResponseCode;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Object data;
    private int code;
    private String message;
    private String message_en;


    public BusinessException(ResponseCode responseCode) {
        this.code = responseCode.getErrorcode();
        this.message = responseCode.getMessage();
        this.message_en =responseCode.getMessage_en();
    }

    public BusinessException(String message, String message_en, int errorcode) {
        this.code = ResponseCode.BusinessException.getErrorcode();
        this.message = ResponseCode.BusinessException.getMessage();
        this.message_en = ResponseCode.BusinessException.getMessage_en();
        this.message = message;
        this.message_en = message_en;
        this.code = errorcode;
    }

    public BusinessException(String message, String message_en, int errorcode, Object data) {
        this.code = ResponseCode.BusinessException.getErrorcode();
        this.message = ResponseCode.BusinessException.getMessage();
        this.message_en = ResponseCode.BusinessException.getMessage_en();
        this.message = message;
        this.message_en = message_en;
        this.code = errorcode;
        this.data = data;
    }

    public BusinessException() {
        this.code = ResponseCode.BusinessException.getErrorcode();
        this.message = ResponseCode.BusinessException.getMessage();
        this.message_en = ResponseCode.BusinessException.getMessage_en();
    }

    public BusinessException(String message) {
        this.code = ResponseCode.BusinessException.getErrorcode();
        this.message = ResponseCode.BusinessException.getMessage();
        this.message_en = ResponseCode.BusinessException.getMessage_en();
        this.message = message;
        this.message_en = message;
    }

    public BusinessException(int code) {
        this.code = ResponseCode.BusinessException.getErrorcode();
        this.message = ResponseCode.BusinessException.getMessage();
        this.message_en = ResponseCode.BusinessException.getMessage_en();
        this.code = code;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return this.data;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getMessage_en() {
        return this.message_en;
    }

    public String toString() {
        return String.format("\"code\":%s,{\"message\":\"%s\",{\"message_en\":\"%s\"}", this.code, this.message, this.message_en);
    }
}
