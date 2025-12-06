package aoc;

import java.io.IOException;
import java.time.LocalDate;

import aoc.util.InputFetcher;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        long start = System.currentTimeMillis();
        int year, day;
        if (args.length == 0) {
            year = LocalDate.now().getYear();
            day = LocalDate.now().getDayOfMonth();
        } else {
            year = Integer.parseInt(args[0]);
            day = Integer.parseInt(args[1]);
        }
        InputFetcher.fetchInput(year, day);

        System.out.println("Evaluation took: " + (System.currentTimeMillis() - start) + " ms");
    }
}
