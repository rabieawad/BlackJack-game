package org.boes.praktikum.gameclient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        final var client = HttpClient.newBuilder()
                .build();


        Scanner userInput = new Scanner(System.in);
        var objectMapper = new ObjectMapper();

        // new Menu
        Menu menu = new Menu();
        menu.mainMenuIdle(userInput,client,objectMapper);

    }

}
