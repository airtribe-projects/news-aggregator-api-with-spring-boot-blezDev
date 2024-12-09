package com.blez.newsapi_springboot.exception;

import lombok.*;
import org.hibernate.validator.constraints.ConstraintComposition;

import java.util.Date;

@Data
@Getter
@Setter

@NoArgsConstructor

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;


    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }


}
