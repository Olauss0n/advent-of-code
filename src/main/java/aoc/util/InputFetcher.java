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

        if (Files.exists(path)) {
            return;
        }

        String url = "https://adventofcode.com/%d/day/%d/input".formatted(year, day);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Cookie", "session=" + getSessionToken())
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.statusCode());
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
        String sessionCookie = Files.readString(Paths.get("src", "main", "java", "aoc", "util", "session-cookie.txt"))
                .trim();
        if (sessionCookie.isEmpty()) {
            throw new RuntimeException("Could not load session cookie");
        }
        return sessionCookie;
    }
}
