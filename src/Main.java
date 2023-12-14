import day.y2023.Day01;
import day.y2023.Day02;
import day.y2023.Day04;
import day.y2023.Day05;
import day.y2023.Day06;
import day.y2023.Day07;
import day.y2023.Day08;
import day.y2023.Day09;
import day.y2023.Day11;
import day.y2023.Day14;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int argument = Arrays.stream(args).map(Integer::parseInt).findFirst().orElseThrow();

        System.out.printf("Today's date is %s December and the answer is: %n", argument);

        if (argument == 1) {
            Day01.run();
        } else if (argument == 2) {
            Day02.run();
        } else if (argument == 4) {
            Day04.run();
        } else if (argument == 5) {
            Day05.run();
        } else if (argument == 6) {
            Day06.run();
        } else if (argument == 7) {
            Day07.run();
        } else if (argument == 8) {
            Day08.run();
        } else if (argument == 9) {
            Day09.run();
        } else if (argument == 11) {
            Day11.run();
        } else if (argument == 14) {
            Day14.run();
        }

        System.out.println("Evaluation took: " + (System.currentTimeMillis() - start) + " ms");
    }
}