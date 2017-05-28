package com.fast87.amugona.commons;

import lombok.Data;

import java.util.List;

/**
 * Created by jojonari on 2017. 5. 28..
 */
@Data
public class ErrorResponse {

    private String message;
    private String code;
    private List<FieldError> errors;

    public static class FieldError{
        private String field;
        private String value;
        private String code;

    }

}
