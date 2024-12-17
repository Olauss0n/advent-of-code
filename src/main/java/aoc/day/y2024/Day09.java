package aoc.day.y2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;

import aoc.util.AdventOfCodeSolver;

public class Day09 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input) {
        String[] inputArray = input.replace("\n", "").split("");
        int fileId = 0;
        ArrayList<String> disk = new ArrayList<>();
        for (int i = 0; i < inputArray.length; i++) {
            int value = Integer.parseInt(inputArray[i]);
            if (i % 2 == 0) {
                for (int j = 0; j < value; j++) {
                    disk.add(String.valueOf(fileId));
                }
                fileId += 1;
            } else {
                for (int j = 0; j < value; j++) {
                    disk.add(".");
                }
            }
        }
        List<Integer> spaces = IntStream.range(0, disk.size())
                .filter(elem -> disk.get(elem).equals("."))
                .boxed()
                .toList();
        for (Integer space : spaces) {
            while (disk.getLast().equals(".")) {
                disk.removeLast();
            }
            if (disk.size() <= space) {
                break;
            }
            disk.set(space, disk.removeLast());
        }
        long checksum = 0;
        for (int i = 0; i < disk.size(); i++) {
            checksum += i * Long.parseLong(disk.get(i));
        }
        return checksum;
    }

    @Override
    public Object solvePartTwo(String input) {
        String[] inputArray = input.replace("\n", "").split("");
        int fileId = 0;
        int position = 0;
        Map<Integer, Data> disk = new HashMap<>();
        ArrayList<Data> spaces = new ArrayList<>();
        for (int i = 0; i < inputArray.length; i++) {
            int value = Integer.parseInt(inputArray[i]);
            if (i % 2 == 0) {
                disk.put(fileId, new Data(position, value));
                fileId += 1;
            } else {
                spaces.add(new Data(position, value));
            }
            position += value;
        }
        while (fileId > 0) {
            fileId -= 1;
            Data data = disk.get(fileId);
            for (int i = 0; i < spaces.size(); i++) {
                Data space = spaces.get(i);
                if (space.position >= data.position) {
                    spaces = new ArrayList<>(spaces.subList(0, i));
                    break;
                }
                if (data.size <= space.size) {
                    disk.put(fileId, new Data(space.position, data.size));
                    if (data.size == space.size) {
                        spaces.remove(i);
                    } else {
                        spaces.set(i, new Data(space.position + data.size, space.size - data.size));
                    }
                    break;
                }
            }
        }
        long checksum = 0;
        for (int i = 0; i < disk.size(); i++) {
            Data data = disk.get(i);
            for (long j = data.position; j < data.position + data.size; j++) {
                checksum += i * j;
            }
        }
        return checksum;
    }

    private record Data(int position, int size) implements Comparable<Data> {
        @Override
        public int compareTo(Data o) {
            if (this.position == o.position) {
                return this.size - o.size;
            }
            return this.position - o.position;
        }
    }

    private void printOut(Map<Integer, Data> disk, ArrayList<Data> spaces) {
        int counter = 0;
        int spaceCounter = 0;
        StringBuilder sb = new StringBuilder();
        for (Entry<Integer, Data> entry : disk.entrySet()) {
            if (counter == entry.getValue().position) {
                sb.append(String.valueOf(entry.getKey()).repeat(entry.getValue().size()));
                counter += entry.getValue().size();
            } else {
                Data space = spaces.get(spaceCounter);
                sb.append(".".repeat(space.size));
                spaceCounter += 1;
                counter += space.size();

                sb.append(String.valueOf(entry.getKey()).repeat(entry.getValue().size()));
                counter += entry.getValue().size();
            }
        }
        System.out.println(sb);
    }

    private void printOut(Map<Integer, Data> disk) {
        int counter = 0;
        List<Entry<Integer, Data>> sortedEntryList =
                disk.entrySet().stream().sorted(Entry.comparingByValue()).toList();
        StringBuilder sb = new StringBuilder();

        for (Entry<Integer, Data> entry : sortedEntryList) {
            if (counter != entry.getValue().position) {
                sb.append(".".repeat(entry.getValue().position - counter));
                counter += entry.getValue().position - counter;
            }
            sb.append(String.valueOf(entry.getKey()).repeat(entry.getValue().size()));
            counter += entry.getValue().size();
        }
        System.out.println(sb);
    }
}
