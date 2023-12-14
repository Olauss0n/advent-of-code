package day.y2023;

import util.Reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day08 {

    public static void run() {
        System.out.println("For part one: ");
        runPartOne();
        System.out.println("For part two: ");
        runPartTwo();
    }

    private static void runPartOne() {
        List<String> input = Reader.readInputAsList("y2023", "08");

        List<String> instructions = List.of(input.get(0).split(""));

        HashMap<String, Node> nodes = new HashMap<>();

        for (int index = 2; index < input.size(); index++) {
            String s = input.get(index);
            List<String> elements = Arrays.stream(s.replaceAll("\\W+", " ").split(" ")).map(String::trim).toList();
            Node node = new Node(elements.get(0), elements.get(1), elements.get(2));
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
        System.out.println(steps);
    }

    private static void runPartTwo() {
        List<String> input = Reader.readInputAsList("y2023", "08");

        List<String> instructions = List.of(input.get(0).split(""));

        HashMap<String, Node> nodes = new HashMap<>();

        for (int index = 2; index < input.size(); index++) {
            String s = input.get(index);
            List<String> elements = Arrays.stream(s.replaceAll("\\W+", " ").split(" ")).map(String::trim).toList();
            Node node = new Node(elements.get(0), elements.get(1), elements.get(2));
            nodes.put(node.current, node);
        }

        List<Node> currentNodes = nodes.keySet().stream().filter(key -> key.endsWith("A")).map(nodes::get).toList();

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
        Long leastCommonMultiple = lcm(cycleLengths);
        System.out.println(leastCommonMultiple);
    }

    private static long lcm(List<Long> nums) {
        if (nums.size() == 1) {
            return nums.get(0);
        }
        long a = nums.get(0);
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
