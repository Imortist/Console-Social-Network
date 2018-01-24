package com.home.Menu;


import com.home.Model.Post;
import com.home.Model.Wall;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ActionsTest {

    private Actions action;
    private Wall wall;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        LocalDateTime time = LocalDateTime.now();
        Post post0 = new Post("Alex", "Hello World!");
        Post post1 = new Post("Alex", "working hard");
        wall = new Wall();
        wall.addPost(post0);
        wall.addPost(post1);
        action = new Actions(wall, time);
    }

    @Test
    public void postingAddsPostToWall(){
        action.post("Adele -> smoking outside");
        assertEquals(3,  wall.getWall().size());
        assertNotNull(wall.getPostsByUser("Adele"));
    }

    @Test
    public void followAddsUsersToFollowed(){
        action.follow("Alex", "Adele");
        assertEquals("Adele", wall.getFollowedByUser("Alex").get(0));
    }

    @Test
    public void readingPrintsOneMessageByUser(){
        Post post = new Post("Adele","smoking");
        String expectedString = "Adele - smoking" + " (moments ago)";
        wall.addPost(post);
        action.read("Adele");
        assertEquals(expectedString, outContent.toString().trim());
    }

    @Test
    public void readingPrintsAllMessagesByUser() {

        action.read("Alex");
        assertEquals("Alex - Hello World! (moments ago)\n" +
                "Alex - working hard (moments ago)", outContent.toString().trim());
    }

    @Test
    public void wallPrintsUserMessagesAndFollowedUserMessages(){
        Post adelPost = new Post("Adele", "smoking");
        wall.addPost(adelPost);
        action.follow("Alex","Adele");
        action.wall("Alex");
        String actualOutput = outContent.toString().trim();
        assertEquals("Alex - Hello World! (moments ago)\n" +
                "Alex - working hard (moments ago)\n" +
                "Adele - smoking (moments ago)",actualOutput);
    }

    @Test
    public void dynamicPosting(){
        action.post("Aaron -> wtf");
        action.post("Many -> ftw");
        action.follow("Aaron", "Many");
        action.wall("Aaron");
        String actOutput = outContent.toString().trim();
        assertEquals("Aaron - wtf (moments ago)\nMany - ftw (moments ago)", actOutput);
    }


    @After
    public void restoreStreams(){
        System.setOut(System.out);
        System.setErr(System.err);
    }
}