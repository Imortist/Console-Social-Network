package com.home.Model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class PostTest {
    private Post message;


    @Before
    public void setUp(){
        message = new Post("alex", "hello");
    }

    @Test
    public void printMessageReturnsFormattedString(){
        String expected = message.getUser() + " - " + message.getMessage();
        assertEquals(expected, message.printPost());

    }
}