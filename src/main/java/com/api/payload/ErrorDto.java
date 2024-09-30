package com.api.payload;

import java.util.Date;

public class ErrorDto {

    String msg;
    Date date;
    String uri;

    public ErrorDto(String msg, String uri, Date date) {
        this.msg = msg;
        this.uri = uri;
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public Date getDate() {
        return date;
    }

    public String getUri() {
        return uri;
    }
}
