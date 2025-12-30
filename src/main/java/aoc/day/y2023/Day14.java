package aoc.day.y2023;

import java.util.ArrayList;
import java.util.List;

import aoc.util.AdventOfCodeSolver;
import aoc.util.Converter;
import aoc.util.Matrix;

public class Day14 implements AdventOfCodeSolver {

    @Override
    public Object solvePartOne(String input, boolean isExample) {
        Matrix<String> matrix = Converter.convertInputToStringMatrix(input);

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
        Matrix<String> matrix = Converter.convertInputToStringMatrix(input);
        ArrayList<Matrix<String>> seenMatrixes = new ArrayList<>();

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
            Matrix<String> matrixCopy = matrix.copy();
            long indexMatrixContains = contains(seenMatrixes, matrixCopy);
            if (indexMatrixContains != -1) {
                long cycleLength = i - indexMatrixContains;
                indexForSeenMatrixes =
                        Math.toIntExact(indexMatrixContains + ((totalCycles - indexMatrixContains) % cycleLength)) - 1;
                break;
            }
            seenMatrixes.add(matrixCopy);
        }
        Matrix<String> finalMatrix = seenMatrixes.get(indexForSeenMatrixes);

        // Calculate weight
        return calculateWeight(finalMatrix);
    }

    private static boolean tiltMatrixNorth(Matrix<String> matrix) {
        boolean hasMoved = false;
        for (int y = 1; y < matrix.rows(); y++) {
            for (int x = 0; x < matrix.columns(); x++) {
                if (matrix.get(x, y - 1).equals(".") && matrix.get(x, y).equals("O")) {
                    matrix.set(x, y - 1, matrix.get(x, y));
                    matrix.set(x, y, ".");
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

    private static void rotateMatrixClockwise(Matrix<String> matrix) {
        // Traverse each cycle
        for (int i = 0; i < matrix.rows() / 2; i++) {
            for (int j = i; j < matrix.rows() - i - 1; j++) {
                // Swap elements of each cycle
                // in clockwise direction
                String temp = matrix.get(j, i);
                matrix.set(j, i, matrix.get(i, matrix.rows() - 1 - j));
                matrix.set(i, matrix.rows() - 1 - j, matrix.get(matrix.rows() - 1 - j, matrix.rows() - 1 - i));
                matrix.set(matrix.rows() - 1 - j, matrix.rows() - 1 - i, matrix.get(matrix.rows() - 1 - i, j));
                matrix.set(matrix.rows() - 1 - i, j, temp);
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

    private static long contains(List<Matrix<String>> l, Matrix<String> arr) {
        long i = 0;
        for (Matrix<String> array : l) {
            if (array.equals(arr)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private static long calculateWeight(Matrix<String> matrix) {
        long weight = 0;
        for (int i = 0; i < matrix.rows(); i++) {
            weight += matrix.getRow(i).stream()
                            .filter(element -> element.equals("O"))
                            .count()
                    * (matrix.rows() - i);
        }
        return weight;
    }
}
