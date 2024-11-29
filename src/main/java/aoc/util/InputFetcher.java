package aoc.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class InputFetcher {

    public static void fetchInput(int year, int day) throws IOException, InterruptedException {

        Path path = createPath(year, day);
        String sessionCookie = getSessionToken();

        if (Files.exists(path)) {
            System.out.println("Input file already exists.");
            return;
        }
        if (sessionCookie.isEmpty()) {
            System.out.println("Session cookie could not be found.");
            return;
        }

        String url = "https://adventofcode.com/%d/day/%d/input".formatted(year, day);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Cookie", "session=" + sessionCookie)
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            System.out.println("Received http error code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
            return;
        }

        Files.createDirectories(path.getParent());

        Files.writeString(path, response.body(), StandardOpenOption.CREATE_NEW);

        System.out.println("Input saved to: " + path);
    }

    private static Path createPath(int year, int day) {
        boolean addZero = day < 10;
        return Paths.get(
                "src",
                "main",
                "resources",
                "input",
                "y%d".formatted(year),
                "input-%s%d.txt".formatted(addZero ? "0" : "", day));
    }

    private static String getSessionToken() throws IOException {
        return Files.readString(Paths.get("session-cookie.txt")).trim();
    }
}
