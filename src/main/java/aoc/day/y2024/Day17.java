package aoc.day.y2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.exceptions.NotImplementedException;

public class Day17 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList =
                Arrays.stream(input.split("\n")).filter(elem -> !elem.isBlank()).toList();
        int registerA = Integer.parseInt(inputList.getFirst().split(": ")[1]);
        int registerB = Integer.parseInt(inputList.get(1).split(": ")[1]);
        int registerC = Integer.parseInt(inputList.get(2).split(": ")[1]);
        String[] program = inputList.getLast().split(": ")[1].split(",");
        int pointer = 0;
        List<Integer> output = new ArrayList<>();
        while (pointer < program.length) {
            int instruction = Integer.parseInt(program[pointer]);
            int operand = Integer.parseInt(program[pointer + 1]);
            if (instruction == 0) {
                registerA = registerA >> combo(operand, registerA, registerB, registerC);
            } else if (instruction == 1) {
                registerB = registerB ^ operand;
            } else if (instruction == 2) {
                registerB = combo(operand, registerA, registerB, registerC) % 8;
            } else if (instruction == 3) {
                if (registerA != 0) {
                    pointer = operand;
                    continue;
                }
            } else if (instruction == 4) {
                registerB = registerB ^ registerC;
            } else if (instruction == 5) {
                output.add(combo(operand, registerA, registerB, registerC) % 8);
            } else if (instruction == 6) {
                registerB = registerA >> combo(operand, registerA, registerB, registerC);
            } else if (instruction == 7) {
                registerC = registerA >> combo(operand, registerA, registerB, registerC);
            }
            pointer += 2;
        }
        return String.join(",", output.stream().map(String::valueOf).toList());
    }

    private int combo(int operand, int registerA, int registerB, int registerC) {
        if (operand >= 0 && operand <= 3) {
            return operand;
        }
        if (operand == 4) {
            return registerA;
        }
        if (operand == 5) {
            return registerB;
        }
        if (operand == 6) {
            return registerC;
        }
        throw new IllegalStateException();
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        throw new NotImplementedException();
    }
}
