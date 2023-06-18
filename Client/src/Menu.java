package org.boes.praktikum.gameclient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;


public class Menu {

    RequestBuilder reqBuilder = new RequestBuilder();
    AsciiArt ascii = new AsciiArt();
    InputHandler inputHandler = new InputHandler();

    SecureRandom random = new SecureRandom();

    String token = generateToken();

    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();


    // Generate a token for player identification
    public String generateToken(){
        String token;
        byte[] randomBytes = new byte[24];
        random.nextBytes(randomBytes);
        token = base64Encoder.encodeToString(randomBytes);
        return token;
    }

    public void mainMenuIdle(Scanner userInput, HttpClient client, ObjectMapper objectMapper) throws URISyntaxException, IOException, InterruptedException {

        while(true) {
            // Main-menu for user interaction
            // print the game's title
            System.out.println(ascii.title());

            System.out.println();
            System.out.println("Enter 1 to start a new game of BlackJack!");
            String input = userInput.nextLine();
            if(inputHandler.startGame(input)){
                // change to betMenu once server supports multi-client games
                betMenu(client,objectMapper,userInput);
            }
        }
    }


    public void betMenuOneClient(HttpClient client, ObjectMapper objectMapper,Scanner userInput) throws URISyntaxException, IOException, InterruptedException {
        System.out.println("Player One please place a bet between 2$ and 500$:");
        boolean bet = false;
        int bet1 = 0;
        int bet2 = 0;
        while(!bet){
            String input = userInput.nextLine();
            if(inputHandler.isBetValid(input).equals("valid")){
                bet = true;
                bet1 = Integer.parseInt(input);
            } else {
                System.out.println(inputHandler.isBetValid(input));
            }
        }
        bet = false;
        System.out.println("Player Two please place a bet between 2$ and 500$:");
        while(!bet){
            String input = userInput.nextLine();
            if(inputHandler.isBetValid(input).equals("valid")){
                bet = true;
                bet2 = Integer.parseInt(input);
            } else {
                System.out.println(inputHandler.isBetValid(input));
            }
        }

        Status status = reqBuilder.placeBetOneClient(client,objectMapper,bet1,bet2);
        hitMenuOneClient(client,objectMapper,userInput,status,false,false);
    }

    private void hitMenuOneClient(HttpClient client, ObjectMapper objectMapper, Scanner userInput, Status status, boolean stand1, boolean stand2) throws URISyntaxException, IOException, InterruptedException {
        System.out.println(ascii.round1());
        String dealerHand = parseCards(status,1);
        String player1Hand = parseCards(status,2);
        String player2Hand = parseCards(status,3);
        System.out.println("Dealers Hand:");
        System.out.println(dealerHand);
        System.out.println("Player1's Hand:");
        System.out.println(player1Hand);
        System.out.println("Player1's Value:");
        System.out.println(status.p1sum);


        System.out.println();
        System.out.println("Player2's Hand:");
        System.out.println(player2Hand);
        System.out.println("Player2's Value:");
        System.out.println(status.p2sum);

        System.out.println();
        Status newest = null;
        if(!stand1){
            System.out.println("Player One:");
            boolean hit = inputHandler.hit(userInput);
            System.out.println();
            if(hit){
                Status player1hit = reqBuilder.hit(client,objectMapper,1);
                newest = player1hit;
                if(player1hit.p1lost){
                    player1hit = reqBuilder.stand(client,objectMapper,1);
                    newest = player1hit;
                    stand1 = true;
                }
            } else{
                Status player1hit = reqBuilder.stand(client,objectMapper,1);
                newest = player1hit;
                stand1 = true;
            }
        }

        if(!stand2){
            System.out.println("Player Two:");
            boolean hit2 = inputHandler.hit(userInput);
            System.out.println();
            if(hit2){
                Status player2hit = reqBuilder.hit(client,objectMapper,2);
                newest = player2hit;
                if(player2hit.p2lost){
                    player2hit = reqBuilder.stand(client,objectMapper,2);
                    stand2 = true;
                    newest = player2hit;
                }
            } else{
                Status player2hit = reqBuilder.stand(client,objectMapper,2);
                newest = player2hit;
                stand2 = true;
            }
        }

        if(stand1 && stand2){
            newest = reqBuilder.status(client, objectMapper);
            endMenu(client,objectMapper,userInput,newest,1);
            return;
        }

        hitMenuOneClient(client,objectMapper,userInput,newest,stand1,stand2);
    }

