package org.boes.praktikum.gameclient;

import java.util.Scanner;

public class InputHandler {

    public boolean startGame(String input){
        return input.equals("1");
    }

    public String isBetValid(String input){

        try {
            if(Integer.parseInt(input) < 1){
                return "Your bet is too low!";
            } else if(Integer.parseInt(input)> 20){
                return "Your bet is too high!";
            } else {
                return "valid";
            }
        }
        catch(NumberFormatException e) {
            return "Invalid Input";
        }

    }

    public boolean hit(Scanner userInput){
        while(true){
            System.out.println("Do you want to draw another card (hit) or do you want to stand?");
            System.out.println("1: Hit");
            System.out.println("2: Stand");
            String input = userInput.nextLine();
            if(input.equals("1")){
                return true;
            } else if(input.equals("2")){
                return false;
            }
            System.out.println("Invalid Input! Please try again!");
            System.out.println();
        }

    }

    public int playernumber(Scanner userInput){
        int player;

        System.out.println("Please specify your player number:");
        String input = userInput.nextLine();

        player = Integer.parseInt(input);
        return player;
    }
}
