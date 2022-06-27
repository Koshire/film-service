package com.andersenlab.backbasetesttask.config.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Getter
public class ErrorModel {

    public ErrorModel(String id, String message, String field) {
        this.id = id;
        this.message = message;
        this.field = field;
    }

    public ErrorModel(String message) {
        this.message = message;
    }

    public ErrorModel(String message, String field) {
        this.message = message;
        this.field = field;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String field;
}
