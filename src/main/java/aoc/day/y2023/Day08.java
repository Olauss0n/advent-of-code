package aoc.day.y2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Reader;

public class Day08 implements AdventOfCodeSolver {

    @Override
    public Object solvePartOne() {
        List<String> input = Reader.readInputAsList(this.getClass());

        List<String> instructions = List.of(input.getFirst().split(""));

        HashMap<String, Node> nodes = new HashMap<>();

        for (int index = 2; index < input.size(); index++) {
            String s = input.get(index);
            List<String> elements = Arrays.stream(s.replaceAll("\\W+", " ").split(" "))
                    .map(String::trim)
                    .toList();
            Node node = new Node(elements.getFirst(), elements.get(1), elements.get(2));
            nodes.put(node.current, node);
        }

        Node current = nodes.get("AAA");
        Node stop = nodes.get("ZZZ");
        long steps = 0;
        while (current != stop) {
            String instruction = instructions.get((int) steps % instructions.size());
            if (instruction.equals("L")) {
                current = nodes.get(current.left);
            } else {
                current = nodes.get(current.right);
            }
            steps++;
        }
        return steps;
    }

    @Override
    public Object solvePartTwo() {
        List<String> input = Reader.readInputAsList(this.getClass());

        List<String> instructions = List.of(input.getFirst().split(""));

        HashMap<String, Node> nodes = new HashMap<>();

        for (int index = 2; index < input.size(); index++) {
            String s = input.get(index);
            List<String> elements = Arrays.stream(s.replaceAll("\\W+", " ").split(" "))
                    .map(String::trim)
                    .toList();
            Node node = new Node(elements.getFirst(), elements.get(1), elements.get(2));
            nodes.put(node.current, node);
        }

        List<Node> currentNodes = nodes.keySet().stream()
                .filter(key -> key.endsWith("A"))
                .map(nodes::get)
                .toList();

        ArrayList<Long> cycleLengths = new ArrayList<>();

        for (Node currentNode : currentNodes) {
            long steps = 0;
            while (!currentNode.current.endsWith("Z")) {
                String instruction = instructions.get((int) steps % instructions.size());
                if (instruction.equals("L")) {
                    currentNode = nodes.get(currentNode.left);
                } else {
                    currentNode = nodes.get(currentNode.right);
                }
                steps++;
            }
            cycleLengths.add(steps);
        }
        return lcm(cycleLengths);
    }

    private static long lcm(List<Long> nums) {
        if (nums.size() == 1) {
            return nums.getFirst();
        }
        long a = nums.getFirst();
        long b = lcm(nums.subList(1, nums.size()));
        return a * b / gcd(a, b);
    }

    private static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    private record Node(String current, String left, String right) {}
}
