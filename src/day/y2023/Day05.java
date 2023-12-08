package day.y2023;

import util.Reader;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day05 {

    private static HashMap<String, List<Map>> hashMap = new HashMap<>();

    public static void run() {
        String input = Reader.readFile("y2023", "05");

        List<String> inputList = Arrays.asList(input.split("\n\n"));

        List<BigInteger> seeds = new ArrayList<>(Arrays.asList(inputList.get(0).replaceAll("seeds: ", "").split(" ")).stream().map(BigInteger::new).toList());

        List<List<Map>> converterMaps = new ArrayList<>();

        for (int lineIndex = 1; lineIndex < inputList.size(); lineIndex++) {
            List<Map> maps = new ArrayList<>();
            List<String> elements = Arrays.asList(inputList.get(lineIndex).replaceAll("[\\w-]+ map:\n", "").split("\n"));
            for (String element: elements) {
                List<String> values = Arrays.asList(element.split(" "));
                Map map = new Map(new BigInteger(values.get(0)), new BigInteger(values.get(1)), new BigInteger(values.get(2)));
                maps.add(map);
            }
            converterMaps.add(maps);
        }
        for (int seedIndex = 0; seedIndex < seeds.size(); seedIndex++) {

            for (List<Map> converterMap : converterMaps) {
                BigInteger seedValue = seeds.get(seedIndex);
                BigInteger updatedSeedValue = getUpdatedValue(converterMap, seedValue);
                // print(i, updatedSeedValue);
                seeds.set(seedIndex, updatedSeedValue);
            }
            //System.out.println("======== END OF THIS SEED: " + (seedIndex + 1) + " =========");
        }
        System.out.println(Collections.min(seeds));
    }

    private static BigInteger getUpdatedValue(List<Map> maps, BigInteger seedValue) {
        Optional<BigInteger> updatedValue;
        for (Map map: maps) {
            updatedValue = getConvertedValue(seedValue, map);
            if (updatedValue.isPresent()) {
                // System.out.println("MATCHING: " + updatedValue.get());
                return updatedValue.get();
            }
        }
        //System.out.println("NOT MATCHING. Returning with: " + seedValue);
        return seedValue;
    }

    private static void print(int seedIndex, BigInteger updatedValue) {
        String type = "";
        if (seedIndex == 0) {
            type = "seed-to-soil";
        } else if (seedIndex == 1) {
            type = "soil-to-fertilizer";
        } else if (seedIndex == 2) {
            type = "fertilizer-to-water";
        } else if (seedIndex == 3) {
            type = "water-to-light";
        } else if (seedIndex == 4) {
            type = "light-to-temperature";
        } else if (seedIndex == 5) {
            type = "temperature-to-humidity";
        } else if (seedIndex == 6) {
            type = "humidity-to-location";
        } else {
            type = "Unknown index: " + seedIndex + " ";
        }
        System.out.println(type + ": " + updatedValue);
    }

    private static Optional<BigInteger> getConvertedValue(BigInteger input, Map map) {
        if (input.compareTo(map.source) >= 0 && input.compareTo(map.sourceEnd) <= 0) {
            return Optional.of(input.add(map.sourceDestinationDifference));
        }
        return Optional.empty();
    }

    private static String getMapName(List<String> inputList, int lineIndex) {
        String mapName = "";
        Pattern pattern = Pattern.compile("([\\w-]+) map");
        Matcher matcher = pattern.matcher(inputList.get(lineIndex));
        if (matcher.find()) {
            mapName = matcher.group(1);
        }
        return mapName;
    }

    private static final class Map {

        BigInteger source;
        BigInteger sourceEnd;
        BigInteger destination;
        BigInteger destinationEnd;
        BigInteger range;
        BigInteger sourceDestinationDifference;

        private Map(BigInteger destination, BigInteger source, BigInteger range) {
            this.destination = destination;
            this.source = source;
            this.range = range;
            this.destinationEnd = this.destination.add(this.range).subtract(new BigInteger("1")) ;
            this.sourceEnd = this.source.add(this.range).subtract(new BigInteger("1"));
            this.sourceDestinationDifference = this.destination.subtract(this.source);
        }
    }
}
