package aoc;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import aoc.util.InputFetcher;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        long start = System.currentTimeMillis();
        List<Integer> arguments = Arrays.stream(args).map(Integer::parseInt).toList();
        int year = arguments.getFirst();
        int day = arguments.getLast();
        if (year > 2035 || year < 2015) {
            System.out.println("Invalid year: " + year);
            return;
        }
        if (day < 1 || day > 31) {
            System.out.println("Invalid day: " + day);
            return;
        }

        InputFetcher.fetchInput(year, day);

        System.out.println("Evaluation took: " + (System.currentTimeMillis() - start) + " ms");
    }
}
