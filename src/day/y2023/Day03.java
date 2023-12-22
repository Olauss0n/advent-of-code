package day.y2023;

import util.Converter;
import util.Reader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day03 {

    public static void run() {
        System.out.print("For part one: ");
        runPartOne();
        System.out.print("For part two: ");
        runPartTwo();
    }

    private static void runPartOne() {
        List<String> input = Reader.readInputAsList("y2023", "03");
        String[][] matrix = Converter.convertListInputToStringMatrix(input);

        HashSet<Coordinate> coordinates = new HashSet<>();

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                String currentElement = matrix[row][column];
                if (Character.isDigit(currentElement.charAt(0)) || ".".equals(currentElement)) {
                    continue;
                }
                for (int checkRow = row - 1; checkRow < row + 2; checkRow++) {
                    for (int checkColumn = column - 1; checkColumn < column + 2; checkColumn++) {
                        if (checkRow < 0
                                || checkRow >= matrix.length
                                || checkColumn < 0
                                || checkColumn >= matrix[checkRow].length
                                || !Character.isDigit(matrix[checkRow][checkColumn].charAt(0))) {
                            continue;
                        }
                        int tempCheckColumn = checkColumn;
                        while (tempCheckColumn > 0
                                && Character.isDigit(matrix[checkRow][tempCheckColumn - 1].charAt(0))) {
                            tempCheckColumn--;
                        }
                        coordinates.add(new Coordinate(checkRow, tempCheckColumn));
                    }
                }
            }
        }
        ArrayList<Integer> partNumbers = new ArrayList<>();

        for (Coordinate coordinate : coordinates) {
            Integer columnCoordinate = coordinate.column;
            StringBuilder partNumber = new StringBuilder();
            while (columnCoordinate < matrix[coordinate.row].length
                    && Character.isDigit(matrix[coordinate.row][columnCoordinate].charAt(0))) {
                partNumber.append(matrix[coordinate.row][columnCoordinate]);
                columnCoordinate++;
            }
            if (!partNumber.toString().isEmpty()) {
                partNumbers.add(Integer.parseInt(partNumber.toString()));
            }
        }
        partNumbers.stream().reduce(Integer::sum).ifPresent(System.out::println);
    }

    private static void runPartTwo() {
        List<String> input = Reader.readInputAsList("y2023", "03");
        String[][] matrix = Converter.convertListInputToStringMatrix(input);

        Integer sumOfGearRatios = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                if (!"*".equals(matrix[row][column])) {
                    continue;
                }
                HashSet<Coordinate> coordinates = new HashSet<>();
                for (int checkRow = row - 1; checkRow < row + 2; checkRow++) {
                    for (int checkColumn = column - 1; checkColumn < column + 2; checkColumn++) {
                        if (checkRow < 0
                                || checkRow >= matrix.length
                                || checkColumn < 0
                                || checkColumn >= matrix[checkRow].length
                                || !Character.isDigit(matrix[checkRow][checkColumn].charAt(0))) {
                            continue;
                        }
                        int tempCheckColumn = checkColumn;
                        while (tempCheckColumn > 0
                                && Character.isDigit(matrix[checkRow][tempCheckColumn - 1].charAt(0))) {
                            tempCheckColumn--;
                        }
                        coordinates.add(new Coordinate(checkRow, tempCheckColumn));
                    }
                }
                if (coordinates.size() != 2) {
                    continue;
                }
                ArrayList<Integer> partNumbers = new ArrayList<>();
                for (Coordinate coordinate : coordinates) {
                    Integer columnCoordinate = coordinate.column;
                    StringBuilder partNumber = new StringBuilder();
                    while (columnCoordinate < matrix[coordinate.row].length
                            && Character.isDigit(matrix[coordinate.row][columnCoordinate].charAt(0))) {
                        partNumber.append(matrix[coordinate.row][columnCoordinate]);
                        columnCoordinate++;
                    }
                    if (!partNumber.toString().isEmpty()) {
                        partNumbers.add(Integer.parseInt(partNumber.toString()));
                    }
                }
                sumOfGearRatios += partNumbers.get(0) * partNumbers.get(1);
            }
        }
        System.out.println(sumOfGearRatios);
    }

    private record Coordinate(Integer row, Integer column) {}
}
