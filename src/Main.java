import day.y2023.Day01;
import day.y2023.Day02;
import day.y2023.Day04;
import day.y2023.Day05;
import day.y2023.Day06;
import day.y2023.Day07;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
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
            }
        }
    }
}