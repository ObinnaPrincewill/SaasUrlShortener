package com.binna.sassurlshortener.DTOs.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class ResponseWrapper <T> {
    private T date;
    private String meassage;
    private HttpStatusCode statusCode;
}
