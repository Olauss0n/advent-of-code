import java.util.Arrays;
import java.util.List;

import runner.Run2023;
import runner.Run2024;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<Integer> arguments = Arrays.stream(args).map(Integer::parseInt).toList();

        System.out.printf("Today's date is %s December %s and the answer is: %n", arguments.getFirst(), arguments.getLast());

        switch (arguments.getLast()) {
            case 2023 -> Run2023.runDay(arguments.getFirst());
            case 2024 -> Run2024.runDay(arguments.getFirst());
            default -> throw new IllegalArgumentException("Provided year is not supported");
        }
        ;

        System.out.println("Evaluation took: " + (System.currentTimeMillis() - start) + " ms");
    }
}