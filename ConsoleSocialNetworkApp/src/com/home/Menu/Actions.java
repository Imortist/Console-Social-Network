package com.home.Menu;

import com.home.Model.Post;
import com.home.Model.TimeAgo;
import com.home.Model.Wall;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Actions {
    private UserInput ui;
    private Wall wall;
    private LocalDateTime machineTime;

    Actions(Wall wall, LocalDateTime time){
        machineTime = time;
        ui = new UserInput();
        this.wall = wall;
    }

    private Date getMachineDate(){
        Instant instantOfMachineTime = machineTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instantOfMachineTime);
    }

    private Date getPostCreationDate(Post post){
       return post.getCreationTimeInDate();
    }

    public void read(String user){
        List<Post> userPosts =  wall.getPostsByUser(user);
        for(Post post : userPosts){
            String timeStamp = TimeAgo.toRelative(getPostCreationDate(post), getMachineDate());
            System.out.printf("%s (%s)\n", post.printPost(), timeStamp);
        }
    }

    public void post(String input){
        Post postFromInput = ui.createPostFromInput(input);
        wall.addPost(postFromInput);
    }


    public void wall(String user) {
        List<Post> focusedPosts = wall.getPostsByUser(user);
        if(wall.getFollowedByUser(user) != null) {
            List<String> followedUsers = wall.getFollowedByUser(user);
            followedUsers.stream().map(followedUser ->
                    wall.getPostsByUser(followedUser)).forEach(focusedPosts::addAll);
        }
        for(Post focusedPost : focusedPosts){
            String timeStamp = TimeAgo.toRelative(getPostCreationDate(focusedPost), getMachineDate());
            System.out.printf("%s (%s)\n", focusedPost.printPost(), timeStamp);
        }
    }


    public void follow(String user,String targetUser){
        wall.follow(user,targetUser);
    }
}
