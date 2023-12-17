package com.application.personal_financial.crosscutting.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorDetails {

    private static final long serialVersionUID = 1L;

    private Date timestamp;
    private String message;
    private String details;
}
