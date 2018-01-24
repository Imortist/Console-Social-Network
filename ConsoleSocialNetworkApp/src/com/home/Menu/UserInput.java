package com.home.Menu;

import com.home.Model.Post;

import java.time.LocalDateTime;
import java.util.Scanner;


public class UserInput {

    private Scanner scanner = new Scanner(System.in);
    private LocalDateTime time = LocalDateTime.now();


    public String getInput(){
        return scanner.nextLine();
    }

    public String getUserFromInput(String input) {
        int endOfFirstWord = input.indexOf(" ");
        return input.substring(0,endOfFirstWord);

    }

    public String getUserFromSingleWord(String input){
        return input;
    }

    public String getTargetUserFromInput(String input){
        return input.substring(input.lastIndexOf(" ")+1);
    }


    public String getMessageFromInput(String input) {
        int messageStartPoint = input.indexOf("->")+2;
        return input.substring(messageStartPoint, input.length()).trim();

    }

    public Post createPostFromInput(String input){
        String user = getUserFromInput(input);
        String message = getMessageFromInput(input);
        return new Post(user,message);
    }


}


