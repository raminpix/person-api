package com.person.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record JSONResponse(int code, String reason, String message, String resourceUri,
                           Object data) implements Serializable {

    private static final long serialVersionUID = -4337989365139548619L;

}
