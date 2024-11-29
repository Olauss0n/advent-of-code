package aoc.day.y2023;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aoc.util.Reader;

public class Day06 {

    public static void run() {
        System.out.print("For part one: ");
        runPartOne();
        System.out.print("For part two: ");
        runPartTwo();
    }

    private static void runPartOne() {
        List<String> inputList = Reader.readInputAsList("y2023", "06");

        List<Integer> times = Arrays.stream(
                        inputList.get(0).replaceAll("Time: +", "").split(" +"))
                .map(Integer::parseInt)
                .toList();
        List<Integer> distances = Arrays.stream(
                        inputList.get(1).replaceAll("Distance: +", "").split(" +"))
                .map(Integer::parseInt)
                .toList();

        ArrayList<List<Integer>> races = new ArrayList<>();

        for (int index = 0; index < times.size(); index++) {
            ArrayList<Integer> heats = new ArrayList<>();
            for (int holdDuration = 0; holdDuration <= times.get(index); holdDuration++) {
                int remainingDuration = times.get(index) - holdDuration;
                int travelDistance = Math.multiplyExact(remainingDuration, holdDuration);
                if (travelDistance > distances.get(index)) {
                    heats.add(holdDuration);
                }
            }
            races.add(heats);
        }
        races.stream().map(List::size).reduce(Math::multiplyExact).ifPresent(System.out::println);
    }

    private static void runPartTwo() {
        List<String> inputList = Reader.readInputAsList("y2023", "06");

        BigInteger time = BigInteger.valueOf(Long.parseLong(inputList.get(0).replaceAll("Time: +| +", "")));
        BigInteger distance = BigInteger.valueOf(Long.parseLong(inputList.get(1).replaceAll("Distance: +| +", "")));

        ArrayList<BigInteger> heats = new ArrayList<>();

        for (BigInteger holdDuration = new BigInteger("0");
                holdDuration.compareTo(time) <= 0;
                holdDuration = holdDuration.add(new BigInteger("1"))) {
            BigInteger remainingDuration = time.subtract(holdDuration);
            BigInteger travelDistance = remainingDuration.multiply(holdDuration);
            if (travelDistance.compareTo(distance) > 0) {
                heats.add(holdDuration);
            }
        }
        System.out.println(heats.size());
    }
}