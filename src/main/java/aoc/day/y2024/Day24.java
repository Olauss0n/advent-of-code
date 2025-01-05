package aoc.day.y2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import aoc.util.AdventOfCodeSolver;
import aoc.util.exceptions.NotImplementedException;

public class Day24 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        String[] inputSplit = input.split("\n\n");
        List<String> wires = Arrays.stream(inputSplit[0].split("\n")).toList();
        List<String> operations =
                new ArrayList<>(Arrays.stream(inputSplit[1].split("\n")).toList());

        HashMap<String, Integer> knownRegisters = new HashMap<>();
        for (String wire : wires) {
            String register = wire.split(": ")[0];
            String value = wire.split(": ")[1];
            knownRegisters.put(register, Integer.parseInt(value));
        }
        for (int i = 0; i < operations.size(); i++) {
            GateOperation gate = Pattern.compile("(AND|OR|XOR)")
                    .matcher(operations.get(i))
                    .results()
                    .map(MatchResult::group)
                    .map(GateOperation::valueOf)
                    .toList()
                    .getFirst();
            List<String> registers = Pattern.compile("[a-z0-9]{3}")
                    .matcher(operations.get(i))
                    .results()
                    .map(MatchResult::group)
                    .toList();

            if (knownRegisters.containsKey(registers.getFirst()) && knownRegisters.containsKey(registers.get(1))) {
                knownRegisters.put(
                        registers.get(2),
                        performGateOperation(
                                knownRegisters.get(registers.getFirst()), knownRegisters.get(registers.get(1)), gate));
            } else {
                operations.add(operations.get(i));
            }
        }
        List<Integer> zValues = knownRegisters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("z"))
                .sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey()))
                .map(Entry::getValue)
                .toList();

        long result = 0;
        for (int i = 0; i < zValues.size(); i++) {
            result += (long) (zValues.get(i) * Math.pow(2, zValues.size() - 1 - i));
        }
        return result;
    }

    private int performGateOperation(int firstRegister, int secondRegister, GateOperation operation) {
        return switch (operation) {
            case AND -> firstRegister & secondRegister;
            case OR -> firstRegister | secondRegister;
            case XOR -> firstRegister ^ secondRegister;
        };
    }

    private enum GateOperation {
        AND,
        OR,
        XOR
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        throw new NotImplementedException();
    }
}
