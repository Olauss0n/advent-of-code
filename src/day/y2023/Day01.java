package day.y2023;

import util.Reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day01 {

    public static void run() {
        System.out.print("For part one: ");
        runPartOne();
        System.out.print("For part two: ");
        runPartTwo();
    }

    public static void runPartOne() {
        String input = Reader.readInputAsString("y2023", "01");

        String newInput = input.replaceAll("[a-zA-Z]", "");

        List<String> inputList = Arrays.asList(newInput.split("\n"));
        List<String> combinedList = new ArrayList<>();

        for (String line : inputList) {
            String firstDigit = line.substring(0, 1);
            String lastDigit = line.substring(line.length() - 1);
            String wantedNumber = firstDigit + lastDigit;
            combinedList.add(wantedNumber);
        }
        combinedList.stream().map(Integer::parseInt).reduce(Integer::sum).ifPresent(System.out::println);
    }

    public static void runPartTwo() {
        runPartTwoWithOverlaps();
        // runPartTwoWithoutOverlaps();
    }

    private static void runPartTwoWithOverlaps() {
        String input = Reader.readInputAsString("y2023", "01");

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
        combinedList.stream().map(Integer::parseInt).reduce(Integer::sum).ifPresent(System.out::println);
    }

    private static void runPartTwoWithoutOverlaps() {
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

        List<String> inputList = Reader.readInputAsList("y2023", "01");
        List<String> combinedList = new ArrayList<>();

        for (String line : inputList) {

            StringBuffer sb = new StringBuffer();

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
        combinedList.stream().map(Integer::parseInt).reduce(Integer::sum).ifPresent(System.out::println);
    }
}
