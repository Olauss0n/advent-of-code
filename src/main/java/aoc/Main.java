package aoc;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import aoc.util.InputFetcher;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        long start = System.currentTimeMillis();
        List<Integer> arguments = Arrays.stream(args).map(Integer::parseInt).toList();
        int day = arguments.getFirst();
        int year = arguments.getLast();
        if (year > 2035 || year < 2015) {
            throw new IllegalArgumentException("Invalid year: " + year);
        }
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Invalid day: " + day);
        }

        InputFetcher.fetchInput(year, day);

        System.out.println("Evaluation took: " + (System.currentTimeMillis() - start) + " ms");
    }
}
