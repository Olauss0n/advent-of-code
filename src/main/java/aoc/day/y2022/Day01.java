package aoc.day.y2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import aoc.util.Reader;

public class Day01 {

    public static void run() {
        System.out.printf("Part 1: %s%n", runPart1());
        System.out.printf("Part 2: %s%n", runPart2());
    }

    public static String runPart1() {
        ArrayList<Integer> calorieList = commonPart();
        Optional<Integer> maxValue = calorieList.stream().max(Comparator.naturalOrder());

        return maxValue.orElseThrow().toString();
    }

    public static String runPart2() {
        ArrayList<Integer> calorieList = commonPart();

        calorieList.sort(Collections.reverseOrder());

        return calorieList.stream().limit(3).reduce(0, Integer::sum).toString();
    }

    private static ArrayList<Integer> commonPart() {
        String input = Reader.readInputAsString("y2022", "01");

        ArrayList<Integer> calorieList = new ArrayList<>();

        for (String element : input.split("\n\n")) {
            List<String> individualList = Arrays.asList(element.split("\n"));
            Integer calorie = individualList.stream().map(Integer::parseInt).reduce(0, Integer::sum);
            calorieList.add(calorie);
        }
        return calorieList;
    }
}
