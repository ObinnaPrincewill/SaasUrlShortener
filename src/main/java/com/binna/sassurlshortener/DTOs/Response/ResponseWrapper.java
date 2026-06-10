package com.binna.sassurlshortener.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Builder
@Data
@ToString
@AllArgsConstructor
public class ResponseWrapper <T> {
    private T data;
    private String message;
    private HttpStatusCode statusCode;
}
