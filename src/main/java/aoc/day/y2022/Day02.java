package aoc.day.y2022;

import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Reader;
import lombok.Getter;

public class Day02 implements AdventOfCodeSolver {

    @Getter
    public enum Hand {
        ROCK(1),
        PAPER(2),
        SCISSOR(3);

        private final int value;

        Hand(int value) {
            this.value = value;
        }
    }

    @Override
    public Object solvePartOne() {
        List<String> inputList = Reader.readInputAsList(this.getClass());
        long score = 0;
        for (String line : inputList) {
            String opponent = line.split(" ")[0];
            String you = line.split(" ")[1];
            Hand opponentHand = getHand(opponent);
            Hand yourHand = getHand(you);
            int outcome = (yourHand.value - opponentHand.value + 3) % 3;
            if (outcome == 1) {
                score += yourHand.value + 6;
            } else if (outcome == 0) {
                score += yourHand.value + 3;
            } else {
                score += yourHand.value;
            }
        }
        return score;
    }

    @Override
    public Object solvePartTwo() {
        List<String> inputList = Reader.readInputAsList(this.getClass());
        long score = 0;
        for (String line : inputList) {
            String opponent = line.split(" ")[0];
            String you = line.split(" ")[1];
            Hand opponentHand = getHand(opponent);
            Hand yourHand = getHand(you);
            if (Hand.ROCK.name().equals(yourHand.name())) {
                // Lose
                yourHand = createHand((opponentHand.value - 1 + 3) % 3);
            } else if (Hand.PAPER.name().equals(yourHand.name())) {
                // Draw
                yourHand = createHand((opponentHand.value + 3) % 3);
            } else {
                // Win
                yourHand = createHand((opponentHand.value + 1 + 3) % 3);
            }
            int outcome = (yourHand.value - opponentHand.value + 3) % 3;
            if (outcome == 1) {
                score += yourHand.value + 6;
            } else if (outcome == 0) {
                score += yourHand.value + 3;
            } else {
                score += yourHand.value;
            }
        }
        return score;
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
}
