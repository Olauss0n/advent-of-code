package aoc.day.y2024;

import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.MatrixUtil;
import aoc.util.MatrixUtil.Position;

public class Day18 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);
        int size = isExample ? 7 : 71;
        Position startPosition = new Position(0, 0);
        Position endPosition = isExample ? new Position(6, 6) : new Position(70, 70);
        String[][] stringMatrix = MatrixUtil.fillMatrix(MatrixUtil.createStringMatrix(size, size), ".");
        int i = 0;
        for (String line : inputList) {
            if ((i >= 12 && isExample) || i >= 1024) {
                break;
            }
            Position position =
                    new Position(Integer.parseInt(line.split(",")[0]), Integer.parseInt(line.split(",")[1]));
            stringMatrix[position.yPos()][position.xPos()] = "#";
            i++;
        }
        return MatrixUtil.calculateDistance(stringMatrix, startPosition, endPosition, 0);
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        List<String> inputList = Converter.convertInputToList(input);
        int size = isExample ? 7 : 71;
        Position startPosition = new Position(0, 0);
        Position endPosition = isExample ? new Position(6, 6) : new Position(70, 70);
        String[][] stringMatrix = MatrixUtil.fillMatrix(MatrixUtil.createStringMatrix(size, size), ".");
        Position position = startPosition;
        for (String line : inputList) {
            position = new Position(Integer.parseInt(line.split(",")[0]), Integer.parseInt(line.split(",")[1]));
            try {
                stringMatrix[position.yPos()][position.xPos()] = "#";
                MatrixUtil.calculateDistance(stringMatrix, startPosition, endPosition, 0);
            } catch (Exception e) {
                break;
            }
        }
        return position.xPos() + "," + position.yPos();
    }
}
