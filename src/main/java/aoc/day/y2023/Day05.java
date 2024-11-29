package aoc.day.y2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aoc.util.Reader;

public class Day05 {

    public static void run() {
        System.out.print("For part one: ");
        runPartOne();
        System.out.print("For part two: ");
        runPartTwo();
    }

    private static void runPartOne() {
        String input = Reader.readInputAsString("y2023", "05");
        List<String> inputList = Arrays.asList(input.split("\n\n"));
        List<Long> seeds = new ArrayList<>(
                Arrays.stream(inputList.get(0).replaceAll("seeds: ", "").split(" "))
                        .map(Long::parseLong)
                        .toList());

        for (int lineIndex = 1; lineIndex < inputList.size(); lineIndex++) {
            List<Map> maps = getMaps(inputList, lineIndex);
            seeds = getNewSeeds(seeds, maps);
        }
        Long minimumSeedValue = seeds.stream().reduce(Math::min).orElseThrow();
        System.out.println(minimumSeedValue);
    }

    private static void runPartTwo() {
        String input = Reader.readInputAsString("y2023", "05");
        List<String> inputList = Arrays.asList(input.split("\n\n"));
        List<Long> seeds = new ArrayList<>(
                Arrays.asList(inputList.get(0).replaceAll("seeds: ", "").split(" ")).stream()
                        .map(Long::parseLong)
                        .toList());

        int partitionSize = 2;
        List<List<Long>> seedRanges = new ArrayList<>();

        for (int i = 0; i < seeds.size(); i += partitionSize) {
            seedRanges.add(List.of(seeds.get(i), seeds.get(i) + seeds.get(i + 1)));
        }

        for (int lineIndex = 1; lineIndex < inputList.size(); lineIndex++) {
            List<Map> maps = getMaps(inputList, lineIndex);

            ArrayList<List<Long>> newSeeds = new ArrayList<>();

            while (!seedRanges.isEmpty()) {
                List<Long> lastSeedRange = seedRanges.removeLast();
                boolean rangeFound = false;
                long seedRangeStart = lastSeedRange.get(0);
                long seedRangeEnd = lastSeedRange.get(1);
                for (Map map : maps) {
                    long overlapStart = Math.max(seedRangeStart, map.destination);
                    long overlapEnd = Math.min(seedRangeEnd, map.destination + map.range);
                    if (overlapStart < overlapEnd) {
                        newSeeds.add(List.of(
                                overlapStart - map.destination + map.source,
                                overlapEnd - map.destination + map.source));
                        if (overlapStart > seedRangeStart) {
                            seedRanges.add(List.of(seedRangeStart, overlapStart));
                        }
                        if (seedRangeEnd > overlapEnd) {
                            seedRanges.add(List.of(overlapEnd, seedRangeEnd));
                        }
                        rangeFound = true;
                    }
                }
                if (!rangeFound) {
                    newSeeds.add(List.of(seedRangeStart, seedRangeEnd));
                }
            }
            seedRanges = newSeeds;
        }
        Long minimumSeedValue =
                seedRanges.stream().map(List::getFirst).reduce(Math::min).orElseThrow();
        System.out.println(minimumSeedValue);
    }

    private static List<Map> getMaps(List<String> inputList, int lineIndex) {
        List<Map> maps = new ArrayList<>();
        String[] elements =
                inputList.get(lineIndex).replaceAll("[\\w-]+ map:\n", "").split("\n");
        for (String element : elements) {
            List<String> values = Arrays.asList(element.split(" "));
            Map map = new Map(values.get(0), values.get(1), values.get(2));
            maps.add(map);
        }
        return maps;
    }

    private static ArrayList<Long> getNewSeeds(List<Long> seeds, List<Map> maps) {
        ArrayList<Long> newSeeds = new ArrayList<>();

        for (long seed : seeds) {
            boolean rangeFound = false;
            for (Map map : maps) {
                if (map.destination <= seed && seed < map.destination + map.range) {
                    newSeeds.add(seed - map.destination + map.source);
                    rangeFound = true;
                    break;
                }
            }
            if (!rangeFound) {
                newSeeds.add(seed);
            }
        }
        return newSeeds;
    }

    private static final class Map {

        long source;
        long destination;
        long range;

        private Map(String source, String destination, String range) {
            this.source = Long.parseLong(source);
            this.destination = Long.parseLong(destination);
            this.range = Long.parseLong(range);
        }

        public String toString() {
            return "Map{source=" + source + ",destination=" + destination + ",range=" + range + "}";
        }
    }
}