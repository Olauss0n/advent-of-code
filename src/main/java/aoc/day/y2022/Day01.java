package aoc.day.y2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import aoc.util.AdventOfCodeSolver;

public class Day01 implements AdventOfCodeSolver {

    @Override
    public Object solvePartOne(String input) {
        ArrayList<Integer> calorieList = commonPart(input);
        Optional<Integer> maxValue = calorieList.stream().max(Comparator.naturalOrder());

        return maxValue.orElseThrow();
    }

    @Override
    public Object solvePartTwo(String input) {
        ArrayList<Integer> calorieList = commonPart(input);

        calorieList.sort(Collections.reverseOrder());

        return calorieList.stream().limit(3).reduce(0, Integer::sum);
    }

    private ArrayList<Integer> commonPart(String input) {
        ArrayList<Integer> calorieList = new ArrayList<>();

        for (String element : input.split("\n\n")) {
            List<String> individualList = Arrays.asList(element.split("\n"));
            Integer calorie = individualList.stream().map(Integer::parseInt).reduce(0, Integer::sum);
            calorieList.add(calorie);
        }
        return calorieList;
    }
}
