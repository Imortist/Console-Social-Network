package com.home;

import com.home.Menu.Menu;

import java.time.LocalDateTime;


public class Main {

    public static void main(String[] args) {
        System.out.println("Console commands:");
        System.out.println("1.Create message: <user name> -> <message>");
        System.out.println("2.Print all messages by user: <user name>");
        System.out.println("3.Follow another user: <user name> follows <user name>");
        System.out.println("4.Print user messages and followed user messages: <user name> wall");
        System.out.println("5.Exit the program: quit");
        LocalDateTime machineTime = LocalDateTime.now();
        Menu menu = new Menu(machineTime);
        menu.run();
    }
}
