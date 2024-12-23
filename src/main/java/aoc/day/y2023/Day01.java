package aoc.day.y2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.Reader;

public class Day01 implements AdventOfCodeSolver {

    @Override
    public Object solvePartOne(String input, boolean isExample) {
        String newInput = input.replaceAll("[a-zA-Z]", "");

        List<String> inputList = Arrays.asList(newInput.split("\n"));
        List<String> combinedList = new ArrayList<>();

        for (String line : inputList) {
            String firstDigit = line.substring(0, 1);
            String lastDigit = line.substring(line.length() - 1);
            String wantedNumber = firstDigit + lastDigit;
            combinedList.add(wantedNumber);
        }
        return combinedList.stream().map(Integer::parseInt).reduce(Integer::sum).orElseThrow();
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        return runPartTwoWithOverlaps(input);
    }

    private Integer runPartTwoWithOverlaps(String input) {
        String newInput = input.replaceAll("one", "o1e")
                .replaceAll("two", "t2o")
                .replaceAll("three", "t3e")
                .replaceAll("four", "f4r")
                .replaceAll("five", "f5e")
                .replaceAll("six", "s6x")
                .replaceAll("seven", "s7n")
                .replaceAll("eight", "e8t")
                .replaceAll("nine", "n9e")
                .replaceAll("[a-zA-Z]", "");

        List<String> inputList = Arrays.asList(newInput.split("\n"));
        List<String> combinedList = new ArrayList<>();

        for (String line : inputList) {
            String firstDigit = line.substring(0, 1);
            String lastDigit = line.substring(line.length() - 1);
            String wantedNumber = firstDigit + lastDigit;
            combinedList.add(wantedNumber);
        }
        return combinedList.stream().map(Integer::parseInt).reduce(Integer::sum).orElseThrow();
    }

    private Integer runPartTwoWithoutOverlaps() {
        Map<String, String> replacements = new HashMap<>() {
            {
                put("one", "1");
                put("two", "2");
                put("three", "3");
                put("four", "4");
                put("five", "5");
                put("six", "6");
                put("seven", "7");
                put("eight", "8");
                put("nine", "9");
            }
        };

        List<String> inputList = Converter.convertInputToList(Reader.readInput(this.getClass()));
        List<String> combinedList = new ArrayList<>();

        for (String line : inputList) {

            StringBuilder sb = new StringBuilder();

            Pattern pattern = Pattern.compile("(one)|(two)|(three)|(four)|(five)|(six)|(seven)|(eight)|(nine)");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                matcher.appendReplacement(sb, replacements.get(matcher.group()));
                matcher.appendTail(sb);
            }

            String newLine;
            if (sb.isEmpty()) {
                newLine = line.replaceAll("[a-zA-Z]", "");
            } else {
                newLine = sb.toString().replaceAll("[a-zA-Z]", "");
            }

            String firstDigit = newLine.substring(0, 1);
            String lastDigit = newLine.substring(newLine.length() - 1);
            String wantedNumber = firstDigit + lastDigit;
            combinedList.add(wantedNumber);
        }
        return combinedList.stream().map(Integer::parseInt).reduce(Integer::sum).orElseThrow();
    }
}
