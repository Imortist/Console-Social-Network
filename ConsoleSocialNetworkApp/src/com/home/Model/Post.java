package com.home.Model;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Post {
    private String user;
    private String message;
    private LocalDateTime creationTime;

    public Post(String user, String message){
        this.user = user;
        this.message = message;
        creationTime = LocalDateTime.now();
    }


    public Date getCreationTimeInDate(){
        Instant currentTime = creationTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(currentTime);

    }

    public String getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }


    public String printPost() {
        return getUser() + " - " + getMessage();
    }
}
