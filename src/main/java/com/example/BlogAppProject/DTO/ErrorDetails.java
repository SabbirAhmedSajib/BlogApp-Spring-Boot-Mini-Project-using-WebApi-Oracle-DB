package com.example.BlogAppProject.DTO;

import java.util.Date;

public class ErrorDetails {

    private Date timeStamp;
    private String message;
    private String Details;

    public ErrorDetails(Date timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        Details = details;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return Details;
    }
}
