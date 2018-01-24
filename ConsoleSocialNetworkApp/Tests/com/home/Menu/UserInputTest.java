package com.home.Menu;


import com.home.Model.Post;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserInputTest {
    private UserInput ui;
    private String input;

    @Before
    public void setUp(){
        ui = new UserInput();
        input = "Alex -> having fun";
    }

    @Test
    public void getUserFromSingleWordInput(){
        String word = "Alex";
        assertEquals("Alex",ui.getUserFromSingleWord(word));

    }

    @Test
    public void getUserFromInput(){
        assertEquals("Alex", ui.getUserFromInput(input));
    }


    @Test
    public void getMessageFromInput(){

        assertEquals("having fun", ui.getMessageFromInput(input));
    }

    @Test
    public void createPostFromInput(){
        Post post = ui.createPostFromInput(input);
        assertEquals("Alex - having fun", post.printPost());
    }

    @Test
    public void getSecondUserFromIntpu(){
        String input = "Alex bla bla asdasd Adele";
        assertEquals("Adele", ui.getTargetUserFromInput(input));
    }
}