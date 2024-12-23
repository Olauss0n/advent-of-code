package aoc.day.y2022;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;

public class Day03 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);
        int result = 0;
        for (String line : inputList) {
            String firstHalf = line.substring(0, line.length() / 2);
            String secondHalf = line.substring(line.length() / 2);

            Set<Character> firstHalfSet =
                    firstHalf.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
            result += calculateCommonCharacter(firstHalfSet, secondHalf);
        }
        return result;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);
        List<List<String>> inputMatrix = Converter.convertListInputToListMatrix(inputList, 3);

        int result = 0;
        for (List<String> list : inputMatrix) {
            String first = list.get(0);
            String second = list.get(1);
            String third = list.get(2);

            HashSet<Character> firstThirdSet = new HashSet<>();
            for (char c : first.toCharArray()) {
                firstThirdSet.add(c);
            }

            HashSet<Character> secondThirdSet = new HashSet<>();
            for (char c : second.toCharArray()) {
                if (firstThirdSet.contains(c)) {
                    secondThirdSet.add(c);
                }
            }
            result += calculateCommonCharacter(secondThirdSet, third);
        }
        return result;
    }

    private int calculateCommonCharacter(Set<Character> set, String string) {
        return string.chars()
                .mapToObj(character -> (char) character)
                .filter(set::contains)
                .findFirst()
                .map(c -> {
                    if (Character.isLowerCase(c)) {
                        return c - 'a' + 1;
                    }
                    if (Character.isUpperCase(c)) {
                        return c - 'A' + 27;
                    }
                    return 0;
                })
                .orElseThrow();
    }
}
