package aoc.day.y2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aoc.day.AdventOfCodeSolver;
import aoc.util.exception.NotImplementedException;
import aoc.util.parse.Parser;

public class Day14 implements AdventOfCodeSolver {
    @Override
    public Object solvePartOne(String input, boolean isExample) {
        List<String> inputList = Parser.toList(input);

        Pattern pattern = Pattern.compile("-?\\d+");
        int width = isExample ? 11 : 101;
        int height = isExample ? 7 : 103;
        int[][] matrix = new int[height][width];
        for (int[] array : matrix) {
            Arrays.fill(array, 0);
        }
        for (String line : inputList) {
            Matcher matcher = pattern.matcher(line);

            List<Integer> numbers = new ArrayList<>();
            while (matcher.find()) {
                numbers.add(Integer.parseInt(matcher.group()));
            }
            int xStartPos = numbers.get(0);
            int yStartPos = numbers.get(1);
            int xDirection = numbers.get(2);
            int yDirection = numbers.get(3);
            int xPos = ((xStartPos + xDirection * 100) % width + width) % width;
            int yPos = ((yStartPos + yDirection * 100) % height + height) % height;

            matrix[yPos][xPos] += 1;
        }

        int xMiddle = Math.floorDiv(width, 2);
        int yMiddle = Math.floorDiv(height, 2);

        int topLeft = 0;
        int bottomLeft = 0;
        int topRight = 0;
        int bottomRight = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                int value = matrix[row][col];
                if (row == yMiddle || col == xMiddle || value == 0) {
                    continue;
                }
                if (row < yMiddle) {
                    if (col < xMiddle) {
                        topLeft += value;
                    }
                    if (col > xMiddle) {
                        topRight += value;
                    }
                }
                if (row > yMiddle) {
                    if (col < xMiddle) {
                        bottomLeft += value;
                    }
                    if (col > xMiddle) {
                        bottomRight += value;
                    }
                }
            }
        }
        return topLeft * topRight * bottomLeft * bottomRight;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        throw new NotImplementedException();
    }
}
