package aoc.day.y2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;

public class Day14 implements AdventOfCodeSolver {

    @Override
    public Object solvePartOne(String input, boolean isExample) {
        String[][] matrix = Converter.convertInputToStringMatrix(input);

        // Update matrix
        boolean shouldKeepTiltingMatrix = true;
        while (shouldKeepTiltingMatrix) {
            shouldKeepTiltingMatrix = tiltMatrixNorth(matrix);
        }

        // Calculate weight
        long weight = calculateWeight(matrix);
        return weight;
    }

    @Override
    public Object solvePartTwo(String input, boolean isExample) {
        String[][] matrix = Converter.convertInputToStringMatrix(input);
        ArrayList<String[][]> seenMatrixes = new ArrayList<>();

        int totalCycles = 1000000000;
        int indexForSeenMatrixes = 0;
        // Update matrix
        for (int i = 0; i < totalCycles; i++) {
            for (int j = 0; j < 4; j++) {
                boolean shouldKeepTiltingMatrix = true;
                while (shouldKeepTiltingMatrix) {
                    shouldKeepTiltingMatrix = tiltMatrixNorth(matrix);
                }
                rotateMatrixClockwise(matrix);
            }
            String[][] matrixCopy = new String[matrix.length][];
            for (int index = 0; index < matrix.length; index++) matrixCopy[index] = matrix[index].clone();
            long indexMatrixContains = contains(seenMatrixes, matrixCopy);
            if (indexMatrixContains != -1) {
                long cycleLength = i - indexMatrixContains;
                indexForSeenMatrixes =
                        Math.toIntExact(indexMatrixContains + ((totalCycles - indexMatrixContains) % cycleLength)) - 1;
                break;
            }
            seenMatrixes.add(matrixCopy);
        }
        String[][] finalMatrix = seenMatrixes.get(indexForSeenMatrixes);

        // Calculate weight
        long weight = calculateWeight(finalMatrix);
        return weight;
    }

    private static boolean tiltMatrixNorth(String[][] matrix) {
        boolean hasMoved = false;
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i - 1][j].equals(".") && matrix[i][j].equals("O")) {
                    matrix[i - 1][j] = matrix[i][j];
                    matrix[i][j] = ".";
                    hasMoved = true;
                }
            }
        }
        return hasMoved;
    }

    private static boolean tiltMatrixSouth(String[][] matrix) {
        boolean hasMoved = false;
        for (int i = matrix.length - 2; i >= 0; i--) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i + 1][j].equals(".") && matrix[i][j].equals("O")) {
                    matrix[i + 1][j] = matrix[i][j];
                    matrix[i][j] = ".";
                    hasMoved = true;
                }
            }
        }
        return hasMoved;
    }

    private static boolean tiltMatrixWest(String[][] matrix) {
        boolean hasMoved = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = matrix[i].length - 1; j >= 1; j--) {
                if (matrix[i][j - 1].equals(".") && matrix[i][j].equals("O")) {
                    matrix[i][j - 1] = matrix[i][j];
                    matrix[i][j] = ".";
                    hasMoved = true;
                }
            }
        }
        return hasMoved;
    }

    private static boolean tiltMatrixEast(String[][] matrix) {
        boolean hasMoved = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length - 1; j++) {
                if (matrix[i][j + 1].equals(".") && matrix[i][j].equals("O")) {
                    matrix[i][j + 1] = matrix[i][j];
                    matrix[i][j] = ".";
                    hasMoved = true;
                }
            }
        }
        return hasMoved;
    }

    private static void rotateMatrixClockwise(String[][] matrix) {
        // Traverse each cycle
        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = i; j < matrix.length - i - 1; j++) {
                // Swap elements of each cycle
                // in clockwise direction
                String temp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length - 1 - j][i];
                matrix[matrix.length - 1 - j][i] = matrix[matrix.length - 1 - i][matrix.length - 1 - j];
                matrix[matrix.length - 1 - i][matrix.length - 1 - j] = matrix[j][matrix.length - 1 - i];
                matrix[j][matrix.length - 1 - i] = temp;
            }
        }
    }

    private static void printMatrix(String[][] matrix) {
        for (String[] row : matrix) {
            for (String column : row) {
                System.out.print(column + " ");
            }
            System.out.println("");
        }
    }

    private static long contains(List<String[][]> l, String[][] arr) {
        long i = 0;
        for (String[][] array : l) {
            if (Arrays.deepEquals(array, arr)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private static long calculateWeight(String[][] matrix) {
        long weight = 0;
        for (int i = 0; i < matrix.length; i++) {
            weight += Arrays.stream(matrix[i])
                            .filter(element -> element.equals("O"))
                            .count()
                    * (matrix.length - i);
        }
        return weight;
    }
}
