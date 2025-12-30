package aoc.day.y2023;

import java.util.ArrayList;
import java.util.HashSet;

import aoc.day.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.GridUtil.Position;
import aoc.util.Matrix;

public class Day03 implements AdventOfCodeSolver {

    @Override
    public Object solvePartOne(String input, boolean isExample) {
        Matrix<String> matrix = Converter.convertInputToStringMatrix(input);

        HashSet<Position> positions = new HashSet<>();

        for (int row = 0; row < matrix.rows(); row++) {
            for (int column = 0; column < matrix.columns(); column++) {
                String currentElement = matrix.get(column, row);
                if (Character.isDigit(currentElement.charAt(0)) || ".".equals(currentElement)) {
                    continue;
                }
                for (int checkRow = row - 1; checkRow < row + 2; checkRow++) {
                    for (int checkColumn = column - 1; checkColumn < column + 2; checkColumn++) {
                        if (checkRow < 0
                                || checkRow >= matrix.rows()
                                || checkColumn < 0
                                || checkColumn >= matrix.getColumn(checkRow).size()
                                || !Character.isDigit(
                                        matrix.get(checkColumn, checkRow).charAt(0))) {
                            continue;
                        }
                        int tempCheckColumn = checkColumn;
                        while (tempCheckColumn > 0
                                && Character.isDigit(matrix.get(tempCheckColumn - 1, checkRow)
                                        .charAt(0))) {
                            tempCheckColumn--;
                        }
                        positions.add(new Position(tempCheckColumn, checkRow));
                    }
                }
            }
        }
        ArrayList<Integer> partNumbers = getPartNumbers(positions, matrix);
        return partNumbers.stream().reduce(Integer::sum).orElseThrow();
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        Matrix<String> matrix = Converter.convertInputToStringMatrix(input);

        int sumOfGearRatios = 0;

        for (int row = 0; row < matrix.rows(); row++) {
            for (int column = 0; column < matrix.columns(); column++) {
                if (!"*".equals(matrix.get(column, row))) {
                    continue;
                }
                HashSet<Position> positions = new HashSet<>();
                for (int checkRow = row - 1; checkRow < row + 2; checkRow++) {
                    for (int checkColumn = column - 1; checkColumn < column + 2; checkColumn++) {
                        if (checkRow < 0
                                || checkRow >= matrix.rows()
                                || checkColumn < 0
                                || checkColumn >= matrix.getColumn(checkRow).size()
                                || !Character.isDigit(
                                        matrix.get(checkColumn, checkRow).charAt(0))) {
                            continue;
                        }
                        int tempCheckColumn = checkColumn;
                        while (tempCheckColumn > 0
                                && Character.isDigit(matrix.get(tempCheckColumn - 1, checkRow)
                                        .charAt(0))) {
                            tempCheckColumn--;
                        }
                        positions.add(new Position(tempCheckColumn, checkRow));
                    }
                }
                if (positions.size() != 2) {
                    continue;
                }
                ArrayList<Integer> partNumbers = getPartNumbers(positions, matrix);
                sumOfGearRatios += partNumbers.getFirst() * partNumbers.get(1);
            }
        }
        return sumOfGearRatios;
    }

    private static ArrayList<Integer> getPartNumbers(HashSet<Position> positions, Matrix<String> matrix) {
        ArrayList<Integer> partNumbers = new ArrayList<>();
        for (Position position : positions) {
            int columnCoordinate = position.xPos();
            StringBuilder partNumber = new StringBuilder();
            while (columnCoordinate < matrix.getColumn(position.yPos()).size()
                    && Character.isDigit(
                            matrix.get(columnCoordinate, position.yPos()).charAt(0))) {
                partNumber.append(matrix.get(columnCoordinate, position.yPos()));
                columnCoordinate++;
            }
            if (!partNumber.toString().isEmpty()) {
                partNumbers.add(Integer.parseInt(partNumber.toString()));
            }
        }
        return partNumbers;
    }
}
