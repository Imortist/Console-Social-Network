package com.home.Model;

import com.home.Menu.UserInput;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class WallTest {
    private Wall wall;
    private Post post, post1, post2;
    private UserInput ui;
    private String input;

    @Before
    public void setUp() {
        input = "Aaron -> is tired";
        ui = new UserInput();
        wall = new Wall();
        post = new Post("Alex", "Hello World!");
        post1 = new Post("Alex", "working hard");
        post2 = new Post("Adele", "smoking again");
        wall.addPost(post);
        wall.addPost(post1);
        wall.addPost(post2);
    }



    @Test
    public void addDynamicPost(){
        Post dynamicPost = ui.createPostFromInput(input);
        wall.addPost(dynamicPost);
        String aaronsPost = wall.getPostsByUser("Aaron").get(0).getMessage();
        assertNotNull(wall.getPostsByUser("Aaron"));
        assertEquals("is tired", aaronsPost);

    }


    @Test
    public void addPostAddsPostsToTheWall(){
        assertEquals(true, wall.getWall().contains(post));
        assertEquals(true, wall.getWall().contains(post1));
        assertEquals(true, wall.getWall().contains(post2));
    }



    @Test
    public void multiplePostsCanBeLinkedToAuthors(){
        int postsNumber = wall.getPostsByUser("Alex").size();
        assertEquals(2, postsNumber);
    }

    @Test
    public void getPostsByUserReturnsListOfPosts(){
        assertEquals(2, wall.getPostsByUser("Alex").size());
        assertEquals("smoking again", wall.getPostsByUser("Adele").get(0).getMessage());
    }

    @Test
    public void followAddsUserToList(){
        wall.follow("Adele", "Alex");
        assertEquals("Alex", wall.getFollowedByUser("Adele").get(0));
    }

    @Test
    public void followedContainsMultipleFollowedForUser(){
        wall.follow("Adele", "Alex");
        wall.follow("Adele", "Aaron");
        assertEquals(2, wall.getFollowedByUser("Adele").size());
    }
}