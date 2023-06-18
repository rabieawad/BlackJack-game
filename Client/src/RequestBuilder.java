package org.boes.praktikum.gameclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static java.lang.Thread.sleep;

@Data
@AllArgsConstructor
public class RequestBuilder {

    // Method to request a new game to be started with two players playing from the same client (until server implementation allows multi client games)

    public Status placeBetOneClient(HttpClient client, ObjectMapper objectMapper,int parseInt, int parseInt2) throws URISyntaxException, IOException, InterruptedException {
        StartGame startGame = new StartGame(parseInt,parseInt2);
        var startGameRequestOneClient = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:7070/start"))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(startGame)))
                .build();

        HttpResponse<String> responseStartGameOneClient = client.send(startGameRequestOneClient, HttpResponse.BodyHandlers.ofString());
        var startGameOneClientBody = responseStartGameOneClient.body();
        return objectMapper.readValue(startGameOneClientBody, Status.class);
    }

    public boolean askForStart(HttpClient client, ObjectMapper objectMapper, String name) throws InterruptedException, IOException, URISyntaxException {
        boolean has_game_started = false;
        boolean returned_value = false;
        AskForStartRequest ask = new AskForStartRequest(name);
        var startGameRequestOneClient = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:7070/ask_for_start"))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(ask)))
                .build();

        while(!has_game_started){
            HttpResponse<String> responseAskStart = client.send(startGameRequestOneClient, HttpResponse.BodyHandlers.ofString());
            var startGameOneClientBody = responseAskStart.body();
            returned_value = objectMapper.readValue(startGameOneClientBody, Boolean.class);
            if(returned_value){
                has_game_started = true;
            }
            sleep(5000);
        }
        return true;
    }


    // Place-Bet request builder for multi-client games (only one player's bet is placed)
    public Status placeBet(HttpClient client, ObjectMapper objectMapper,int parseInt, int player) throws URISyntaxException, IOException, InterruptedException {
        StartGame startGame = new StartGame(parseInt, player);
        var startGameRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:7070/start"))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(startGame)))
                .build();

        HttpResponse<String> responseStartGame = client.send(startGameRequest, HttpResponse.BodyHandlers.ofString());
        int count = 0;
        var startGameBody = responseStartGame.body();
        System.out.println("Waiting for other players...");
        while((count<50) && (startGameBody.equals(""))){
            responseStartGame = client.send(startGameRequest, HttpResponse.BodyHandlers.ofString());
            startGameBody = responseStartGame.body();
            count++;
            sleep(5000);
        }
        if(count >= 50){
            throw new RuntimeException();
        }

        return objectMapper.readValue(startGameBody, Status.class);
    }

    public Status hit(HttpClient client, ObjectMapper objectMapper,int player) throws URISyntaxException, IOException, InterruptedException {
        HitRequest hitRequestTemplate = new HitRequest(player);
        var hitRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:7070/hit"))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(hitRequestTemplate)))
                .build();

        HttpResponse<String> responseHit = client.send(hitRequest, HttpResponse.BodyHandlers.ofString());
        var hit = responseHit.body();
        return objectMapper.readValue(hit, Status.class);
    }

    public Status stand(HttpClient client, ObjectMapper objectMapper,int player) throws URISyntaxException, IOException, InterruptedException {
        HitRequest standRequestTemplate = new HitRequest(player);
        var standRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:7070/stand"))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(standRequestTemplate)))
                .build();

        HttpResponse<String> standResponse = client.send(standRequest, HttpResponse.BodyHandlers.ofString());
        var stand = standResponse.body();
        return objectMapper.readValue(stand, Status.class);
    }

    public Status status(HttpClient client, ObjectMapper objectMapper) throws URISyntaxException, IOException, InterruptedException {

        var statusreq = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:7070/status"))
                .GET()
                .build();

        HttpResponse<String> status_response = client.send(statusreq, HttpResponse.BodyHandlers.ofString());
        var status_body = status_response.body();
        return objectMapper.readValue(status_body,Status.class);
    }

    public int whatPlayerAmI(HttpClient client, ObjectMapper objectMapper, String token) throws URISyntaxException, IOException, InterruptedException {
        PlayerRequest playerReq = new PlayerRequest(token);
        var playerRequest = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:7070/player"))
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(playerReq)))
                .build();

        HttpResponse<String> playerResponse = client.send(playerRequest, HttpResponse.BodyHandlers.ofString());
        var stand = playerResponse.body();
        return objectMapper.readValue(stand, Integer.class);
    }
}
