package aoc;

import java.util.Arrays;
import java.util.List;

import aoc.runner.Run2023;
import aoc.runner.Run2024;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<Integer> arguments = Arrays.stream(args).map(Integer::parseInt).toList();
        Integer day = arguments.getFirst();
        Integer year = arguments.getLast();

        System.out.printf("Today's date is %s December %s and the answer is: %n", day, year);

        switch (year) {
            case 2023 -> Run2023.runDay(day);
            case 2024 -> Run2024.runDay(day);
            default -> throw new IllegalArgumentException("Provided year is not supported");
        }

        System.out.println("Evaluation took: " + (System.currentTimeMillis() - start) + " ms");
    }
}