    public void endMenu(HttpClient client, ObjectMapper objectMapper, Scanner userInput, Status status, int player) throws URISyntaxException, IOException, InterruptedException {
        int count = 0;
        System.out.println("Waiting for other players...");
        while((count < 50) && !(status.p2equal||status.p2lost || status.p2won) || !(status.p1equal||status.p1lost || status.p1won)){
            status = reqBuilder.stand(client,objectMapper,player);
            count++;
            sleep(2000);
        }
        if(count >= 50){
            throw new RuntimeException();
        }

        System.out.println("Game has ended!");
        System.out.println("Final Hands:");
        Status finalHands = reqBuilder.stand(client,objectMapper,player);
        System.out.println(ascii.end());
        String dealerHand = parseCards(finalHands,1);
        String player1Hand = parseCards(finalHands,2);
        String player2Hand = parseCards(finalHands,3);
        System.out.println("Dealer's Hand:");
        System.out.println(dealerHand);
        System.out.println("Dealer's Value:");
        System.out.println(status.dealersum);
        if(player == 1){
            System.out.println("Your Hand:");
            System.out.println(player1Hand);
            System.out.println("Your Value:");
            System.out.println(status.p1sum);
        } else {
            System.out.println("Player1's Hand:");
            System.out.println(player1Hand);
            System.out.println("Player1's Value:");
            System.out.println(status.p1sum);
        }

        if(finalHands.p1won){
            System.out.println("Congratulations Player One on winning!");
            System.out.println();
        } else if(finalHands.p1lost){
            System.out.println("Player One lost this round :(");
            System.out.println("Better Luck next Time!");
            System.out.println();
        } else {
            System.out.println("It's a draw for Player One!");
            System.out.println("Better Luck next Time!");
            System.out.println();
        }


        System.out.println();
        if(player == 2){
            System.out.println("Your Hand:");
            System.out.println(player2Hand);
            System.out.println("Your Value:");
            System.out.println(status.p2sum);
        } else {
            System.out.println("Player2's Hand:");
            System.out.println(player2Hand);
            System.out.println("Player2's Value:");
            System.out.println(status.p2sum);
        }

        if(finalHands.p2won){
            System.out.println("Congratulations Player Two on winning!");
            System.out.println();
        } else if(finalHands.p2lost){
            System.out.println("Player Two lost this round :(");
            System.out.println("Better Luck next Time!");
            System.out.println();
        } else {
            System.out.println("It's a draw for Player Two!");
            System.out.println("Better Luck next Time!");
            System.out.println();
        }

        finalHands = reqBuilder.status(client,objectMapper);
        System.out.println("Your new account balance is:");
        if(player == 1){
            System.out.println(finalHands.p1guthaben + "$");
        } else {
            System.out.println(finalHands.p2guthaben + "$");
        }
        System.out.println();


    }


