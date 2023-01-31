package fcit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;


public class App {


    public static void main(String[] args) throws IOException, InterruptedException {


        Scanner input = new Scanner(System.in);
        System.out.println("IMDB TOP 100 \n");
        boolean flag = true;
        String rnk = "";
        while (flag) {
            try {
                System.out.print("Enter a rank number between 1-100: ");
                int userRank = input.nextInt();
                if (userRank <= 100 && userRank >= 0) {
                    rnk = String.valueOf(userRank);
                    flag = false;
                } else {
                    System.out.println("Enter a rank number between 1 and 100");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a number !");
                input.nextLine();
            }
        }


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://imdb-top-100-movies.p.rapidapi.com/top" + rnk))
                .header("X-RapidAPI-Key", System.getenv("API_key"))
                .header("X-RapidAPI-Host", "imdb-top-100-movies.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


        if (response != null) {
            // System.out.println(response.body());
            ImdbInfo iInfo = parseRankResponse(response.body(), ImdbInfo.class);
            System.out.println(iInfo);
        }
    }

    public static ImdbInfo parseRankResponse(String responseString, Class<?> elementClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode ImdbInfoNode = objectMapper.readTree(responseString);
            ImdbInfo iInfo = new ImdbInfo();
            int rank = ImdbInfoNode.get("rank").intValue();
            String title = ImdbInfoNode.get("title").toString();
            int year = ImdbInfoNode.get("year").intValue();
            String rating = ImdbInfoNode.get("rating").toString();


            iInfo.setRank(rank);
            iInfo.setTitle(title);
            iInfo.setYear(year);
            iInfo.setRating(rating);


            return iInfo;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
