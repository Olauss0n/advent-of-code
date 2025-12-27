package aoc.day.y2024;

import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.GridUtil;
import aoc.util.GridUtil.Direction;
import aoc.util.GridUtil.Position;
import aoc.util.GridUtil.State;

public class Day18 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);
        int size = isExample ? 7 : 71;
        Position startPosition = new Position(0, 0);
        Position endPosition = isExample ? new Position(6, 6) : new Position(70, 70);
        String[][] stringMatrix = GridUtil.fillMatrix(GridUtil.createStringMatrix(size, size), ".");
        int i = 0;
        for (String line : inputList) {
            if ((i >= 12 && isExample) || i >= 1024) {
                break;
            }
            Position position = new Position(line.split(","));
            stringMatrix[position.yPos()][position.xPos()] = "#";
            i++;
        }
        return GridUtil.calculateDistance(stringMatrix, new State(startPosition, Direction.RIGHT, 0), endPosition, 0);
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);
        int size = isExample ? 7 : 71;
        Position startPosition = new Position(0, 0);
        Position endPosition = isExample ? new Position(6, 6) : new Position(70, 70);
        String[][] stringMatrix = GridUtil.fillMatrix(GridUtil.createStringMatrix(size, size), ".");
        Position position = startPosition;
        for (String line : inputList) {
            position = new Position(line.split(","));
            try {
                stringMatrix[position.yPos()][position.xPos()] = "#";
                GridUtil.calculateDistance(stringMatrix, new State(startPosition, Direction.RIGHT, 0), endPosition, 0);
            } catch (Exception e) {
                break;
            }
        }
        return position.xPos() + "," + position.yPos();
    }
}
