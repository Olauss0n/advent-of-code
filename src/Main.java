import day.y2023.Day01;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
            Integer argument = Arrays.stream(args).map(Integer::parseInt).findFirst().orElseThrow();

            System.out.printf("Today's date is %s December and the answer is: %n", argument);

            switch (argument) {
                case 1:
                    Day01.run();
                case 2:
            }
        }
    }
}