package com.home.Menu;

import com.home.Model.Wall;

import java.time.LocalDateTime;

import static java.lang.System.exit;

public class Menu {
    private UserInput ui;
    private Actions action;

    public Menu(LocalDateTime time){
        Wall wall = new Wall();
        action = new Actions(wall,time);
        ui = new UserInput();
    }

    public void run(){
        String input;
        do {
            input = ui.getInput();
            if (input.contains("->")) {
                action.post(input);
            }
            if (!input.contains("\n")) {
                String user = ui.getUserFromSingleWord(input);
                action.read(user);
            }
            if (input.contains("follows")) {
                String user = ui.getUserFromInput(input);
                String targetUser = ui.getTargetUserFromInput(input);
                action.follow(user, targetUser);
            }
            if (input.contains("wall")) {
                String user = ui.getUserFromInput(input);
                action.wall(user);
            }
        } while(!input.equals("quit"));

        exit(1);
    }
}
