package aoc.day.y2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.MatrixUtil.Direction;
import aoc.util.MatrixUtil.Position;
import aoc.util.exceptions.NotImplementedException;

import static aoc.util.MatrixUtil.isWithinBounds;

public class Day12 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input) {
        List<String> inputList = Converter.convertInputToList(input);
        String[][] matrix = Converter.convertListInputToStringMatrix(inputList);

        List<HashMap<String, Set<Position>>> arrangements = new ArrayList<>();
        HashMap<Position, Boolean> visited = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                String type = matrix[i][j];
                Position position = new Position(i, j);

                HashMap<String, Set<Position>> arrangement = new HashMap<>();

                if (!visited.containsKey(position)) {
                    Set<Position> positions = checkAdjacentPositions(matrix, position, visited, new HashSet<>());
                    positions.add(position);
                    arrangement.put(type, positions);
                    arrangements.add(arrangement);
                }
            }
        }
        int result = 0;
        for (HashMap<String, Set<Position>> arrangement : arrangements) {
            for (Entry<String, Set<Position>> entrySet : arrangement.entrySet()) {
                Set<Position> positions = entrySet.getValue();
                int value = 4 * positions.size();
                for (Position position : positions) {
                    for (Direction direction : Direction.values()) {
                        Position newPos = position.move(direction);
                        if (isWithinBounds(matrix, newPos) && positions.contains(newPos)) {
                            value -= 1;
                        }
                    }
                }
                result += value * positions.size();
            }
        }
        return result;
    }

    private Set<Position> checkAdjacentPositions(
            String[][] matrix, Position pos, HashMap<Position, Boolean> visited, Set<Position> set) {
        if (visited.containsKey(pos)) {
            return set;
        }
        visited.put(pos, true);
        set.add(pos);
        for (Direction direction : Direction.values()) {
            Position newPos = pos.move(direction);
            if (isWithinBounds(matrix, newPos)
                    && matrix[newPos.yPos()][newPos.xPos()].equals(matrix[pos.yPos()][pos.xPos()])) {
                set.add(newPos);
                set = checkAdjacentPositions(matrix, newPos, visited, set);
            }
        }
        return set;
    }

    @Override
    public Object solvePartTwo(String input) {
        throw new NotImplementedException();
    }
}
