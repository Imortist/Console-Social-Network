package com.home.Model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wall {
    private  List<Post> wall = new ArrayList<>();
    private Map<String, List<String>> followed = new HashMap<>();


    public void addPost(Post post) {
        wall.add(post);
    }

    public List<Post> getWall() {
        return wall;
    }

    public List<Post> getPostsByUser(String user) {
        List<Post> userPosts = new ArrayList<>();
        for(Post post : wall){
            if(post.getUser().equals(user)){
                userPosts.add(post);
            }
        }
        return userPosts;
    }

    public void follow(String user, String targetUser) {
        List<String> followedUsers;
        followedUsers = followed.computeIfAbsent(user, k -> new ArrayList<>());
        followedUsers.add(targetUser);
        followed.put(user, followedUsers);
    }

    public List<String> getFollowedByUser(String user){
        return followed.get(user);
    }

}




