package aoc.day.y2022;

import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Day02 implements AdventOfCodeSolver {

    @Getter
    @RequiredArgsConstructor
    public enum Hand {
        ROCK(1),
        PAPER(2),
        SCISSOR(3);

        private final int value;
    }

    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);
        long score = 0;
        for (String line : inputList) {
            String opponent = line.split(" ")[0];
            String you = line.split(" ")[1];
            Hand opponentHand = getHand(opponent);
            Hand yourHand = getHand(you);
            score += calculateScore(opponentHand, yourHand);
        }
        return score;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);
        long score = 0;
        for (String line : inputList) {
            String opponent = line.split(" ")[0];
            String you = line.split(" ")[1];
            Hand opponentHand = getHand(opponent);
            Hand yourHand = getHand(you);
            Hand yourNewHand = createHand((opponentHand.value + adjustHand(yourHand)) % 3);
            score += calculateScore(opponentHand, yourNewHand);
        }
        return score;
    }

    private long calculateScore(Hand opponentHand, Hand yourHand) {
        return switch ((yourHand.value - opponentHand.value + 3) % 3) {
            case 1 -> yourHand.value + 6;
            case 0 -> yourHand.value + 3;
            default -> yourHand.value;
        };
    }

    private Hand getHand(String character) {
        return switch (character) {
            case "A", "X" -> Hand.ROCK;
            case "B", "Y" -> Hand.PAPER;
            case "C", "Z" -> Hand.SCISSOR;
            default -> throw new IllegalStateException("Unexpected value: " + character);
        };
    }

    private Hand createHand(int value) {
        return switch (value) {
            case 1 -> Hand.ROCK;
            case 2 -> Hand.PAPER;
            case 0 -> Hand.SCISSOR;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }

    private int adjustHand(Hand hand) {
        return switch (hand) {
            case Hand.ROCK -> -1;
            case Hand.PAPER -> 0;
            case Hand.SCISSOR -> 1;
        };
    }
}