    private String parseCards(Status status, int party){
        String output = "";
        int count = 0;
        int index = 0;
        switch(party){
            case 1:
                String[] dealerArray = status.Dealer.split(" ");
                for(int i = 0;i< dealerArray.length;i++){
                    if(!dealerArray[i].equals("dealer's") && !dealerArray[i].equals("cards:") && !dealerArray[i].equals("") && !dealerArray[i].equals("Cards") && !dealerArray[i].equals("Dealer's")){
                        count++;
                    }
                }
                String[] cards = new String[count];
                for(int i = 0;i< dealerArray.length;i++){
                    if(!dealerArray[i].equals("dealer's") && !dealerArray[i].equals("cards:") && !dealerArray[i].equals("") && !dealerArray[i].equals("Cards") && !dealerArray[i].equals("Dealer's")){
                        String[] tmp = dealerArray[i].split("-");
                        cards[index] = ascii.card(tmp[0],tmp[1]) + "\n";
                        index++;
                    }
                }
                index =0;
                for(int j =0; j< cards.length;j++){
                    output = output + cards[j];
                }
                break;
            case 2:
                String[] player1Array = status.player1.split(" ");
                count = 0;
                for(int i = 0;i< player1Array.length;i++){
                    if(!player1Array[i].equals("Player1's") && !player1Array[i].equals("cards:") && !player1Array[i].equals("")){
                        count++;
                    }
                }
                String[] cardsPlayer1 = new String[count];
                for(int i = 0;i< player1Array.length;i++){
                    if(!player1Array[i].equals("Player1's") && !player1Array[i].equals("cards:") && !player1Array[i].equals("")){
                        String[] tmp = player1Array[i].split("-");
                        cardsPlayer1[index] = ascii.card(tmp[0],tmp[1]) + "\n";
                        index++;
                    }
                }
                index = 0;
                for(int j =0; j< cardsPlayer1.length;j++){
                    output = output + cardsPlayer1[j];
                }
                break;
            case 3:
                String[] player2Array = status.player2.split(" ");
                count = 0;
                for(int i = 0;i< player2Array.length;i++){
                    if(!player2Array[i].equals("Player2's") && !player2Array[i].equals("cards:") && !player2Array[i].equals("")){
                        count++;
                    }
                }
                String[] cardsPlayer2 = new String[count];
                for(int i = 0;i< player2Array.length;i++){
                    if(!player2Array[i].equals("Player2's") && !player2Array[i].equals("cards:") && !player2Array[i].equals("")){
                        String[] tmp = player2Array[i].split("-");
                        cardsPlayer2[index] = ascii.card(tmp[0],tmp[1]) + "\n";
                        index++;
                    }
                }
                index = 0;
                for(int j =0; j< cardsPlayer2.length;j++){
                    output = output + cardsPlayer2[j];
                }
                break;
        }
        return output;
    }

    public void betMenu(HttpClient client, ObjectMapper objectMapper,Scanner userInput) throws URISyntaxException, IOException, InterruptedException {


        int player = inputHandler.playernumber(userInput);
        System.out.println("Please place a bet between 1$ and 20$:");
        boolean bet = false;
        int bet1 = 0;
        while(!bet){
            String input = userInput.nextLine();
            if(inputHandler.isBetValid(input).equals("valid")){
                bet = true;
                bet1 = Integer.parseInt(input);
            } else {
                System.out.println(inputHandler.isBetValid(input));
            }
        }

        Status status = reqBuilder.placeBet(client,objectMapper,bet1,player);
        System.out.println("Your Account Balance:");
        System.out.println(status.p1guthaben);
        hitMenu(client,objectMapper,userInput,status,false, player);
    }

    private void hitMenu(HttpClient client, ObjectMapper objectMapper, Scanner userInput, Status status,boolean stand1, int player) throws URISyntaxException, IOException, InterruptedException {
        System.out.println(ascii.round1());
        String dealerHand = parseCards(status,1);
        String player1Hand = parseCards(status,2);
        String player2Hand = parseCards(status,3);
        System.out.println("Dealers Hand:");
        System.out.println(dealerHand);
        System.out.println("Your Hand:");
        if(player == 1){
            System.out.println(player1Hand);
        } else {
            System.out.println(player2Hand);
        }

        System.out.println("Your Value:");
        if(player == 1){
            System.out.println(status.p1sum);
        } else {
            System.out.println(status.p2sum);
        }



        System.out.println();
        Status newest = null;
        if(!stand1){
            System.out.println("Choose your action:");
            boolean hit = inputHandler.hit(userInput);
            System.out.println();
            if(hit){
                Status player1hit = reqBuilder.hit(client,objectMapper,player);
                newest = player1hit;

                if(player == 1){
                    if(player1hit.p1sum > 21){
                        player1hit = reqBuilder.stand(client,objectMapper,player);
                        newest = player1hit;
                        stand1 = true;
                    }
                } else {
                    if(player1hit.p2sum > 21){
                        player1hit = reqBuilder.stand(client,objectMapper,player);
                        newest = player1hit;
                        stand1 = true;
                    }
                }

            } else{
                Status player1hit = reqBuilder.stand(client,objectMapper,player);
                newest = player1hit;
                stand1 = true;
            }
        }


        if(stand1){
            newest = reqBuilder.status(client, objectMapper);
            endMenu(client,objectMapper,userInput,newest,player);
            return;
        }

        hitMenu(client,objectMapper,userInput,newest,stand1, player);
    }


}
